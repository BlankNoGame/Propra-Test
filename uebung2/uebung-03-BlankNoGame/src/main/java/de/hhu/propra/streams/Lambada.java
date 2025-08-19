package de.hhu.propra.streams;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambada {
    public static void main(String[] args) {
        Stream<Integer> objStream = IntStream.range(1, 10) // geht auch in einem Schritt, also als List<Integer> list = IntStream.range(1,10)
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .boxed();
            List<Integer> intList = objStream.toList();
            System.out.println(intList);
    }
}
