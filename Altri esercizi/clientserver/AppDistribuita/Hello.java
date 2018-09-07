package clientserver.AppDistribuita;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by mattia on 09/01/17.
 */
public interface Hello extends Remote {
    public String sayHello() throws RemoteException;
}