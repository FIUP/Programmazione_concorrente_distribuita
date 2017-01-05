import java.util.Vector;

/**
 * Created by mattia on 05/01/17.
 * Pagina 144
 */

class Registro_Redditi{
    private int[] redditi;
    int n_componenti;
    Registro_Redditi(int dim_famiglia){redditi=new int[dim_famiglia];n_componenti=dim_famiglia;}
    public int riassunto(){ // ritorna la somma dei metodi dichiarati
        int sum=0;
        for(int i=0;i<n_componenti;i++)
            sum+=redditi[i];
        return sum;
    }
}

abstract class Membro_Famiglia extends Thread{
    protected Registro_Redditi r;
    Membro_Famiglia(Registro_Redditi r, String nome){super(nome);this.r=r;}
    public abstract void run();
}

public class TasseAbstr {

}
