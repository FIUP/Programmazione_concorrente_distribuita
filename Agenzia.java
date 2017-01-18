import java.util.List;
import java.util.Vector;

/**
 * Created by mattia on 03/01/17.
 * pagina 139
 */

class Viaggio{
    String dest;int minimo;boolean conf=false,prenotabile=true;
    Vector<Persona> viaggiatori;
    Viaggio(String dest,int minimo){this.dest=dest;this.minimo=minimo;viaggiatori=new Vector<Persona>();}

    // aggiunge p all'elenco di iscritti al viaggio. TRUE <=> il viaggio è confermato, else FALSE. confermato <=> #iscritti<minimo.
    //PRE=(il viaggio è prenotabile)
    public synchronized boolean prenota(Persona p){
        System.out.println(p.nome + " prenota!");
        viaggiatori.add(p);
        if (viaggiatori.size() < minimo) return false;
        for (int i = 0; i < viaggiatori.size(); i++) // notifica, ma in questo modo non rispetta la consegna
            viaggiatori.elementAt(i).avvertito = true;
        notifyAll(); // anche notify() sarebbe potuto andare bene(ma non son sicuro)
        return true;
    }
    public synchronized void chiudi(){
        prenotabile=false;
        if(conf==true){
            System.out.println("I seguenti stanno per andare in "+dest+":");
            for(int i=0;i<viaggiatori.size();i++)
                System.out.println(viaggiatori.elementAt(i).nome);
        }else {
            System.out.println("Il viaggio è stato annullato.");
            for (int i = 0; i < viaggiatori.size(); i++) // notifica
                viaggiatori.elementAt(i).avvertito = true;
            notifyAll();
        }
    }
}

class Persona extends Thread{
    String nome;Viaggio v;boolean avvertito=false;
    Persona(String nome, Viaggio v){this.nome=nome;this.v=v;}
    public void run() {
        synchronized (v) {
            if (v.prenotabile == true) {
                v.conf = v.prenota(this);
                if (v.conf == false) {
                    while (avvertito == false) {
                        try {
                            v.wait(); // rilascio del lock
                        } catch (InterruptedException e) {
                        }
                    }

                } else ;/*
                for(int i=0;i<v.viaggiatori.size();i++)
                    System.out.println(v.viaggiatori.elementAt(i).nome + ", ti confermiamo che andrai in " + v.dest + "!");*/
            } else System.out.println(nome + ", non è più possibile prenotare questo viaggio.");
        }
    }
}

public class Agenzia{
    public static void main(String[] args){
        Viaggio v=new Viaggio("Namibia",6);
        Persona p1=new Persona("Alice",v);
        Persona p2=new Persona("Bob",v);
        Persona p3=new Persona("Carl",v);
        Persona p4=new Persona("Doug",v);
        Persona p5=new Persona("Eric",v);
        Persona p6=new Persona("Frank",v);
        Persona p7=new Persona("Matt",v);
        p6.start();p4.start();p3.start();p7.start();
        p2.start();p5.start();p1.start();

        v.chiudi();
    }
}
