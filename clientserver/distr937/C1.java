import java.util.*;
import java.rmi.*;
public class C1 extends UnicastRemoteObject implements Int{
  Object lock=new Object();
  C1(){}
  public void m(String s){
    int i=0;
    while(i<5){
      System.out.println(s+"-");
      i++;
    }
   }
   public static void main(String[] args){

     Int o=Naming.lookup("rmi://"+HOST+"/remoto");

     // t1 e t2 sono in realtà anonimi. Si potevano creare due classi T1 e T2 e forse poteva essere meglio...

     new Thread({ // t1
       public void run(){
         m("A");
         synchronized(lock){
             o.f();
         }
         m("B");
       }
     }).start();

     new Thread({ // t2
       public void run(){
         m("UNO");
         synchronized(lock){
             o.g();
         }
         m("DUE");
       }
     }).start();

   }
  }
}
