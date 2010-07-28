package parchis;

import java.util.EnumMap;
import java.util.Map;
import org.jdesktop.application.ResourceMap;
import util.Localizable;
import static parchis.Color.*;

/**
 *
 * @author sortega
 */
public class Cell implements Localizable {
    public static final int MAIN_PATH_LENGTH = 68;
    public static final int STAIRCASE_LENGTH = 8;
    public static final Map<Color, Cell> PLAYER_HOMES =
            new EnumMap<Color, Cell>(Color.class) {{
                put(yellow, new Cell(5));
                put(blue,   new Cell(22));
                put(red,    new Cell(39));
                put(green,  new Cell(56));
            }};
    public static final Cell[] ALL_CELLS = new Cell[MAIN_PATH_LENGTH +
            4 * STAIRCASE_LENGTH];
    static {
        for (int number=0; number < MAIN_PATH_LENGTH; number++) {
            ALL_CELLS[number] = C(number + 1);
        }
        for (int number=0; number < STAIRCASE_LENGTH; number++) {
            ALL_CELLS[MAIN_PATH_LENGTH + number] = C(yellow, number + 1);
            ALL_CELLS[MAIN_PATH_LENGTH + STAIRCASE_LENGTH + number] =
                    C(blue, number + 1);
            ALL_CELLS[MAIN_PATH_LENGTH + 2 * STAIRCASE_LENGTH + number] =
                    C(red, number + 1);
            ALL_CELLS[MAIN_PATH_LENGTH + 3 * STAIRCASE_LENGTH + number] =
                    C(green, number + 1);
        }
    }

    public Cell(Color color, int pos) {
        this.color = color;
        this.pos = pos;
    }

    public Cell(int pos) {
        this(null, pos);
    }

    public static Cell C(int pos) {
        return new Cell(pos);
    }

    public static Cell C(Color color, int pos) {
        return new Cell(color, pos);
    }

    public Color getColor() {
        return this.color;
    }


    public int getNumber() {
        return this.pos;
    }

    public boolean isShelter() {
        if (!this.isStair()) {
            int translatedPos = getEquivPos();
            return translatedPos == 5 || translatedPos == 12 || translatedPos == 17;
        } else {
            return false;
        }
    }

    public boolean isHome() {
        return (!this.isStair()) && (getEquivPos() == 5);
    }

    public boolean isStair() {
        return this.color != null;
    }

    public boolean isGoal() {
        return this.isStair() && pos == STAIRCASE_LENGTH;
    }

    /**
     * Next Cell (null for the last Cell).
     * @param playerColor
     * @return
     */
    public Cell nextCell(Color playerColor) {

        if (this.isStair()) {
            if (this.isGoal()) {
                return null;
            } else {
                return C(playerColor, this.pos + 1);
            }

        } else {
            if (PLAYER_PATH_ENTRANCES.get(playerColor).equals(this)) {
                return C(playerColor, 1);
            } else {
                return C(this.pos % MAIN_PATH_LENGTH + 1);
            }
        } 
    }

    public Cell nextCell(Color player, int length) {
        Cell cell = this;

        while (length > 0 && cell != null) {
            length--;
            cell = cell.nextCell(player);
        }

        return cell;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.color != other.color || 
                (this.color != null && !this.color.equals(other.color))) {
            return false;
        }
        if (this.pos != other.pos) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (this.color != null) {
            hash = 19 * hash + this.color.hashCode();
        }
        hash = 19 * hash + this.pos;
        return hash;
    }

    @Override
    public String toString() {
        if (color == null) {
            return String.format("<%d>", pos);
        } else {
            return String.format("<%s, %d>", color, pos);
        }
    }

    @Override
    public String toLocalizedString(ResourceMap resourceMap) {
        if (color == null) {
            return Integer.toString(pos);
        } else {
            return String.format("%s %d", color.toLocalizedString(resourceMap), pos);
        }
    }


    /**
     * Cells which are the start of the private path for each player.
     */
    private static final Map<Color, Cell> PLAYER_PATH_ENTRANCES =
            new EnumMap<Color, Cell>(Color.class) {{
                    put(yellow, C(68));
                    put(blue, C(17));
                    put(red, C(34));
                    put(green, C(51));
                }};

    /**
     * There are 5 numbered paths:
     * <ul>
     *    <li><code>null</code>. Regular path, range [1, 68]
     *    <li>Player color. Private path, range [1, 8]
     * </ul>
     */
    private Color color;

    /** Numeric Cell */
    private int pos;

    private int getEquivPos() {
        return (pos - 1) % 17 + 1;
    }
}
