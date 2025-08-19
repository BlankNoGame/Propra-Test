package termine;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.List;

public class TerminVerwaltung {


  private Multimap<String, String> termine = ArrayListMultimap.create();


    public void addTermin(String datum, String username) {
        termine.put(datum, username);
    }

    public List<String> getTermineAm(String datum) {
        return new ArrayList<>(termine.get(datum));
    }





}
