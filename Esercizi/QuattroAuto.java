import java.util.concurrent.locks.*;
/**
 * Created by mattia on 15/01/17.
 * pagina 202
 */

class Automobile extends Thread{
    private int distanza;
    private Rotatoria r;
    protected int direzione; // Nord=0,Est=1,sud=2,ovest=3

    Automobile(int d,Rotatoria rr,int dd){distanza=d;r=rr;direzione=dd;}

    public void run(){
        try{

            while(distanza>0){
                distanza--;
                sleep((int)(Math.random()*100));
            }
            //System.out.println(direzione);
            r.entraRotatoria(direzione);
            sleep((int)Math.random()*5000); // auto resta nella rotatoria
            r.esciRotatoria(direzione);
        }catch(InterruptedException e){e.printStackTrace();}
    }
}

class Rotatoria{
    boolean direzione[]=new boolean[4];
    Rotatoria(){
        for(int i=0;i<4;i++) direzione[i]=false;
    }
    void entraRotatoria(int dir){
        try {
            synchronized (this){
                while(direzione[(dir+1)%4]==true) wait();
                direzione[dir]=true;
                System.out.println("Entra "+dir);
            }
        }catch(InterruptedException e){}
    }
    void esciRotatoria(int dir){
        synchronized (this){
            direzione[dir]=false;
            System.out.println("Esce "+dir);
            notifyAll(); // per essere precisi bisognerebbe risvegliare chi attendeva in direzione[(dir+1)%4]
        }
    }
}

public class QuattroAuto{
    public static void main(String[] args){
        Rotatoria r=new Rotatoria();
        Automobile[] a=new Automobile[4];
        for(int i=0;i<4;i++){
            a[i]=new Automobile(10,r,i);
        }
        a[0].start();a[2].start();a[3].start();a[1].start();
    }
}
