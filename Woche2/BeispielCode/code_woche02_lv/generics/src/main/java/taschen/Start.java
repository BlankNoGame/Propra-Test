package taschen;

import taschen.inhalte.MobilTelefonHose;
import taschen.inhalte.Mobiltelefon;
import taschen.inhalte.Nokia3210;
import taschen.inhalte.Portemonnaie;
import taschen.inhalte.Taschentuch;

public class Start {

  public static void main(String[] args) {
    Hose<Taschentuch, Portemonnaie> hose = new Hose<>();
    hose.inDieLinkeTaschePacken(new Taschentuch());
    System.out.println(hose);

    MobilTelefonHose<Taschentuch> mobilTelefonHose = new MobilTelefonHose<>();
    mobilTelefonHose.inDieLinkeTaschePacken(new Nokia3210());
    mobilTelefonHose.inDieRechteTaschePacken(new Taschentuch());
    System.out.println(mobilTelefonHose);

  }

}
