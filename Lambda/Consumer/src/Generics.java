public class Generics {


    static public Integer[] add (Integer[] zahlen, Integer a) {
        int size = zahlen.length;
        Integer[] neueZahlen = new Integer[size + 1];

        for(int i = 0; i < size; i++){
            neueZahlen[i] = zahlen[i];
        }
        neueZahlen[size] = a;
        return neueZahlen;
    }

    public static <T> void arrayPrint (T[] array) {
        for(T elem : array) {
            System.out.println(elem);
        }
    }

    public static void main(String [] args) {

        Integer[] array = {1,2,3,4,5,6,7};
        arrayPrint(array);
        Integer [] neuerArray = add(array, 10);

        arrayPrint(neuerArray);

        Box<String> textBox = new Box<>();
        Box<Integer> integerBox = new Box<>();

        integerBox.set(3);
        textBox.set("hallo");

        System.out.println(textBox.get());
        System.out.println(integerBox.get());

    }


}
