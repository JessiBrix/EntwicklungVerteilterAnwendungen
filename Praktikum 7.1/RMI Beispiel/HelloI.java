import java.rmi.*;

public interface HelloI extends Remote {
   public String sayHello (String language) throws RemoteException;
}
