import java.util.Vector;

/**
 * Created by mattia on 05/01/17.
 * Pagina 144
 */

class Registro_Redditi{
    private int[] redditi;
    int n_componenti=0;
    Registro_Redditi(int dim_famiglia){redditi=new int[dim_famiglia];}
    public int riassunto(){ // ritorna la somma dei metodi dichiarati
        int sum=0;
        for(int i=0;i<n_componenti;i++)
            sum+=redditi[i];
        return sum;
    }
    public int getComponenti(){return n_componenti;}
    public void add(int v){redditi[n_componenti]=v;n_componenti++;}
}

abstract class Membro_Famiglia extends Thread{
    protected Registro_Redditi r;
    Membro_Famiglia(Registro_Redditi r, String nome){super(nome);this.r=r;}
    public abstract void run();
}

class Figlio extends Membro_Famiglia{
    Figlio(Registro_Redditi r, String nome){super(r,nome);}
    @Override
    public void run(){
        int reddito=(int)(Math.random()*3000);
        synchronized (r){
            r.add(reddito);System.out.println(Thread.currentThread().getName()+" dichiara "+reddito);
            r.notifyAll();
        }
    }
}

class Genitore extends Membro_Famiglia{
    int n_figli=0;
    Genitore(Registro_Redditi r, String nome, int n_figli){super(r,nome);this.n_figli=n_figli;}
    @Override
    public void run() {
        try {
            int reddito = (int) (Math.random() * 36000); // min 0, max 36k all'anno
            synchronized (r){
                while (r.getComponenti()<n_figli)// attesa dei redditi dei figli
                    r.wait();
                if (r.getComponenti() == n_figli) { // primo genitore, bonus 5%
                    r.add(reddito-(r.riassunto()/100*5));
                    ;System.out.println(Thread.currentThread().getName()+" dichiara "+reddito+" con bonus");
                }else{
                    r.add(reddito);
                    System.out.println(Thread.currentThread().getName()+" dichiara "+reddito);
                }
            }
        }catch(InterruptedException e){}
    }
}

public class TasseAbstr {
    public static void main(String[] args){
        Registro_Redditi r=new Registro_Redditi(6);
        Figlio f1=new Figlio(r,"Antonio");Figlio f2=new Figlio(r,"Luca");
        Figlio f3=new Figlio(r,"Giovanna");Figlio f4=new Figlio(r,"Isabella");
        Genitore g1=new Genitore(r,"Mamma",4);Genitore g2=new Genitore(r,"Papà",4);
        f1.start();f2.start();g1.start();f3.start();g2.start();f4.start();
        System.out.println(r.riassunto());
    }
}
