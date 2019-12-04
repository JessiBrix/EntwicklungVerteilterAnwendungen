package server;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class MethodResponse extends Response
{
	
	private String valueType;      // Boolean
	private String value;         // z.B 17
	
	public MethodResponse(){
		ClientResponse clientResponse1 = Client.create().resource(
				"http://localhost:8080/rest/client" ).get( ClientResponse.class );
		System.out.println( clientResponse1.getStatus() );                // 200
		if ( clientResponse1.hasEntity() )
			System.out.println( clientResponse1.getEntity( String.class )); // Yea!
	}
	
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setValueType(String valueType){
		this.valueType = valueType;
	}
	
	public String getValueType(){
		return this.valueType;
	}
		

}
