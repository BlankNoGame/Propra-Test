public class Ergebnis implements Comparable<Ergebnis>{
    protected final Integer punkte;

    Ergebnis(int punkte) {
        this.punkte = punkte;
    }

    @Override
    public String toString() {
        return "[punkte=%d]".formatted(punkte);
    }
    @Override
    public int compareTo(Ergebnis o2) {
        return this.punkte.compareTo(o2.punkte);
    }
}