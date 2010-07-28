package parchis.actions;

import org.jdesktop.application.ResourceMap;

/**
 *
 * @author sortega
 */
public class Pass implements Action {

    @Override
    public String toString() {
        return "(Pass)";
    }

    @Override
    public String toLocalizedString(ResourceMap resourceMap) {
        return resourceMap.getString("Game.Actions.Pass");
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
