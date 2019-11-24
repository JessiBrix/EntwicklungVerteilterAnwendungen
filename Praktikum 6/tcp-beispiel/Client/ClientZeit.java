package Client;//   Client f�r Server-Zeit


import java.net.*;
import java.io.*;


public class ClientZeit
{
    public static void main(String args[]) throws IOException
	{
	    Socket server = new Socket("localhost", 4711);
		System.out.println("Verbindung zu "+ server.getInetAddress());
		InputStream in = server.getInputStream();
		
		byte b[] = new byte[100];
		int num = in.read(b);
		String date= new String(b);
		System.out.println("Server sagt "+date);
	}
}