import java.rmi.*;
import java.rmi.server.*;

public class Server extends UnicastRemoteObject implements ServiceInterface
{
    public Server() throws RemoteException {
        super();
    }

    public void tuwas()
    {
        System.out.println("call: tuwas()");
        System.out.println("exit: tuwas()");
    }

    /**
     * Dies ist eine Funktion mit einer Endlosschleife
     */
    public void forever(int i)
    {
        System.out.println("call: forever");
        int ii = -i*i;
        while(ii!=0)
        {
            System.out.println(" " + ii);
            ii--;
        }
        System.out.println("exit: forever");
        return;
    }

    /**
     *  Diese Methode liefert einen Laufzeitfehler
     */
    public double division() throws RuntimeException
    {
        System.out.println("call: division()");
        double k = 1/0;
        return k;
    }

    /**
     * Diese Methode liefert eine eigene Ausnahme
     */
    public String tuwas(Integer i, Float j, Double t) throws MyException
    {
        System.out.println("call: tuwas(Integer, Float, Double)");
//		System.exit(0);
        if(1<0)
        {
            throw (new MyException("Interne Fehler in tuwas"));
        }
        i = new Integer(4711);
        j = new Float(0.815f);
        return "Erfolg";
    }

    /**
     * Diese Methode verbraucht recht schnell den gesamten Speicher
     *
     */
    public void boundless(int i) throws OutOfMemoryError
    {
        System.out.println("call: boundless");
        while(true)
        {
            System.out.println("i="+i);
            int[] a = new int[i];
            boundless(2*i);
        }
    }

    /**
     * Ein Beispiel um Daten zu �bertragen - hier nur einen Integer
     */
    public DataTransferObject transfer(DataTransferObject t)
    {
        System.out.println("call: transfer(DataTransferObject)");
        t.setValue(t.getValue()+1);
        return t;
    }
}