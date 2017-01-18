/**
 * Created by mattia on 16/01/17.
 * pagina 203
 */
public class Distributore {
    private Object acqua=new Object();
    private Object vino=new Object();
    private  Object coca_cola=new Object();

    public void riempiBicchiere(){
        Cliente c=(Cliente)(Thread.currentThread()); // returns a reference to the currently executing thread object.
        if(c.bevanda=="acqua"){
            synchronized (acqua){
                System.out.println("ACQUA di "+c.id);
            }
        }else if(c.bevanda=="vino"){
            synchronized (vino){
                System.out.println("VINO di "+c.id);
            }
        }else{ // c.bevanda==coca cola
            synchronized (coca_cola){
                System.out.println("COCA COLA di "+c.id);
            }
        }
    }

    public class Cliente extends Thread{
        int id;
        String bevanda;
        Cliente(int id,String bevanda){this.id=id;this.bevanda=bevanda;}
        public void run(){
            System.out.println("Sono il cliente n "+id+" e voglio bere "+bevanda);
            riempiBicchiere();
            System.out.println("Sono il cliente n "+id+" e ho bevuto "+bevanda);
        }
    }

    public static void main(String[] args){
        Distributore d=new Distributore();
        String b;
        int a=0,v=0,c=0;
        for(int i=0;i<100;i++){
            int bev=(int)(Math.random()*3);
            if(bev==0){
                b="acqua";
                a++;
            }
            else if(bev==1){
                b="vino";
                v++;
            }
            else{
                b="coca cola";
                c++;
            }
            d.new Cliente(i,b).start();
        }
        System.out.println("ACQUA: "+a+" || VINO: "+v+" || COCA COLA: "+c);
    }

}
