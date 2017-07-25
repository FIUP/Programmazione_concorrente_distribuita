package clientserver.multiserverMinimale;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by mattia on 08/01/17.
 */
public class ServerThread extends Thread{
    private Socket s=null;
    public ServerThread(Socket socket){
        super("ServerThread");
        s=socket;
    }
    public void run(){
        try{
            OutputStream o=s.getOutputStream();
            PrintWriter output=new PrintWriter(o,true);
            InputStream i=s.getInputStream();
            Scanner input=new Scanner(i);
            output.println("Ciao caro client!");// spedisce
            String dato=input.nextLine(); // legge il dato mandato dal client(?)
            System.out.println(dato);
        }catch(IOException e){
            System.err.print("I/O exception");
            System.exit(1);
        }
        finally{
            try{
                s.close();
            }catch(IOException e){
                System.err.print("o nooo");
                System.exit(0);
            }
        }
    }
}
