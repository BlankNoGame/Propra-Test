package HosenMitTaschen;

public class Hose <T1, T2>{

    private Tasche<T1> linkeTasche;
    private Tasche<T2> rechteTasche;

    public Hose () {
        this.linkeTasche = new Tasche<>();
        this.rechteTasche = new Tasche<>();
    }

    public void inDieLinkeTaschePacken (T1 inhalt) {
        linkeTasche.setInhalt(inhalt);
    }

    public void inDieRechteTaschePacken (T2 inhalt) {
        rechteTasche.setInhalt(inhalt);
    }

    @Override
    public String toString(){
        return "Hose{" + "linkeTasche = " + linkeTasche + ", rechteTasche = " + rechteTasche+'}';
    }


}
