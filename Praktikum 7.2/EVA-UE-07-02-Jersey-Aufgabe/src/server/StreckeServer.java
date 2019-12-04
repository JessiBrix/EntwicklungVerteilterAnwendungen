package server;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import java.io.IOException;



public class StreckeServer
{
	private static HttpServer server;

		public static void main(String[] args)
		{
			
			start();
			
		}

	public static void start() {
		// Eine Instanz der Strecke muss bereitgestellt werden.
		Strecke strecke = Strecke.getInstance();
		// ToDo: Der Jersey-Server muss gestartet werden
		try {
			server = HttpServerFactory.create("http://localhost:8080/rest");
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.start();
	}

	public static void stop() {
		// Die Stecke-Instanz muss gespeichert werden
		Strecke.getInstance().streckeSpeichern();
		// ToDo: Der Jersey-Server muss gestoppt werden.
		JOptionPane.showMessageDialog( null, "Ende" );
		server.stop( 0 );
	}
}


