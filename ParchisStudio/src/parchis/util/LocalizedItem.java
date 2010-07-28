package parchis.util;

import util.Localizable;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author sortega
 */
public class LocalizedItem {
    public LocalizedItem(ResourceMap resourceMap, Localizable item) {
        this.resourceMap = resourceMap;
        this.item = item;
    }

    @Override
    public String toString() {
        return item.toLocalizedString(resourceMap);
    }

    private final ResourceMap resourceMap;
    private final Localizable item;
}
