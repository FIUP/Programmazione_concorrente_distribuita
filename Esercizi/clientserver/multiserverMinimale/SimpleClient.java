package clientserver.multiserverMinimale;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by mattia on 08/01/17.
 */
public class SimpleClient {
    private Socket s=null;
    private InetAddress ip;
    private int port=3456;
    public void go() throws IOException{
        // parametri per connettersi al ServerSocket
        ip=InetAddress.getByName("127.0.0.1");
        s=new Socket(ip,port);
        try{
            Scanner input=new Scanner(s.getInputStream());
            OutputStream o=s.getOutputStream();
            PrintWriter output=new PrintWriter(o,true);
            String dato=input.nextLine(); // legge il dato mandato dal server
            System.out.println(dato);
            System.out.println("Premi Enter per mandare la ricevuta");
            Scanner t=new Scanner(System.in);
            String inv=t.nextLine();
            output.println(inv); // manda dato
        }finally{ // chiude sempre la connessione
            s.close();
        }
    }
    public static void main(String[] a) throws IOException{
        SimpleClient c=new SimpleClient();
        c.go();
    }
}
