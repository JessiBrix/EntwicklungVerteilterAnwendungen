package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ClientConnection {

    private int seqNr;
    private Socket socket;
    private int port = 4712;

//    public ClientConnection(int seqNr)  {
//        try {
//            socket = new MulticastSocket(port);
//            group = InetAddress.getByName("224.0.0.8");
//            socket.joinGroup(group);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Verbindung zu " + socket.getInetAddress());
//    }
//
//    public String sendMsg(String dieNachricht) {
//
//        byte[] buf = (dieNachricht).getBytes();
//        DatagramPacket dg = new DatagramPacket(buf, buf.length, group, port);
//        try {
//            socket.send(dg);
//            System.out.println("Senden erfolgreich");
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        return "";
//    }
//}

    public ClientConnection(int seqNr) {
        this.seqNr = seqNr;
    }

    public String sendMsg(String dieNachricht) {

        InputStream in = null;
        byte binaryResponse[] = new byte[1000];

        try {
            Socket server = new Socket("localhost", 4712);
            System.out.println("Verbindung zu " + server.getInetAddress());
            in = server.getInputStream();
            System.out.println(dieNachricht);
            server.getOutputStream().write(dieNachricht.getBytes());
            Thread.sleep(1000);
            final int dummy = in.read(binaryResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = new String(binaryResponse);
        System.out.println("Antwort:" + response);
        return response;
    }
}