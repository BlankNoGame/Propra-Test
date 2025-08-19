package taschen;

public class Tasche<T> {

  private T inhalt;

  public T getInhalt() {
    return inhalt;
  }

  public void setInhalt(T inhalt) {
    this.inhalt = inhalt;
  }

  @Override
  public String toString() {
    return "Tasche{" +
        "inhalt=" + inhalt +
        '}';
  }
}
