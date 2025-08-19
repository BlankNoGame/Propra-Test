package propra;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Zulassung {
    public static void main(String[] args) {
        List<Integer> punktzahl = new ArrayList<>();
        int ergebnis = 0;
        int count = 0;
        int bestanden = 0;
        punktzahl.add(42);
        punktzahl.add(23);
        punktzahl.add(12);
        punktzahl.add(54);
        punktzahl.add(20);
        punktzahl.remove(2);

        for(int i:punktzahl) {
            System.out.println(i);
        }

        Collections.sort(punktzahl);

        for(int i:punktzahl) {
            ergebnis = ergebnis + i;
            count = count + 1;
        }
        System.out.println("Durchscnitt: " + ergebnis/count);

        for(int i:punktzahl) {
            if(i > 30 ) {
                bestanden = bestanden + 1;
            } else{
                i++;
            }
        }
        System.out.println("Bestanden: " + bestanden);


        HashMap<Integer, Double> matrikelnummerPunkte = new HashMap<>();
        
        matrikelnummerPunkte.put(1942000, 33.2);
        matrikelnummerPunkte.put(1972300, 10.1);
        matrikelnummerPunkte.put(203400, 50.3);
        matrikelnummerPunkte.put(1942000, 11.2);

        for (Map.Entry<Integer, Double> eintrag : matrikelnummerPunkte.entrySet()) {
            System.out.println("%d hat %6.2f Punkte".formatted(eintrag.getKey(), eintrag.getValue()));
            }

        System.out.println(matrikelnummerPunkte);
        System.out.println(matrikelnummerPunkte.getOrDefault(20340,0.0));





        Zulassung zulassung = new Zulassung();
        System.out.println("\n// -- Ausgabe der main-Methode -\n");
        zulassung.listen();
        System.out.println("\n// -----------------------------\n");
        zulassung.hashmap();
        System.out.println("\n// -- Ende Ausgabe main --------\n");
    }

    private void listen() {
        // Beginnen Sie hier
    }

    private void hashmap() {
        // Für den Aufgabenteil danach
    }

}
