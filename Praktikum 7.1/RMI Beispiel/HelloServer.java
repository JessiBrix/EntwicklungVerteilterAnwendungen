import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class HelloServer 
{    
    public static void main (String[] args /* args[0]: port */) throws RemoteException 
    {
	   int port = (args.length > 0) ? Integer.parseInt(args[0]) : 4711;

	    HelloImpl obj = new HelloImpl();
	    String objName = "HelloObj";

	    if (System.getSecurityManager() == null) 
	    {
	        System.setSecurityManager (new RMISecurityManager());
	    }
	
	    Registry reg = LocateRegistry.getRegistry (port);
	    boolean bound = false;
	    for (int i = 0; ! bound && i < 2; i++) 
	    {
	        try 
	        {
		        reg.rebind (objName, obj);
		        bound = true;
		        System.out.println (objName+" bound to registry, port " + port + "."); 
	        }
	        catch (RemoteException e) 
	        {
		        System.out.println ("Rebinding " + objName + " failed, retrying ...");
		        reg = LocateRegistry.createRegistry (port);
		        System.out.println ("Registry started on port " + port + ".");
	        }
	    }
	    System.out.println ("HelloServer ready.");
    }
}
