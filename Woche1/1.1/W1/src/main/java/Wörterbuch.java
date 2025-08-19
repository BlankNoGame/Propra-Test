import java.security.cert.CollectionCertStoreParameters;
import java.util.Arrays;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class Wörterbuch {

    static Collection<String> input = Arrays.asList(new String[] {
            "Krokodil",
            "Bus",
            "Fahrrad",
            "Blume",
            "Schreiben",
            "Universität",
            "Schule",
            "Haus",
            "Gebäude",
            "Schmetterling",
            "Ei",
            "Lebensmittel"
    });

    private static int compare(String s1, String s2) {
        int res = -Integer.compare(s1.length(), s2.length()); //gehört zu Standard Integer Klasse
        if (res == 0) {
            return s1.compareTo(s2);
        }
        return res;
    }

    public static void  main(String[] args) {

        TreeSet<String> ausgabe = new TreeSet<>((s1,s2) -> compare(s1,s2));
        ausgabe.addAll(input);
        System.out.println(ausgabe);

    }

}
