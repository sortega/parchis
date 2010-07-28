package parchis.actions;

import org.jdesktop.application.ResourceMap;
import parchis.Cell;

/**
 *
 * @author sortega
 */
public class Move implements Action {

    public Move(Cell pawn, int advances) {
        this.pawn = pawn;
        this.advances = advances;
    }

    public Cell getPawn() {
        return this.pawn;
    }

    public int getAdvances() {
        return this.advances;
    }

    @Override
    public String toString() {
        return String.format("(Move %s)", pawn);
    }

    @Override
    public String toLocalizedString(ResourceMap resourceMap) {
        return resourceMap.getString("Game.Actions.Move", advances, 
                pawn.toLocalizedString(resourceMap));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        if (this.pawn != other.pawn && (this.pawn == null || !this.pawn.equals(other.pawn))) {
            return false;
        }
        if (this.advances != other.advances) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.pawn != null ? this.pawn.hashCode() : 0);
        hash = 79 * hash + this.advances;
        return hash;
    }


    private final Cell pawn;
    private final int advances;

}
