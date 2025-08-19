import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class Main {

    private final BidiMap<String, String> buch = new DualHashBidiMap<>();

    public Main() {
        buch.put("Christian", "+49 211 81-14407");
        buch.put("Jan", "+49 211 81-11637");
        buch.put("Jens","+49 211 81-10714");
        buch.put("Kristin", "+49 211 81-10713");
        buch.put("Markus", "+49 211 81-13461");
    }

    public void suche (String kommando, String wert) {

        switch(kommando) {
            case "name":
                System.out.println(buch.getOrDefault(wert, "Nicht vorhanden"));
                break;
            case "nummer":
                System.out.println(buch.inverseBidiMap().getOrDefault(wert,"Nicht vorhanden"));
                break;
            default:
                System.err.println("Unbekanntes Kommando");
        }
    }


    public static void main (String []args) {

        if(args.length != 2) {
            System.out.println("Aufruf: Kommando Suchwert");
        }
        Main telefonbuch = new Main();
        String kommando = args[0];
        String wert = args[1];
        telefonbuch.suche(kommando, wert);

    }
}