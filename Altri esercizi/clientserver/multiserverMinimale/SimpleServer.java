package clientserver.multiserverMinimale;

import java.io.*;
import java.net.*;

/**
 * Created by mattia on 08/01/17.
 */
public class SimpleServer{
    private int port=3456;
    private ServerSocket s= null;

    public void activate(){
        try{
            s=new ServerSocket(port);
        }catch (IOException e){
            System.err.print("Could not listen on port: "+port);
            System.exit(1);
        }
        while(true){
            try {
                Socket s1=s.accept(); // attesa che un client si connetta
                ServerThread st=new ServerThread(s1); // creo il thread con i parametri del socket del client
                st.start();
            }catch (IOException e){}
        }
    }
    public static void main(String[] args){
        SimpleServer s=new SimpleServer();
        s.activate();
    }
}
