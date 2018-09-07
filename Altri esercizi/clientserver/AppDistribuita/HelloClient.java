package clientserver.AppDistribuita;

import java.rmi.ConnectException;
import java.rmi.Naming;

/**
 * Created by mattia on 09/01/17.
 */
public class HelloClient {
    private static final String HOST="localhost";
    public static void main(String[] args) throws Exception{
        try{
            // ottieni istanza di oggetto remoto(di tipo giusto)
            Hello ref=(Hello) Naming.lookup("rmi://"+HOST+"/Hello");
            // invoca il metodo remoto
            System.out.println("msg ricevuto: "+ref.sayHello());
        }catch(ConnectException e){
            System.out.println("Problemi di connessione al server");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
