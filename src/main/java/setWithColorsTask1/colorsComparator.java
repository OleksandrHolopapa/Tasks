package setWithColorsTask1;

import java.util.Comparator;

public class colorsComparator implements Comparator {
    private static final String COLORS_ORDER = "ЧОЖЗБСФ";
    @Override
    public int compare(Object o1, Object o2) {
        Character color1 = (Character) o1;
        Character color2 = (Character) o2;
        int positionOfColor1 = COLORS_ORDER.indexOf(color1);
        int positionOfColor2 = COLORS_ORDER.indexOf(color2);
        return positionOfColor1 - positionOfColor2;
    }
}
