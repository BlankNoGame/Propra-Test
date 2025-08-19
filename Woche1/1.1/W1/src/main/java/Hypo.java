import java.util.Scanner;
import java.lang.Math;

import static java.lang.Math.sqrt;
import static java.lang.Math.acos;

public class Hypo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double gegenkathete =  scanner.nextDouble();
        double ankathete    =  scanner.nextDouble();

        double hypotheneuse = sqrt((gegenkathete*gegenkathete) + (ankathete * ankathete));
        double winkel = acos(ankathete/hypotheneuse);

        System.out.println("Hypotheuse: " + hypotheneuse + " Winkel im Bogenmaß: " + winkel);


    }

}
