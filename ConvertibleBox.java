import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;


public class ConvertibleBox<T> {
  private final T content;

  public ConvertibleBox(T content) {
    this.content = content;
  }

  public <R> R convert(Function<T,R> f) {
    return f.apply(content);
  }

  public static void main(String[] args) {
    ConvertibleBox<Integer> box = new ConvertibleBox<>(42);

    // Arbeiten Sie das Kapitel Funktionale Interfaces und Lambda-Ausdrücke durch,
    // um die Notation e -> "Wert: " + e zu verstehen
    String asString = box.convert(e -> "Wert: " + e);
    System.out.println(asString);
    
    Double asDouble = box.convert(e -> Math.PI * e);
    System.out.println(asDouble);
  }
}