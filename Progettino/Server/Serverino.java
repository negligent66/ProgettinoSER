package Progettino.Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serverino {
    public static final int PORT = 1050;


    public static void main(String[] args) {
        System.out.println("Server avviato...");
        LettoreCSV lettoreCSV = new LettoreCSV();


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuova connessione: " + clientSocket);
                new Thread(new Threadino(clientSocket, lettoreCSV)).start();
            }
        } catch (IOException e) {
            System.err.println("Errore nel server: " + e.getMessage());
        }
    }


}

