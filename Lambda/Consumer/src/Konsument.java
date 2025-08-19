import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Konsument {

    public static void main (String [] args) {

        List<Integer> zahlen = List.of(5, -3, 8, 0, -1, 12);
        List<Integer> posZahlen = new ArrayList<>();

        Consumer<Integer> zurListeHinzu = zahl -> posZahlen.add(zahl);

        zahlen.stream()
                .filter(zahl -> zahl > 0)
                .forEach(zurListeHinzu);


        System.out.println("Ursprünglich Liste: " + zahlen);
        System.out.println("Liste mit positiven Zahlen: " + posZahlen);

    }

}
