import java.util.Vector;

/**
 * Created by mattia on 04/01/17.
 * pagina 139
 */

class Aereo{
    private static int n;
    private int num;
    private String direzione;
    Aereo(String d){num=n++;direzione=d;}
    //ATTENZIONE: invocare questo metodo quando l'aereo è in pista
    public void stampa(){System.out.println("Aereo num "+num+" - "+direzione);}
}

class GeneraArrivi extends Thread{
    private Controllore contr;
    GeneraArrivi(Controllore c){contr=c;}
    public void run(){

        while(true){
            contr.add_arrivi(new Aereo("in arrivo"));
        }
    }
}

class GeneraPartenze extends Thread{
    private Controllore contr;
    GeneraPartenze(Controllore c){contr=c;}
    public void run(){

        while(true){
            contr.add_partenze(new Aereo("in partenza"));
        }
    }
}

class Controllore extends Thread{
    Object pista=new Object();
    private Vector<Aereo> ritardo=new Vector<Aereo>();
    private Vector<Aereo> coda_arrivi=new Vector<Aereo>();
    private Vector<Aereo> coda_partenze=new Vector<Aereo>();

    void add_arrivi(Aereo a){
        synchronized (coda_arrivi) {
            coda_arrivi.add(a);
            coda_arrivi.notifyAll();
        }
    }
    void add_partenze(Aereo a){
        synchronized (coda_partenze) {
            coda_partenze.add(a);
            coda_partenze.notifyAll();
        }
    }

    private char prox_transito(){
        int r=(int)(Math.random()*2);
        if(r==0) return 'A';
        else return 'P';
    }
    public void run(){
        while(true){
            char c=prox_transito();
            if(c=='A') gestisci_arrivo();
            else gestisci_partenza();
        }
    }

    private synchronized void gestisci_arrivo(){
        Aereo x;
        synchronized (coda_arrivi){
            if(coda_arrivi.isEmpty()==true){
                (new TS(coda_arrivi)).start();
                return;
            }else x=coda_arrivi.remove(0);
        }
        synchronized (pista){ // deve atterrare
            try {
                sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x.stampa();
        }
    }
    private void gestisci_partenza(){
        Aereo x;
        synchronized (coda_partenze){
            if(coda_partenze.isEmpty()==true){
                (new TS(coda_partenze)).start();
                return;
            }else x=coda_partenze.remove(0);
        }
        synchronized (pista){ // deve decollare
            try {
                sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x.stampa();
        }
    }
    private class TS extends Thread{
        Vector<Aereo> coda;
        TS(Vector<Aereo> s){coda=s;}
        public void run() {
            try{
                Aereo x;
                synchronized (coda){
                    while(coda.isEmpty()==true) coda.wait();
                    x=coda.remove(0);
                }
                synchronized (pista){ // l'aereo in ritardo deve usare la pista
                    x.stampa();
                }
            }catch(InterruptedException e){}
        }
    }
}

public class Aeroporto{
    public static  void  main(String[] args){
        Controllore contr=new Controllore();
        GeneraArrivi gA=new GeneraArrivi(contr);
        GeneraPartenze gP=new GeneraPartenze(contr);
        gP.start();gA.start();contr.start();
    }
}
