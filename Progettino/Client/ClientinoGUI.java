package Progettino.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.List;

public class ClientinoGUI extends JFrame {

    private JButton bConnetti, bDisconnetti, bInvia;
    private JTextField ipCampo, portCampo, valoreCampo;
    private JComboBox<String> chiaveBox, valoreBox;
    private JTextArea areaRisposta;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Map<String, Set<String>> valoriPerCampo = new HashMap<>();

    public ClientinoGUI() {
        setTitle("Client Antenne GUI");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inizializzazione();
    }

    private void inizializzazione() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ipCampo = new JTextField("127.0.0.1", 10);
        portCampo = new JTextField("1050", 5);

        topPanel.add(new JLabel("IP:"));
        topPanel.add(ipCampo);
        topPanel.add(new JLabel("Porta:"));
        topPanel.add(portCampo);
        valoreCampo = new JTextField(10);
        valoreCampo.setVisible(false);


        bConnetti = new JButton("Connetti");
        bDisconnetti = new JButton("Disconnetti");
        bInvia = new JButton("Invia");

        chiaveBox = new JComboBox<>(new String[]{
                "get_all", "comune", "provincia", "regione", "nome",
                "anno", "identificatore", "longitudine", "latitudine", "sort_by", "get_row", "help"
        });

        valoreBox = new JComboBox<>();
        valoreBox.setPrototypeDisplayValue("Seleziona valore...");
        valoreBox.setEnabled(false);

        topPanel.add(bConnetti);
        topPanel.add(bDisconnetti);
        topPanel.add(new JLabel("Comando:"));
        topPanel.add(chiaveBox);
        topPanel.add(new JLabel("Valore:"));
        topPanel.add(valoreBox);
        topPanel.add(valoreCampo);
        topPanel.add(bInvia);

        areaRisposta = new JTextArea();
        areaRisposta.setEditable(false);
        JScrollPane scrollArea = new JScrollPane(areaRisposta);

        add(topPanel, BorderLayout.NORTH);
        add(scrollArea, BorderLayout.CENTER);

        bConnetti.addActionListener(this::connettiServer);
        bDisconnetti.addActionListener(e -> disconnetti());
        bInvia.addActionListener(e -> inviaComando());
        chiaveBox.addActionListener(e -> aggiornaValoriDisponibili());
    }

    private void connettiServer(ActionEvent e) {
        try {
            areaRisposta.setText("");
            String ip = ipCampo.getText().trim();
            int port = Integer.parseInt(portCampo.getText().trim());
            socket = new Socket(ip, port);
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            areaRisposta.append("Connesso al server.\n");
            messaggioIniziale();
            caricaValori();

            bConnetti.setEnabled(false);
            bDisconnetti.setEnabled(true);
            bInvia.setEnabled(true);
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Errore connessione: " + ex.getMessage());
        }
    }

    private void messaggioIniziale() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("END_OF_MESSAGE")) break;
            }
        } catch (IOException e) {
            areaRisposta.append("Errore: " + e.getMessage() + "\n");
        }
    }

    private void caricaValori() {
        valoriPerCampo.clear();
        out.println("get_all");

        try {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("END_OF_MESSAGE")) break;

                String[] parti = line.split("\\)", 2);
                String dati = (parti.length > 1) ? parti[1].trim() : line.trim();

                String[] valori = dati.split(",\\s*");
                if (valori.length < 9) continue;

                aggiornaMappa("comune", valori[0]);
                aggiornaMappa("provincia", valori[1]);
                aggiornaMappa("regione", valori[2]);
                aggiornaMappa("nome", valori[3]);
                if (valori[4].matches("\\d{4}")) {
                    aggiornaMappa("anno", valori[4]);
                }
                aggiornaMappa("identificatore", valori[6]);
                aggiornaMappa("longitudine", valori[7]);
                aggiornaMappa("latitudine", valori[8]);
            }
        } catch (IOException e) {
            areaRisposta.append("Errore durante caricamento valori: " + e.getMessage() + "\n");
        }
    }

    private void aggiornaMappa(String campo, String valore) {
        valoriPerCampo.computeIfAbsent(campo, k -> new HashSet<>()).add(valore);
    }

    private void aggiornaValoriDisponibili() {
        String comando = (String) chiaveBox.getSelectedItem();
        valoreBox.removeAllItems();
        valoreBox.setVisible(true);
        valoreCampo.setVisible(false);

        if (comando.equals("get_all")){
            valoreBox.setEnabled(false);
        }
        else if (comando.equals("sort_by")) {
            valoreBox.setEnabled(true);
            List<String> chiavi = List.of("comune", "provincia", "regione", "nome", "anno",
                    "identificatore", "longitudine", "latitudine");
            for (String chiave : chiavi) valoreBox.addItem(chiave);
        } else if (comando.equals("get_row")) {
            valoreBox.setVisible(false);
            valoreCampo.setVisible(true);
        } else if (comando.equals("help")) {
            valoreBox.setVisible(false);
            valoreCampo.setVisible(false);
        } else {
            valoreBox.setEnabled(true);
            Set<String> valori = valoriPerCampo.getOrDefault(comando, new HashSet<>());
            List<String> listaOrdinata = new ArrayList<>(valori);
            listaOrdinata.sort(String.CASE_INSENSITIVE_ORDER);
            for (String valore : listaOrdinata) {
                valoreBox.addItem(valore);
            }
        }
        revalidate();
        repaint();
    }

    private void inviaComando() {
        if (socket == null || socket.isClosed()) return;

        areaRisposta.setText("");

        String comando = (String) chiaveBox.getSelectedItem();
        String valore = "";

        if (comando.equals("get_row")) {
            valore = valoreCampo.getText().trim();
        } else if (valoreBox.isEnabled() && valoreBox.isVisible()) {
            valore = (String) valoreBox.getSelectedItem();
        }

        String fullCommand = comando;
        if (!comando.equals("get_all") && valore != null && !valore.isBlank()) {
            fullCommand += " " + valore;
        }

        out.println(fullCommand);

        riceviRisposta();
    }

    private void riceviRisposta() {
        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("END_OF_MESSAGE")) break;
                    areaRisposta.append(line + "\n");
                }
            } catch (IOException ex) {
                areaRisposta.append("Errore nella comunicazione: " + ex.getMessage() + "\n");
            }
        }).start();
    }

    private void disconnetti() {
        try {
            if (out != null) out.println("END");
            if (socket != null) socket.close();

            areaRisposta.append("Disconnesso dal server.\n");
            bConnetti.setEnabled(true);
            bDisconnetti.setEnabled(false);
        } catch (IOException e) {
            areaRisposta.append("Errore nella disconnessione: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientinoGUI().setVisible(true));
    }
}

