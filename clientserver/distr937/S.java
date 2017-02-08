// S=Server
import java.rmi.*;
public class S{
  private static final String HOST="localhost";
  public static void main(String[] args) throws RemoteException{
    Object c_lock=new Object(); // common_lock
    objRem o=new objRem(c_lock);
    String indObj="rmi://"+HOST+"/remoto";
    Naming.rebind(indObj,o);
    C1 c1=new C1();
    C2 c2=new C2();
  }
}
