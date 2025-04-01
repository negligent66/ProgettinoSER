package Progettino.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverino {

    public static final int PORT = 1050;

    public static void main(String[] args) {
        System.out.println("EchoServer: started");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server Socket: " + serverSocket);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Nuova connessione accettata: " + clientSocket);

                    Thread thread = new Thread(new Threadino(clientSocket));
                    thread.start();

                } catch (IOException e) {
                    System.err.println("Accept failed: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
            System.exit(1);
        }
    }
}
