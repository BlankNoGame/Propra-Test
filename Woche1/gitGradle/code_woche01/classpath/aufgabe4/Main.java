import mypack.Person;

public class Main {

   public static void main(String[] args) {
     Person p1 = new Person("Markus");
     Person p2 = new Person("Jens");
     System.out.println(p1.greet());
     System.out.println(p2.greet());
   }

}
