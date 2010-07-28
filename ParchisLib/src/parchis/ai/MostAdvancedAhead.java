package parchis.ai;

import java.util.Set;
import parchis.*;
import parchis.actions.*;

/**
 *
 * @author sortega
 */
public class MostAdvancedAhead implements Player {

    @Override
    public Action chooseAction(Game game, Set<Action> actions) {
        Action chosenAction = actions.iterator().next();
        int furthestPos = 0;

        for (Action action: actions) {

            if (action instanceof Move) {
                Move move = (Move) action;
                int pos = equivNumber(game.getCurrentPlayer(), move.getPawn());

                if (pos > furthestPos) {
                    furthestPos = pos;
                    chosenAction = move;
                }
            }
        }

        return chosenAction;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName();
    }

    private int equivNumber(Color player, Cell cell) {
        if (cell.getColor() != null) {
            if (cell.getColor() == player) {
                return cell.getNumber() + 68;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            int equiv = (cell.getNumber() - 1 - 17 * player.ordinal()) % 68 + 1;
            if (equiv < 0) {
                return equiv + 68;
            } else {
                return equiv;
            }
        }
    }
}
