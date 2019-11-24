import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class Client {

    public static void main(String args[]) {
        String host = (args.length < 1) ? "localhost" : args[0];
        int port = (args.length < 2) ? 4711 : Integer.parseInt(args[1]);

        System.setSecurityManager(new RMISecurityManager());
        ServiceInterface meinServer = null;
        try {
            meinServer = (ServiceInterface) Naming.lookup("rmi://" + host + ":" + port + "/" + "ServerObj");
            meinServer.boundless(1);
            meinServer.tuwas();
        } catch (NotBoundException e) {
            System.out.println("Failed to lookup a name that has no associated binding");
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL occured");
        } catch (RemoteException e) {
            System.out.println("Remote Method Call failed");
        }
        String str1;
//        try
        {
            Integer i = new Integer(3);
            Float f = new Float(4.2f);
            Double d = new Double(5.7999999);
            tuwas(i, f, d);

                System.out.println("i=" + i + "f=" + f + "d=" + d);
            try {
                str1 = meinServer.tuwas(i, f, d);
                System.out.println(str1);
            } catch (MyException e) {
                System.out.println("MyException was called");
            } catch (RemoteException e) {
                System.out.println("Remote Method Call failed");
            }

            DataTransferObject t1 = null;
            t1 = new DataTransferObject(1);
            DataTransferObject t2 = null;
            try {
                t2 = meinServer.transfer(t1);
            } catch (RemoteException e) {
                System.out.println("Remote Method Call meinServer.transfer(t1) failed");
            }
            System.out.println("t1=" + t1.value + "   t2=" + t2.value);
        }
    }

    static public String tuwas(Integer i, Float j, Double d) {
        System.out.println("tuwas(int float double)");
        i = new Integer(11);
        j = 0.15f;
        d = new Double(23d);
        return "Erfolg";
    }
}