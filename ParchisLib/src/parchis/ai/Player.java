package parchis.ai;

import parchis.actions.Action;
import java.util.Set;
import parchis.Game;

/**
 *
 * @author sortega
 */
public interface Player {
    public Action chooseAction(Game game, Set<Action> actions);
}
