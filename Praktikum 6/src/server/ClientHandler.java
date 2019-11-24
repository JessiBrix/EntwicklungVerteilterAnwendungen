package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    Socket socket;
    ChatFrame gui;

    public ClientHandler(Socket aSocket, ChatFrame gui) {
        this.socket = aSocket;
		this.gui = gui;
    }

    public void run() {

        InputStream in = null;
        String nachricht = "";
        //      Socketeinlesen-/Zur�ckschreiben
        try {
            in = socket.getInputStream();
            byte b[] = new byte[999];
            in.read(b);
            nachricht = new String(b);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StreckeStub meinStub = new StreckeStub();
        String ausgabe = meinStub.sendReceiveMsg(nachricht);
        gui.output.append(ausgabe + "\n");
    }
}

