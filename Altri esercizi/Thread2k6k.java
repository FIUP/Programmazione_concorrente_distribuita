/**
 * Created by mattia on 03/01/17.
 * Pagina 129
 */

class T1 extends Thread{
    int i=0;
    T2 t2;
    T1(T2 t2){this.t2=t2;}
    public void run(){
        while(i<70){
            synchronized (t2){
                i++;
                if(i==2) t2.sospendi=true;
                if(i==60){
                    t2.sospendi=false;
                    t2.notify();
                }
            } // end synch
            try {
                Thread.sleep((int)(Math.random()*80));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class T2 extends Thread{
    boolean sospendi=false;
    public void run(){
        while(true){
            synchronized (this){
                while(sospendi==true) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("topolino");
            } // fine sync
            try {
                Thread.sleep((int)(Math.random()*80));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Thread2k6k{
    public static void main(String[] args){
        T2 t2=new T2();
        T1 t1=new T1(t2);
        t1.start();t2.start();
    }
}
