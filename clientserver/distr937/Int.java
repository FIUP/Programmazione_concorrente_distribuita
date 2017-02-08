
// Int = Interfaccia
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Int extends Remote{
  void f() throws RemoteException;
  void m() throws RemoteException;
}
