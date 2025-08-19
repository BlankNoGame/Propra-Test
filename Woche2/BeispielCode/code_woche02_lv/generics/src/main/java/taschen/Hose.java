package taschen;

public class Hose<L,R> {
  private Tasche<L> linkeTasche;
  private Tasche<R> rechteTasche;

  public Hose() {
    this.linkeTasche = new Tasche<>();
    this.rechteTasche = new Tasche<>();
  }

  public void inDieLinkeTaschePacken(L inhalt) {
    linkeTasche.setInhalt(inhalt);
  }
  public void inDieRechteTaschePacken(R inhalt) {
    rechteTasche.setInhalt(inhalt);
  }

  @Override
  public String toString() {
    return "Hose{" +
        "linkeTasche=" + linkeTasche +
        ", rechteTasche=" + rechteTasche +
        '}';
  }
}
