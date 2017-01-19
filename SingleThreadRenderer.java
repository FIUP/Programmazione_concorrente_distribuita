import java.util.*;
/**
 * Created by mattia on 19/01/17.
 * esercizio appello 14/12/2012
 * Impossibile da provare sul serio, speremo bèn. Preso tutto da mega
 *
 * PRIMA VERSIONE
 *
 */

/*
public class SingleThreadRenderer {
...
    static void renderPage(CharSequence source){
        Runnable renderTesto = new RenderTesto();
        Thread rT = new Thread(renderTesto);
        rT.start();
        List<ImageInfo> imageInfoList = scanForImageInfo(source);
        List<Image> imageList = new ArrayList<Image>();
        Runnable downloadImmagini = new DownloadImmagini(imageInfoList,imageList);
        Thread dI = new Thread(downloadImmagini);
        dI.start();
        try {
            rT.join();
            dI.join(); // in attesa che rT e dI terminino
            for (Image img : imageList)
                renderImage(img);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class RenderTesto implements Runnable{
    public void run(){
        renderText(source);
    }
}
class DownloadImmagini implements Runnable{
    private List<ImageInfo> listaImmagini;
    private List<Image> immaginiScaricate;
    public DownloadImmagini(List<ImageInfo> l,List<Image> i){
        listaImmagini = l;
        immaginiScaricate = i;
    }
    public void run(){
        for (ImageInfo imageInfo : listaImmagini)
            immaginiScaricate.add(listaImmagini.downloadImage());
    }
}
}
*/

/** SECONDA VERSIONE **/

/*

public class SingleThreadRenderer {
...
    void renderPage(CharSequence source){
        renderText(source);
        List<ImageInfo> imageInfoList = scanForImageInfo(source);
        Object lock = new Object();
        for (ImageInfo imageInfo : imageInfoList ){
            Runnable trattaImmagine = new trattaImmagine(imageInfo, lock);
            Thread dI = new Thread(trattaImmagine);
            dI.start();
        }
    }
}
class TrattaImmagine implements Runnable{
    private ImageInfo immagineInfo;
    private Object lock;
    public DownloadImmagini(ImageInfo i, lock l){
        immagineInfo = i;
        lock = l;
    }
    public void run(){
        Image immagine = immagineInfo.downloadImage();
        synchronized(lock){
            renderImage(immagine);
        }
    }
}

*/