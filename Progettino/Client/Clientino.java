package Progettino.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Clientino {
    public static void main(String[] args) {
        final String DEFAULT_SERVER_ADDRESS = "127.0.0.1";
        final int DEFAULT_SERVER_PORT = 1050;
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Inserisci l'indirizzo del server (default: " + DEFAULT_SERVER_ADDRESS + "):");
            String serverInput = input.nextLine().trim();
            String server = serverInput.isEmpty() ? DEFAULT_SERVER_ADDRESS : serverInput;

            System.out.println("Inserisci la porta del server (default: " + DEFAULT_SERVER_PORT + "):");
            String portInput = input.nextLine().trim();
            int porta = portInput.isEmpty() ? DEFAULT_SERVER_PORT : Integer.parseInt(portInput);

            InetAddress serverAddress = InetAddress.getByName(server);
            Socket socket = new Socket(serverAddress, porta);
            System.out.println("Connessione al server " + socket.getInetAddress() + " sulla porta " + socket.getPort());

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String command;
            while (true) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("END_OF_MESSAGE")) break;
                    System.out.println(line);
                }

                System.out.print("> ");
                command = input.nextLine();

                if (command == null || command.equalsIgnoreCase("END")) {
                    out.println("END");
                    break;
                }

                out.println(command);
            }

            out.close();
            in.close();
            socket.close();
            System.out.println("Connessione chiusa.");
        } catch (IOException e) {
            System.err.println("Errore durante la connessione al server: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Porta non valida. Deve essere un numero intero.");
        }
    }
}


