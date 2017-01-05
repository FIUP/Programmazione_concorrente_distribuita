import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mattia on 04/01/17.
 * pagin 141
 */

class Cliente extends Thread {
    private TavolaCalda mensa;
    private static int nC;
    private int numero;
    Cliente(TavolaCalda m) {
        mensa = m; numero = nC++;
    }
    public void run() {
        mensa.prendi_primo(numero);
        mensa.prendi_secondo(numero);
        mensa.paga(this);
    }
    public void pagamento() {
        System.out.println(numero+" ha pagato");
    }
}

public class TavolaCalda {
    private Object cassa = new Object();
    private Object primo = new Object();
    private Object secondo = new Object();
    private int clienti_paganti=0;
    private int prossimo_primo_da_servire = 0;
    private int prossimo_secondo_da_servire = 0;
    void prendi_primo(int i){
        try{
            synchronized(primo) {
                while(i > prossimo_primo_da_servire)
                    primo.wait();
                prossimo_primo_da_servire++;
                System.out.println(i+" ordinato primo");
                primo.notifyAll();
                Thread.sleep((int)(Math.random()*3000));
            }


        } catch(InterruptedException e) {}
    }
    void prendi_secondo(int i){
        try{
            try { Thread.sleep(10); } catch(InterruptedException e){}
            synchronized(secondo) {
                while(i > prossimo_secondo_da_servire)
                    secondo.wait();
                        System.out.println(i+" ordinato secondo");
                prossimo_secondo_da_servire++;
                secondo.notifyAll();
            }
        } catch(InterruptedException e) {}
    }
    void paga(Cliente c) {
        synchronized(cassa) {
            c.pagamento();
            clienti_paganti++;
            cassa.notifyAll();
        }
    }
    void generaClienti(final int n){
        Thread t = new Thread(){
            public void run() {
                for(int i=0; i<n; ++i) new Cliente(TavolaCalda.this).start();
            }
        };
        t.start();
    }
    void attendiClienti(final int n){
        try {
            synchronized(cassa) {
                while (clienti_paganti < n) // la tavola calda sta in attesa sulla cassa fino al pagamento di n clienti.
                    cassa.wait();
                System.out.println("Tutti i "+n+" clienti hanno pagato");
            }
        } catch(InterruptedException e) {}
    }

    public static void main(String[] args){
        TavolaCalda m = new TavolaCalda();
        m.generaClienti(10);
        m.attendiClienti(10);
    }
}