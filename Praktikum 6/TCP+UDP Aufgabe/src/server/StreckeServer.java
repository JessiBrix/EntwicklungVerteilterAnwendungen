package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class StreckeServer {
    private static ServerSocket socketServer;
    private static ChatFrame gui = new ChatFrame("Zug-Projekt");
    private static ClientHandler worker;

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start() throws IOException {
        //	eingehende Socket-Verbindung wird akzeptiert
        //	ClientHandler worker = new ClientHandler(socket);
        //	worker wird gestartet

        socketServer = new ServerSocket(4712);
        System.out.println(socketServer.getInetAddress());
        while (gui.isShowing()) {
            gui.output.append("Warten auf Client\n");
            Socket aClient = socketServer.accept();
            worker = new ClientHandler(aClient, gui);
            worker.start();
            gui.output.append("Client " + aClient.getInetAddress() + " verbindet sich\n");
        } // end-while
    }

    public void stop() {

    }
}


