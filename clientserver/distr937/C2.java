import java.util.*;
import java.rmi.*;
public class C2 extends UnicastRemoteObject implements Int{
  C2(){}
   public static void main(String[] args){
     Int o=Naming.lookup("rmi://"+HOST+"/remoto");
     o.f();
   }
  }
}
