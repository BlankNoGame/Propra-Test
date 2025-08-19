import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private final Map<String, Double> categoryTotals = new HashMap<>();
    private final Map<String, Double> shopTotals = new HashMap<>();

    private final Scanner stdIn = new Scanner(System.in);

    public void exit() {
        System.out.println("Bye");
        stdIn.close();
    }

    private void add(String[] parts) {
        if(parts.length != 4) {
            invalid();
        }

        String shop = parts[1];
        String category = parts[2];
        double preis = Double.parseDouble(parts[3]);

        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + preis);
        shopTotals.put(shop, shopTotals.getOrDefault(shop, 0.0) + preis);

        System.out.printf("Hinzugefügt zum Shop %s in der Kategorie %s: %.2f%n", shop, category, preis);

    }

    private void invalid() {
        System.out.println("Ungültige Eingabe.");
    }


    private void report(String[] parts) {
        if (parts.length != 2) {
            invalid();
            return;
        }

        switch (parts[1]) {
            case "category":
                categoryTotals.forEach((category, total) -> System.out.printf("%s: %.2f%n", category, total));
                break;

            case "shop":
                shopTotals.forEach((shop, total) -> System.out.printf("%s: %.2f%n", shop, total));
                break;
            default:
                invalid();
        }
    }


    public void run() {
        System.out.println("Ausgabenbuch ist bereit für die Eingaben");
        boolean running = true;
        while (running) {
            running = readAndProcess();
        }
    }


    private boolean readAndProcess() {
        String line;
        line = stdIn.nextLine();
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "add": {
                add(parts);
                return true;
            }
            case "report": {
                report(parts);
                return true;
            }
            case "exit": {
                exit();
                return false;
            }
            default: {
                invalid();
                return true;
            }
        }
    }

    public static void main(String[] args) {
        Main ausgabenbuch = new Main();
        ausgabenbuch.run();
    }


}
