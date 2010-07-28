package parchis.actions;

import parchis.*;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author sortega
 */
public class Suicide implements Action {

    public Suicide(Cell pawn) {
        this.pawn = pawn;
    }

    public Cell getPawn() {
        return pawn;
    }

    @Override
    public String toString() {
        return String.format("(Suicide %s)", pawn.toString());
    }

    @Override
    public String toLocalizedString(ResourceMap resourceMap) {
        return resourceMap.getString("Game.Actions.Suicide", this.pawn);
    }
    
    /** Pawn to suicide identified by its current position. */
    private final Cell pawn;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suicide other = (Suicide) obj;
        if (this.pawn != other.pawn && (this.pawn == null || !this.pawn.equals(other.pawn))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.pawn != null ? this.pawn.hashCode() : 0);
        return hash;
    }
}
