package de.hhu.propra.streams;


import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConfigParser {
    private static final String HORIZONTAL_RULER = "-".repeat(80);

    private static final class ConfigEntry {
        private final String key;
        private final String value;

        ConfigEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String key() {
            return key;
        }

        public String value() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (ConfigEntry) obj;
            return Objects.equals(this.key, that.key) &&
                    Objects.equals(this.value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "ConfigEntry[" +
                    "key=" + key + ", " +
                    "value=" + value + ']';
        }
    }

    private static ConfigEntry parseConfigLine(String line){
        var keyValue = line.split(":");
        return new ConfigEntry(keyValue[0].trim(), keyValue[1].trim());
    }

    private static void addConfigEntry(Map<String,String> map, String line){
        //Das hier zu implementieren könnte helfen ...
    }
    public static void main(String[] args) {
        List<String> config = loadConfig(); // liest alle Zeilen der Datei in eine Liste ein
        Map<String,String> configMap = config.stream().collect(
                // TODO: collect implementieren
                null, // Supplier: erstellt eine leere, veränderliche Map<String,String>
                null, // Accumulator: bekommt eine Map<String,String> und eine Zeile aus der Datei und fügt die Infos aus der Zeile in die Map ein
                null  // Combiner: bekommt zwei Map<String,String> und bildet die Vereinigung (Tipp: Es gibt eine passende Methode im Map-Interface.)
            );
        System.out.format("Read config. Result:%n%s%n%n%s%n%n%s", HORIZONTAL_RULER, configMap, HORIZONTAL_RULER);
    }

    private static List<String> loadConfig() {
        // liest man normalerweise aus einer Datei, ums für jetzt aber einfacher zu machen, schreiben wir den Dateiinhalt direkt hier in den Code.
        // List<String> config = Files.readAllLines(Paths.get("my_config.yml")); // liest alle Zeilen der Datei in eine Liste ein
        // Mit """ können wir mehrzeilige Strings, die Zeilenumbrüche enthalten, erstellen (sog. Text Block). Schauen
        // wir uns auf einem späteren Wochenblatt noch an.
        return """
                hostname: propra.bendisposto.de
                message_of_the_day: uebung 3 ist toll :)
                magic_number_of_the_day: 42
                tage_bis_zur_klausur: 96
                panik_level: chill
                """.lines().toList();
    }
}
