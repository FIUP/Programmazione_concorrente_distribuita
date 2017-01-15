package clientserver.AppDistribuita;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * pagina 184
 * Created by mattia on 09/01/17.
 */


public class HelloImpl extends UnicastRemoteObject implements Hello{
    public HelloImpl()throws RemoteException{}
    public String sayHello() throws RemoteException{
        return "Ciao mondo";
    }
}