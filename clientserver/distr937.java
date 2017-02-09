// Interfaccia condivisa dai Client e dal server

public interface I extends Remote{
  public void f() throws RemoteException;
  public void g() throws RemoteException;
}

// oggetto remoto presente sul server

class ObjRem extends UnicastRemoteObject implements I{
  public void synchronized f() throws RemoteException{
    int i=0;
    while(i<5){
      System.out.println("FF-");
      i++;
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

/** server **/

class Server{
  public static void main() throws RemoteException{
    ObjRem o=new ObjRem(); // creo l'oggetto remoto
    Naming.rebind("pippo",o); // e lo pubblicizzo
  }
}

/** Primo client**/

class C1{
  public void m(String s){
    int i=0;
    while(i<5){
      System.out.println(s+"-");
      i++;
    }
  }

  public static void main(){
    I o=(I)Naming.lookup("pippo");

    Thread t1=new Thread(){
      public void run(){
        m("A");
        o.f();
        m("B");

      }
    };

    Thread t2=new Thread(){
      public void run(){
        m("UNO");
        o.g();
        m("DUE");
      }
    };

    t1.start();
    t2.start();
  }
}

class C2{
  public static void main(){
    I o=(I)Naming.lookup("pippo");
    o.f();
  }
}
