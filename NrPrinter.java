import java.util.List;
import java.util.function.Consumer;

public class NrPrinter implements Consumer<Integer> {
    public void accept(Integer elem) {
        System.out.println(elem);
    }

    public static void main(String [] args) {
    List.of(1,1,2,3,5,8)
        .forEach(new NrPrinter());
    }
}

