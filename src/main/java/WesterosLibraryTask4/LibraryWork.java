package WesterosLibraryTask4;

import static WesterosLibraryTask4.Book.Epoch.*;

public class LibraryWork {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("The Crimson Book", "Archmaester Vailem", 125, AC, "Religion & Philosophy Section");
        library.addBook("The White Book", "Various Scribes", 10, AC, "Archives, Restricted Access");
        library.addBook("The Life and Adventures of Elio Gryvas, First Sword of Braavos", "Elio Gryvas", 275, AC, "Arts of War Section, Braavos");
        library.addBook("The Lives of Four Kings", "Maester Cullen", 190, AC, "Realms & Chronicles Section, Targaryen History");
        library.addBook("The History of the Great and Small Houses", "Archmaester Perrin", 85, AC, "Realms & Chronicles Section, Genealogy");
        library.addBook("The History of House Lannister", "Septon Barth", 60, AC, "Realms & Chronicles Section, The Westerlands");
        library.addBook("The Death of Dragons", "Archmaester Vayn", 145, AC, "Natural History & Arcane Studies Section");
        library.addBook("Ten Thousand Ships", "Princess Nymeria", 1000, BC, "Voyages & Geography Section, Dornish History");
        library.addBook("The Sea Snake, His Voyages", "Lord Corlys Velaryon", 128, AC, "Voyages & Geography Section, Maritime History");
        library.addBook("Wonders", "Septon Meribald", 290, AC, "Natural History & Arcane Studies Section, Curiosities");
        library.addBook("Black Wings, Quick Words", "Maester Galleon", 215, AC, "Maesterly Arts Section, Logistics");

        System.out.println("Try to add the book that already exists: ");
        boolean tryToAdd = library.addBook("The Crimson Book", "Archmaester Vailem", 125, AC, "Religion & Philosophy Section");
        System.out.println(tryToAdd);

        System.out.println("Try to remove the book that doesn't exist: ");
        boolean tryToRemove = library.removeBook("The Great White Book ", "Various Scribes", 10, AC);
        System.out.println(tryToRemove);

        System.out.println("Get the book location: ");
        System.out.println(library.getBookLocation("The Life and Adventures of Elio Gryvas, First Sword of Braavos", "Elio Gryvas", 275, AC));

        System.out.println("Print all books: ");
        library.printAllBooks();
    }
}