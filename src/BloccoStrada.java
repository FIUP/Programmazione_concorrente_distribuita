import java.util.Vector;

/**
 * Created by mattia on 05/01/17.
 * pagina 147
 */

class Passaggio{

}

class Auto{
    static int n=0;
    private int id=0;
    Auto(){id=n++;}
    public int getId(){return id;}
}

class Stop extends Thread {
    private Passaggio p;
    private String senso;
    private Vector<Auto> auto = new Vector<Auto>();

    Stop(String senso, Passaggio p) {
        this.senso = senso;
        this.p = p;
    }

    public synchronized boolean isEmpty() {
        return auto.isEmpty();
    }

    public synchronized void add(Auto a) {
        auto.add(a);
        notify();
    }

    public synchronized void svuota() {
        while (!auto.isEmpty()) {
            Auto x = auto.remove(0);
            System.out.println(x.getId() + " passa " + senso);
            try {
                Thread.sleep((int) (Math.random()) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (isEmpty()) wait();
                }
                synchronized (p){
                    svuota();
                }
            }
        }catch (InterruptedException e){}
    }
}



public class BloccoStrada {
    public static void main(String[] args){
        Passaggio p=new Passaggio();
        Stop c1=new Stop("Destra", p);
        Stop c2=new Stop("Sinistra",p);
        Stop[] ac=new Stop[2];
        ac[0]=c1;ac[1]=c2;
        c1.start();c2.start();
        for(int i=0;i<10;i++){
            ac[i%2].add(new Auto());
            try {
                Thread.sleep((int)(Math.random()*20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
