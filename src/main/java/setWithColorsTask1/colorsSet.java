package setWithColorsTask1;

import java.util.Set;
import java.util.TreeSet;

public class colorsSet {
    public static void main(String[] args) {
        Set<Character> colors = new TreeSet<>(new colorsComparator());
        colors.add('С');
        colors.add('Ч');
        colors.add('Б');
        colors.add('О');
        colors.add('Ф');
        colors.add('З');
        colors.add('Ж');

        System.out.println(colors);
    }
}
