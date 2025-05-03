package Progettino.Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Threadino implements Runnable {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket clientSocket = null;
    private LettoreCSV lettoreCSV;

    public Threadino(Socket clientSocket, LettoreCSV lettoreCSV) {
        this.clientSocket = clientSocket;
        this.lettoreCSV = lettoreCSV;
    }

    public void run() {
        try {
            System.out.println("Connection accepted: " + clientSocket);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            out.println("Scrivi il comando nel seguente modo (keyWord valore) (END per uscire e help per vedere quali comandi puoi usare):");
            out.println("END_OF_MESSAGE"); // primo messaggio

            while (true) {
                String str = in.readLine();
                if (str == null || str.equalsIgnoreCase("END")) break;

                String[] parts = str.split(" ", 2);
                System.out.println(str);

                if (str.equals("help")) {
                    out.println("Chiavi che puoi usare: " +
                            "\nget_row -> restituisce una riga del file" +
                            "\nget_all -> restituisce tutto il file (non ha bisogno di un valore dopo la parola chiave)" +
                            "\ncomune -> restituisce le righe con lo stesso comune" +
                            "\nprovincia -> restituisce le righe con la stessa provincia" +
                            "\nregione -> restituisce le righe con la stessa regione" +
                            "\nnome -> restituisce le righe con lo stesso nome" +
                            "\nanno -> restituisce le righe con lo stesso anno" +
                            "\nidentificatore -> restituisce le righe con lo stesso id" +
                            "\nlongitudine -> restituisce le righe con la stessa longitudine" +
                            "\nlatitudine -> restituisce le righe con la stessa latitudine");
                    out.println("END_OF_MESSAGE");
                    continue;
                }

                String keyword = parts[0].toLowerCase();
                String value = (parts.length > 1) ? parts[1] : "";
                List<Datini> results = null;

                switch (keyword) {
                    case "get_all":
                        results = lettoreCSV.getRecords();
                        int i = 1;
                        for (Datini dati : results) {
                            out.println(i++ + ") " + dati.getComune() + ", " + dati.getProvincia()
                                    + ", " + dati.getRegione() + ", " + dati.getNome()
                                    + ", " + dati.getAnno() + ", " + dati.getDataOra()
                                    + ", " + dati.getIdentificatore() + ", " + dati.getLongitudine()
                                    + ", " + dati.getLatitudine());
                        }
                        out.println("END_OF_MESSAGE");
                        continue;

                    case "get_row":
                        try {
                            int riga = Integer.parseInt(value);
                            if (riga < 1 || riga >= lettoreCSV.getRecords().size()) {
                                out.println("Riga non valida. Inserisci un numero tra 1 e " + (lettoreCSV.getRecords().size() - 1));
                                out.println("END_OF_MESSAGE");
                                continue;
                            }
                            Datini dati = lettoreCSV.getRecords().get(riga);
                            out.println(riga + ") " + dati.getComune() + ", " + dati.getProvincia()
                                    + ", " + dati.getRegione() + ", " + dati.getNome()
                                    + ", " + dati.getAnno() + ", " + dati.getDataOra()
                                    + ", " + dati.getIdentificatore() + ", " + dati.getLongitudine()
                                    + ", " + dati.getLatitudine());
                            out.println("END_OF_MESSAGE");
                        } catch (NumberFormatException e) {
                            out.println("Valore non valido, inserisci un numero.");
                            out.println("END_OF_MESSAGE");
                        }
                        continue;
                    case "sort_by":
                        try {
                            results = lettoreCSV.ordinaPerCampo(value);
                            int p = 1;
                            switch (value.toLowerCase()) {
                                case "comune":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getComune() + ", " + dati.getProvincia() + ", " + dati.getRegione() + ", " +
                                                dati.getNome() + ", " + dati.getAnno() + ", " + dati.getDataOra() + ", " +
                                                dati.getIdentificatore() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "provincia":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getProvincia() + ", " + dati.getComune() + ", " + dati.getRegione() + ", " +
                                                dati.getNome() + ", " + dati.getAnno() + ", " + dati.getDataOra() + ", " +
                                                dati.getIdentificatore() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "regione":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getRegione() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getNome() + ", " + dati.getAnno() + ", " + dati.getDataOra() + ", " +
                                                dati.getIdentificatore() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "nome":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getNome() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getRegione() + ", " + dati.getAnno() + ", " + dati.getDataOra() + ", " +
                                                dati.getIdentificatore() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "anno":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getAnno() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getRegione() + ", " + dati.getNome() + ", " + dati.getDataOra() + ", " +
                                                dati.getIdentificatore() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "dataora":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getDataOra() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getRegione() + ", " + dati.getNome() + ", " + dati.getAnno() + ", " +
                                                dati.getIdentificatore() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "identificatore":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getIdentificatore() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getRegione() + ", " + dati.getNome() + ", " + dati.getAnno() + ", " +
                                                dati.getDataOra() + ", " + dati.getLongitudine() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "longitudine":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getLongitudine() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getRegione() + ", " + dati.getNome() + ", " + dati.getAnno() + ", " +
                                                dati.getDataOra() + ", " + dati.getIdentificatore() + ", " + dati.getLatitudine());
                                    }
                                    break;
                                case "latitudine":
                                    for (Datini dati : results) {
                                        out.println(p++ + ") " + dati.getLatitudine() + ", " + dati.getComune() + ", " + dati.getProvincia() + ", " +
                                                dati.getRegione() + ", " + dati.getNome() + ", " + dati.getAnno() + ", " +
                                                dati.getDataOra() + ", " + dati.getIdentificatore() + ", " + dati.getLongitudine());
                                    }
                                    break;
                            }
                        } catch (IllegalArgumentException e) {
                            out.println("Errore: " + e.getMessage());
                        }
                        out.println("END_OF_MESSAGE");
                        break;
                    case "comune":
                        results = lettoreCSV.ricercaComuni(value);
                        break;
                    case "provincia":
                        results = lettoreCSV.ricercaProvincia(value);
                        break;
                    case "regione":
                        results = lettoreCSV.ricercaRegione(value);
                        break;
                    case "nome":
                        results = lettoreCSV.ricercaNome(value);
                        break;
                    case "anno":
                        results = lettoreCSV.ricercaAnno(value);
                        break;
                    case "identificatore":
                        results = lettoreCSV.ricercaIdentificatore(value);
                        break;
                    case "longitudine":
                        results = lettoreCSV.ricercaLongitudine(value);
                        break;
                    case "latitudine":
                        results = lettoreCSV.ricercaLatitudine(value);
                        break;
                    default:
                        out.println("Chiave non riconosciuta. Prova con: comune, provincia, regione, nome, anno, identificatore, longitudine, latitudine.");
                        out.println("END_OF_MESSAGE");
                        continue;
                }

                if (results != null && !results.isEmpty()) {
                    int j = 1;
                    for (Datini dati : results) {
                        out.println(j++ + ") " + dati.getComune() + ", " + dati.getProvincia()
                                + ", " + dati.getRegione() + ", " + dati.getNome()
                                + ", " + dati.getAnno() + ", " + dati.getDataOra()
                                + ", " + dati.getIdentificatore() + ", " + dati.getLongitudine()
                                + ", " + dati.getLatitudine());
                    }
                } else {
                    out.println("Nessun risultato trovato.");
                }
                out.println("END_OF_MESSAGE");
            }

            out.close();
            in.close();
            clientSocket.close();
            System.out.println("Connessione chiusa.");
        } catch (IOException e) {
            System.err.println("Errore nella connessione: " + e.getMessage());
        }
    }
}
