import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * Created by mattia on 19/01/17.
 * Esercizio appello d'esame del 26/1/2015
 */

class Modulo{
    private static int n=0;
    private String contenuto;
    private int id=n;
    Modulo(){contenuto="modulo n."+n; n=n+1;}
    private Modulo(Modulo m){n=m.n; contenuto=m.contenuto;id=m.id;}
    public String toString(){return contenuto;}

    void modifica(String s){
        try{ contenuto=contenuto+", "+s;
            Thread.sleep((int)(Math.random()*120));
        }catch(Exception e){}
    }

    Modulo copia(){return new Modulo(this);}
    int getId(){return id;}
}
class Repository {
    private Modulo[] codiceSorgente;
    public int totS=0;
    public Object lock_totS=new Object();
    Repository(){
        codiceSorgente=new Modulo[4];
        for(int i=0;i<codiceSorgente.length;i++) codiceSorgente[i]=new Modulo();
    }

    public String toString(){
        String s="Contenuto del repository:\n";
        for(Modulo m:codiceSorgente) s=s+m+"\n";
        return s;
    }

    private void build(){ System.out.println(this);}

    Modulo getCopyM(int i){ // return una copia del modulo i
        synchronized (codiceSorgente[i]) {
            return codiceSorgente[i].copia();
        }
    }

    boolean testM(int i, Modulo m){ // controlla se m è una copia del modulo i-esimo
        synchronized (codiceSorgente[i]) {
            if (i==m.getId() && codiceSorgente[i].toString() == m.toString()) return true;
            return false;
        }
    }

    void setM(int i, Modulo m){ // modifica il modulo i-esimo del repo assegnandogli il valore m
        synchronized (codiceSorgente[i]) {
            codiceSorgente[i] = m;
        }
        synchronized (lock_totS) {
            totS++;
        }
    }

    Modulo getM(int i){
        return codiceSorgente[i];
    }

    public void begin(int n){
        new Thread(){
            boolean ok=false;
            public void run(){
                while(!ok) { // non proprio bello questo modo: sarebbe da mettere in wait() finchè totS<18, ma poi come posso risvegliare un Thread di una classe anonima?
                    synchronized (lock_totS) {
                        if (totS == 18) {
                            ok = true;
                            build();
                            lock_totS.notifyAll(); // risveglio sul main
                        }
                    }
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

class Sviluppatore extends Thread{
    private Repository r;
    private String nome;
    private int nMod;
    Sviluppatore(Repository rep, String n){
        r=rep;nome=n; nMod=((int)(Math.random()*10)%4);//sceglie un modulo a caso da modificare
    }
    public void run(){
        boolean ok=false;
        do {
            Modulo originale = r.getCopyM(nMod); // 1. crea il riferimento originale inizializzato con una copia del modulo numero nMod
            Modulo mod = originale.copia(); // 2. crea un’ulteriore copia di questo modulo, su cui invoca il metodo modifica()
            mod.modifica("Modificato da sviluppatore " + nome);
            synchronized (r.getM(nMod)) {
                if (r.testM(nMod, originale)) {// 3. se il modulo numero nMod nel repository è ancora uguale al valore di originale , allora aggiorna il modulo del
                    // repository con il modulo ottenuto al punto 2, altrimenti ricomicia dal punto 1.
                    r.setM(nMod, mod);
                    ok = true;
                }
            }
        }while(!ok);
    }
}

public class ContinuosIntegration{
    public static void main(String[] a){
        Repository r=new Repository();
        Sviluppatore[] elencoS=new Sviluppatore[18];
        for(int i=0;i<elencoS.length;i++) elencoS[i]=new Sviluppatore(r,"sviluppatore "+i);
        r.begin(elencoS.length);
        for(Sviluppatore s : elencoS) s.start();
        end(r);
    }

    public static  void end(Repository r){
        try {
            synchronized (r.lock_totS) {
                while (r.totS < 18) r.lock_totS.wait();
            }
            System.out.println("Fine main, gli sviluppatori hanno sviluppato");
        }catch (InterruptedException e){e.printStackTrace();}
    }
}
