package server;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class StreckeStub
{

	/**
	 * 
	 * Der Strecke-Stub muss so implementiert werden, dass 
	 * bei der �bergabe der entsprechenden URI
     * ein Response-Objet zur�ckgegeben wird
	 * 
	 */

	StreckeStub() {
		WebResource service = Client.create().resource("http://localhost:8080/rest");
		System.out.println(service.path("client").accept(MediaType.TEXT_PLAIN).get(String.class));
	}
}