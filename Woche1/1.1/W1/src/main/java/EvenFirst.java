import java.util.Comparator;

public class EvenFirst implements Comparator<Ergebnis> {

    @Override
    public int compare(Ergebnis o1, Ergebnis o2) {
        if(o1.punkte % 2 == o2.punkte % 2) {
            return o1.compareTo(o2);
        }
        if( o1.punkte % 2 == 0) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
