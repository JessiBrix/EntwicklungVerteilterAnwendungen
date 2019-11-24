import java.rmi.*;
import java.rmi.server.*;                                                 

public class HelloClient 
{
    static public void main (String[] args) /* args[0]: host, args[1]: port*/ 
    {
	    String host = (args.length < 1) ? "localhost" : args[0];
	    int port = (args.length < 2) ? 4711 : Integer.parseInt(args[1]);
	    try 
	    {   
	        System.out.print("security");                                                                
	        System.setSecurityManager(new RMISecurityManager()); //<--	
	        System.out.println(" ...done");                                                                
	        System.out.print("lookup");                                                                
	        HelloI obj = (HelloI) Naming.lookup("rmi://" + host + ":" + port + "/" + "HelloObj");
	        System.out.println(" ...done");                                                                
	    
	        System.out.println (obj.sayHello("italiano")); sleep();
	        System.out.println (obj.sayHello("français")); sleep();
	        System.out.println (obj.sayHello("Deutsch")); sleep();
	        System.out.println (obj.sayHello("GB"));
	    } 
	    catch (Exception e) 
	   {
	       System.out.println ("Registring failed, caught exception " + e.getMessage());
	    }
    }

    static private void sleep() 
    {
	    try 
	    { 
	        Thread.sleep(500); 
	    }
	    catch (Exception e) 
	    { 
	    }
    }
}
