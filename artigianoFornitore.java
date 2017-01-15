/**
 * Created by mattia on 15/01/17.
 * pagina 201
 */

class Risorsa{}

class Fornitore{
    public Risorsa produci(Artigiano a){
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.arrivata=true;
        synchronized (a.lavoro) {
            a.lavoro.notify(); // l'artigiano potrebbe essersi messo in attesa
        }
        return new Risorsa();
    }
}

class Artigiano extends Thread{
    private Fornitore f;
    protected boolean arrivata=false;
    protected Object lavoro=new Object();
    Artigiano(String n,Fornitore f){super(n); this.f=f;}
    public void run(){
        try {
            f.produci(this);
            for (int i = 0; i < 100; i++)
                if(i==99)
                    System.out.println(getName());
            synchronized (lavoro) {
                while (arrivata == false) lavoro.wait();
            }
            System.out.println(getName()+" Completo il lavoro iniziato");
        }catch(InterruptedException e){e.printStackTrace();}
    }
}

public class artigianoFornitore{
    public static void main(String[] args){
        Fornitore f=new Fornitore();
        Artigiano a=new Artigiano("A",f);
        Artigiano b=new Artigiano("B",f);
        Artigiano c=new Artigiano("C",f);
        a.start();b.start();c.start();
    }
}