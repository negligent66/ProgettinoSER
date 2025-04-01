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


            out.println("Digita una parola chiave seguita dal valore da cercare (END per uscire). Esempio: comune Roma");

            while (true) {
                String str = in.readLine();
                if (str == null || str.equalsIgnoreCase("END")) break;

                String[] parts = str.split(" ", 2);
                if (parts.length < 2) {
                    out.println("Formato non valido. Usa 'parolaChiave valore'.");
                    continue;
                }

                String keyword = parts[0].toLowerCase();
                String value = parts[1];
                List<Datini> results = null;


                switch (keyword) {
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
                        continue;
                }

                if (results != null && !results.isEmpty()) {
                    int i = 1;
                    for (Datini dati : results) {
                        out.println(i + ") " + dati.getComune() + ", " + dati.getProvincia() + ", " + dati.getRegione() + ", " + dati.getNome());
                        i++;
                    }
                } else {
                    out.println("Nessun risultato trovato.");
                }
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
