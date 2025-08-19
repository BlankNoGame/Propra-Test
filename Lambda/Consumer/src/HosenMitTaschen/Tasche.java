package HosenMitTaschen;

public class Tasche <T>{

    private T inhalt;

    public void setInhalt(T inhalt) {
        this.inhalt = inhalt;
    }

    public T getInhalt() {
        return inhalt;
    }

    @Override
    public String toString() {
        return "Tasche{ " + "inhalt = " + inhalt + '}';
    }

}
