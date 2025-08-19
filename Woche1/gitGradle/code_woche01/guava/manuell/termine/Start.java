package termine;

public class Start {

  public static void main(String[] args) {

    TerminVerwaltung terminVerwaltung = new TerminVerwaltung();
    terminVerwaltung.addTermin("16.04.2024", "@bendisposto");
    terminVerwaltung.addTermin("16.04.2024", "@mabre");


    terminVerwaltung
        .getTermineAm("16.04.2024")
        .forEach(p -> System.out.println(p));


  }

}
