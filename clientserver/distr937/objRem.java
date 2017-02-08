// oggetto remoto!
import java.util.*;

public class objRem implements Int extends Remote{
  Object c_lock;
  objRem(Object c_lock) throws RemoteException{this.c_lock=c_lock;}
  public void f() throws RemoteException{
   synchronized(c_lock){
    int i=0;
    while(i<5){
      System.out.println("FF-");
      i++;
    }
   }
  }
  public void g() throws RemoteException{
    int i=0;
    while(i<5){
      System.out.println("GG-");
      i++;
    }
  }
}
