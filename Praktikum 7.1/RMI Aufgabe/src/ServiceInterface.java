import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface extends Remote {
    public void tuwas() throws RemoteException;

    public double division() throws RemoteException;

    public String tuwas(Integer i, Float j, Double t) throws MyException, RemoteException;

    public DataTransferObject transfer(DataTransferObject t) throws RemoteException;

    public void forever(int i) throws RemoteException;

    public void boundless(int i) throws RemoteException;
}

