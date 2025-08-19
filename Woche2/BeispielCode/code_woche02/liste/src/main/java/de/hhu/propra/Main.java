package de.hhu.propra;

public class Main {
    public static void main(String[] args) {
        List liste = new List();
        liste.add(1);
        liste.add(3);
        liste.add(-2);
        System.out.println(liste); // 1,3,-2

        liste.forEach(new StarPrinter()); // *1**3**-2*
        System.out.println();
        liste.forEach(new SquarePrinter()); // 194
        System.out.println();
        liste.forEach(new AbsPrinter()); // 134
        System.out.println();
    }
}
