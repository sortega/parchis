package parchis.util;

import java.util.Collection;
import javax.swing.DefaultListModel;
import util.Localizable;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author sortega
 */
public class LocalizedListModel extends DefaultListModel {

    public LocalizedListModel(ResourceMap map, Collection<? extends Localizable> items) {
        for (Localizable item: items) {
            addElement(new LocalizedItem(map, item));
        }
    }
}
