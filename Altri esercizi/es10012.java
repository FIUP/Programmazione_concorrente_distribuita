// es 10.0.12 pagina 205

/*
Domanda 1:
Tutti e 3 i metodi(prenota, disdici,stopPrenotazioni) devono essere synchronized. Questo perchè esiste sempre almeno un metodo che scrive su
na variabile che è letta da almeno un altro metodo. Sarebbe meglio creare dei lock specifici, visto che ogni metodo blocca tutto il Ristorante(this).
*/

// Domanda 2 e 3

class Cliente extends Thread{
  private Ristorante r;
  private int nPrenotazione;
  Risposta risp;
  Cliente(Ristorante r, int n){this.r=r;nPrenotazione=n;}

  public void run(){
    risp=r.prenota(this);
    do{
      if(risp.prenotato)
        if( ((int)Math.random()*10)%3 == 0 ) r.disdici();
      if(risp.overbooking) wait();
    }while(risp.overbooking==true && risp.prenotato==true); // perchè in prenota(Cliente c) abbiamo return Risposta(0,false, false) se è scaduto il tempo
  }
  public void avvisa(Risposta risp){
    if(!risp.prenotato && !risp.overbooking) notifyAll(); // tempo scaduto, risveglio tutti in maniera tale che anche quelli in attesa terminino.
    this.risp.prenotato=risp.prenotato;
    this.risp.overbooking=risp.overbooking;
    notify();
  }
}

public class Ristorante{
  public static void main(String[] args){
    Ristorante r=new Ristorante();
    new Thread(){
      Tempo t=new Tempo(r);
      t.start();
      t.join();
      System.out.println("Mancano due giorni a capodanno!")
    }
    for(int i=0;i<120;i++){
      new Cliente(r,i).start();
    }
  }
}
