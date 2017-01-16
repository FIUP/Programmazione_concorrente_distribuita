/**
 * Created by mattia on 15/01/17.
 * pagina 202
 */
/*

class Automobile extends Thread{
    private int distanza;
    private Rotatoria r;
    private int direzione; // Nord=0,Est=1,sud=2,ovest=3

    Automobile(int d,Rotatoria rr,int dd){distanza=d;r=rr;direzione=dd;}

    public void run(){
        try{
            while(distanza>0){
                distanza--;
                sleep((int)(Math.random()*100));
            }
            r.entraRotatoria(direzione);
            sleep((int)Math.random()*140); // auto resta nella rotatoria
            r.esciRotatoria(direzione);
        }catch(InterruptedException e){e.printStackTrace();}
    }
}

class Rotatoria{
    private Object nord=new Object(); // 0
    private Object est=new Object(); // 1
    private Object sud=new Object(); // 2
    private Object ovest=new Object(); // 3
    void entraRotatoria(int dir){
        if(dir==0){
            synchronized (est){
                System.out.println("Entra NORD");
                est.notify();
            }
        }else if(dir==1){
            synchronized ()
        }else if(dir==2){

        }else {
        }
    }
    void esciRotatoria(int dir){}
}

public class QuattroAuto{
    public static void main(String[] args){
        Rotatoria r=new Rotatoria();
        Automobile[] a=new Automobile[4];
        for(int i=0;i<4;i++){
            a[i]=new Automobile(10,r,i);
            a[i].start();
        }
    }
}
*/