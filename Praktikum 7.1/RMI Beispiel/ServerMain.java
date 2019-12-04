import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.activation.ActivateFailedException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main (String[] args /* args[0]: port */) throws RemoteException
    {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : 4711;

        String objName = "ServerObj";

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
                reg.rebind (objName, new Server());
                bound = true;
                System.out.println (objName+" bound to registry, port " + port + ".");
            }
            catch (RemoteException e)
            {
                System.out.println ("Rebinding " + objName + " failed, retrying ...");
                try {
                    reg = LocateRegistry.createRegistry(port);
                    System.out.println("Registry started on port " + port + ".");
                }
                catch (ActivateFailedException afe) {
                    System.out.println("Registry not started");
                }
            }
        }
        System.out.println ("Server ready.");
    }
}
