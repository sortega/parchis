package parchis.ai;

import java.util.Random;
import java.util.Set;
import parchis.actions.Action;
import parchis.Game;

/**
 *
 * @author sortega
 */
public class RandomPlayer implements Player {

    public RandomPlayer() {
        this.generator = new Random();
    }

    @Override
    public Action chooseAction(Game game, Set<Action> actions) {
        int chosenIndex = generator.nextInt(actions.size());
        return (Action) actions.toArray()[chosenIndex];
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName();
    }

    private final Random generator;
}
