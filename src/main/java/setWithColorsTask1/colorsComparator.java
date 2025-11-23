package setWithColorsTask1;

import java.util.Comparator;

public class colorsComparator implements Comparator<Character> {
    private static final String COLORS_ORDER = "ЧОЖЗБСФ";
    @Override
    public int compare(Character color1, Character color2) {
        int positionOfColor1 = COLORS_ORDER.indexOf(color1);
        int positionOfColor2 = COLORS_ORDER.indexOf(color2);
        return positionOfColor1 - positionOfColor2;
    }
}
