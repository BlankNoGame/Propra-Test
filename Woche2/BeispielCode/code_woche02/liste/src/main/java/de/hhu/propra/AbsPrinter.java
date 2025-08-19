package de.hhu.propra;

public class AbsPrinter implements Printer {
    public void print(int x) {
        System.out.print(Math.abs(x));
    }
}
