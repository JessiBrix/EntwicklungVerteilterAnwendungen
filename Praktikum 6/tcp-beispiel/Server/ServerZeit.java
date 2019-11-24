package Server;//  ServerZeit

import java.net.*;
import java.io.*;
import java.util.*;


public class ServerZeit
{
    public static void main (String args[]) throws IOException
    {
        int port = 4711;

        ServerSocket theZeitSocket = new ServerSocket(port);
        while(true)
        {
        System.out.println("Warten auf Zeitnehmer");
        Socket aClient = theZeitSocket.accept();
        System.out.println("Client " + aClient.getInetAddress() + " verbindet sich");
        OutputStream out = aClient.getOutputStream();
        Date date = new Date();
        byte b[] = date.toString().getBytes();
        out.write(b);
        } // end-while
    }
}
