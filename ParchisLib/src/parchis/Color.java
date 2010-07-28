package parchis;

import java.util.*;
import util.Localizable;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author sortega
 */
public enum Color implements Localizable {
    yellow, blue, red, green;

    public static final Set<Color> ALL_PLAYERS = 
            new HashSet<Color>(Arrays.asList(Color.values()));

    @Override
    public String toLocalizedString(ResourceMap resourceMap) {
        return resourceMap.getString("PlayerColor." + this.toString());
    }
}
