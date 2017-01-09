package clientserver.AppDistribuita;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by mattia on 09/01/17.
 */
public class HelloServer {
    private static final String HOST="localhost";
    public static void main(String[] a)throws Exception{ // crea istanza di oggetto remoto
        LocateRegistry.createRegistry(1099); // registro di bootstrap rmi. Dev'essere pronto prima del server. Questo metodo potrebbe essere sostituito dal comando rmiregistry 1099 dato al terminale(?)
        HelloImpl ref=new HelloImpl();
        //genero un nome con cui pubblicizzare l'oggetto
        String rmiObjName="rmi://"+HOST+"/Hello";
        Naming.rebind(rmiObjName,ref);
        System.out.println("Server pronto");
    }
}
