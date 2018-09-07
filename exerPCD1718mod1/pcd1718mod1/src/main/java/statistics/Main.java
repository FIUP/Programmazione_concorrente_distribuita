package statistics;

import java.io.File;

public class Main {
    public static void main(String[] s) {
        PCD1718StatisticheAnnuali sts = new PCD1718StatisticheAnnuali();
        try {
            //Path relativi differenti a seconda se esegui da terminale o da IDE
            //per IntelliJ dovrebbero essere ./input.txt e ./output.txt
            //System.out.println(new File(".").getCanonicalPath());
            sts.produceSynthetizedBalanceSheet("../../../input.txt","../../../output.txt");
        }
        catch(Exception e){System.out.println("eccezione");}
    }
}
