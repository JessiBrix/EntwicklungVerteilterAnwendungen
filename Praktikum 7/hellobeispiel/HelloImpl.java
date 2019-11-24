import java.rmi.*;
import java.rmi.server.*;

public class HelloImpl extends UnicastRemoteObject implements HelloI
{
    public HelloImpl() throws RemoteException 
    {
        super();
    }

    public String sayHello(String language) throws RemoteException 
    {
	    System.out.println ("sayHello: " + language);
	    String lang = language.toLowerCase();
        switch (lang.charAt(0)) 
        {
  	        case 'd': return "Hallo!";
	        case 'f': return "Salut!";
	        case 'i': return "Ciao!";
	        default: return "Hi!";
	    }
    }
}
