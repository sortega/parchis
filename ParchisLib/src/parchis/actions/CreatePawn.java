package parchis.actions;

import org.jdesktop.application.ResourceMap;

/**
 *
 * @author sortega
 */
public class CreatePawn implements Action {

    @Override
    public String toString() {
        return "(New pawn)";
    }

    @Override
    public String toLocalizedString(ResourceMap resourceMap) {
        return resourceMap.getString("Game.Actions.CreatePawn");
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return 7;
    }
}
