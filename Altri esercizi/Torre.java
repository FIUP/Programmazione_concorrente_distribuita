/**
 * Created by mattia on 15/01/17.
 * pagina 153
 */
public class Torre{
    private int n_InEntrata, n_InAlto;
    private Object arrivano_in_coda=new Object();
    int usciti=0; // per il debug
    private class GuardiaEntrata extends Thread{
        public void run(){ // fa salire
            while(true) {
                try {
                    synchronized (arrivano_in_coda) {
                        while (n_InEntrata == 0) arrivano_in_coda.wait();
                        System.out.println("In coda: "+n_InEntrata);
                        int n=0;
                        if(n_InEntrata<10){
                            n=n_InEntrata;
                            n_InEntrata=0;
                        }else{
                            n=10;
                            n_InEntrata-=10;
                        }
                        synchronized (Torre.this) { // outerThis
                            n_InAlto += n;
                            System.out.println("In cima: "+n_InAlto);
                            Torre.this.notifyAll();
                        }

                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        public void arriva(int n){
            synchronized (arrivano_in_coda){
                System.out.println("si accodano "+n);
                n_InEntrata+=n;
                arrivano_in_coda.notifyAll();
            }
        }
    }

    private class GuardiaCima extends Thread{
        public void run() {
            while (true) {
                try {
                    synchronized (Torre.this) {
                        while (n_InAlto == 0) Torre.this.wait(); // dobbiamo rilasciare il lock preso sulla torre
                        int n = 0;
                        if (n_InAlto < 10) {
                            n = n_InAlto;
                            n_InAlto = 0;
                        }else{
                            n=10;
                            n_InAlto-=10;
                        }
                        System.out.println("Scendono: "+n+"; rimangono in cima "+n_InAlto);
                        usciti+=n;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void debug(){
        System.out.println("Usciti= "+usciti+"; in entrata= "+n_InEntrata+"; in cima= "+n_InAlto);
    }

    public static void main(String args[]){
        Torre t=new Torre();
        GuardiaEntrata gE=t.new GuardiaEntrata();
        GuardiaCima gC=t.new GuardiaCima();
        gE.start();
        gC.start();
        int turisti=0;
        while(turisti<30){
            int arrivi=(int)(Math.random()*15);
            turisti+=arrivi;
            gE.arriva(arrivi);
            System.out.println("Accodati "+arrivi);
        }



    }

}
