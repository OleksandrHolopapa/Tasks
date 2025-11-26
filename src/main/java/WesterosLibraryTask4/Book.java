package WesterosLibraryTask4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private int year;
    private Epoch epoch;

    @Override
    public String toString() {
        return "\""+title+"\"" + ", by " + author + ", published in " + year+" "+epoch;
    }

    /** AC means "After the Conquest" (of Aegon I Targaryen), the standard calendar in Westeros.
     BC means "Before the Conquest".**/
    public enum Epoch {
        AC, BC
    }
}