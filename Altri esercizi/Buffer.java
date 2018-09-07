import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.*;

/**
 * Created by mattia on 03/01/17.
 * Pagina 130
 */

class BoundedBuffer{
    final Object[] items=new Object[5];
    int count,putptr,takeptr;
    final Lock l=new ReentrantLock(); // lock esplicito
    final Condition notFull=l.newCondition();
    final Condition notEmpty=l.newCondition();

    public void put(Object o) throws InterruptedException{
        l.lock();
        while(count == items.length){
            System.out.println("Attendo: buffer PIENO");
            notFull.await();
        }
        items[putptr]=o; putptr++;
        if(putptr==items.length) putptr=0;
        count++;
        notEmpty.signalAll(); // sveglio i processi che credono che il buffer sia vuoto
        l.unlock();
    }

    public Object take() throws InterruptedException{
        l.lock();
        while(count==0){
            System.out.println("Attendo: buffer VUOTO");
            notEmpty.await();
        }
        Object o=items[takeptr];
        takeptr++;
        if(takeptr==items.length) takeptr=0;
        count--;
        notFull.signalAll(); // sveglio i processi che credono che il buffer sia pieno
        l.unlock();
        return o;
    }
}

class Produttore extends Thread{
    BoundedBuffer b;
    Produttore(BoundedBuffer buff){b=buff;}
    public void run(){
        int i=0;
        while(i<10){
            b.l.lock(); // ma serve?
            try{
                b.put("Pippo");
                System.out.println("messo numero "+i);
                i++;
            }catch(InterruptedException e){
                System.out.println("buffer PIENO");
            }
            finally{b.l.unlock();}
        }
    }
}

class Consumatore extends Thread{
    BoundedBuffer b;
    Consumatore(BoundedBuffer buff){b=buff;}
    public void run(){
        int i=0;
        while(i<10){
            b.l.lock(); // ma serve?
            synchronized (b){
                try{
                    System.out.println("preso "+b.take()+" numero "+i);
                    i++;
                }catch(InterruptedException e){
                    System.out.println("Buffer VUOTO");
                }
                finally{b.l.unlock();}
            }
        }
    }
}

public class Buffer{
    public static void main(String[] args){
        BoundedBuffer buff=new BoundedBuffer();
        Produttore prod=new Produttore(buff);
        Consumatore cons=new Consumatore(buff);
        prod.start();
        cons.start();
    }
}
