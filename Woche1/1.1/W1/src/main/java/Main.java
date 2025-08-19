import java.util.*;

public class Main {



    public static void main(String[] args) {

        Ergebnis p1 = new Ergebnis(4);
        Ergebnis p2 = new Ergebnis(-5);
        Ergebnis p3 = new Ergebnis(1);
        Ergebnis p4 = new Ergebnis(100);

        Set<Ergebnis> listeErgebnis = new TreeSet<>((o1,o2) -> -Integer.compare(o1.punkte, o2.punkte));

        listeErgebnis.add(p1);
        listeErgebnis.add(p2);
        listeErgebnis.add(p3);
        listeErgebnis.add(p4);

        SortedSet<Ergebnis> ergebnisse = new TreeSet<>();
//        Set<Ergebnis> ergebnisset = new TreeSet<>(new EvenFirst());

        ergebnisse.add(p1);
        ergebnisse.add(p2);
        ergebnisse.add(p3);

        System.out.println("Ergebnisse 1: " + ergebnisse);

//        Collections.sort(listeErgebnis, (o1,o2) -> Integer.compare(p1.punkte, p2.punkte));
        System.out.println("Ergebnisse Test lambda:" + listeErgebnis);

/*        ergebnisset.add(p1);
        ergebnisset.add(p2);
        ergebnisset.add(p3);


        System.out.println("Ergebnisse 2: " + ergebnisset);
*/
    }
}
