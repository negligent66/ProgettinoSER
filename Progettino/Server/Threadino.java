package Progettino.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Threadino implements Runnable {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket clientSocket = null;

    public Threadino(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            // bloccante finchè non avviene una connessione

            System.out.println("Connection accepted: " + clientSocket);
            // creazione stream di input da clientSocket
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(isr);
            // creazione stream di output su clientSocket
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
            // ciclo di ricezione dal client e invio di risposta
            out.print("Inserisci il tuo nome (END to close connection): ");
            out.flush();
            while (true) {
                char h;
                int somma = 0;
                String str = in.readLine();
                for(int i = 0; i < str.length(); i++){
                    h = str.charAt(i);
                    somma += h;
                }
                if (str.equals("END"))
                    break;
                System.out.println("Echoing: " + str.toUpperCase());
                out.println("somma dei valori ASCII delle lettere del tuo nome: " + somma);
            }
            // chiusura di stream e socket
            System.out.println("EchoServer: closing...");
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }

    }
}