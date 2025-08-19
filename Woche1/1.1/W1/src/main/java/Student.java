import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Student {
    private final String matrikelnr;
    private final String name;

    public Student(String matrikelnr, String name) {
        this.matrikelnr = matrikelnr;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student (Matrikelnr: %s, Name: %s)".formatted(matrikelnr, name);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Student student)) { // neue Pattern-Matching-Syntax für instanceof:
            // Wenn das andere Objekt keine Student-Instanz ist:
            return false;
        }
        // Andernfalls ist hier student eine Referenz auf o vom Typ Student:

        return Objects.equals(matrikelnr, student.matrikelnr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(matrikelnr);
    }

    public static void main(String[] args) {
        Student s1 = new Student("987654321", "Markus Müller");
        Student s2 = new Student("987654321", "Someone Else");
        System.out.println(s1.equals(s2));
        Set<Student> studis = new HashSet<>();
        studis.add(s1);
        studis.add(s2);
        System.out.println(studis.size());
    }
}