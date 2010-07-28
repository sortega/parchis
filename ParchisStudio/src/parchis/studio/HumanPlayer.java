package parchis.studio;

import java.util.Set;
import parchis.Game;
import parchis.actions.Action;
import parchis.ai.Player;

/**
 *
 * @author sortega
 */
public class HumanPlayer implements Player {

    @Override
    public Action chooseAction(Game game, Set<Action> actions) {
        return null;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName();
    }
}
