package parchis;

/**
 *
 * @author sortega
 */
public class Pawn {
    public Pawn(Color color, Cell cell) {
        this.color = color;
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pawn other = (Pawn) obj;
        if (this.color != other.color) {
            return false;
        }
        if (this.cell != other.cell && (this.cell == null || !this.cell.equals(other.cell))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.color.hashCode();
        hash = 79 * hash + (this.cell != null ? this.cell.hashCode() : 0);
        return hash;
    }

    private final Color color;
    private final Cell cell;
}
