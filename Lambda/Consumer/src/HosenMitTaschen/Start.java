package HosenMitTaschen;

public class Start {

    public static void main (String [] args ) {
        Hose<Taschentuch, Portmornaie> hose = new Hose<>();

        hose.inDieLinkeTaschePacken(new Taschentuch());
        System.out.println(hose);


    }


}
