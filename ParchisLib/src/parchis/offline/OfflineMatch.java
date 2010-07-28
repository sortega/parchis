package parchis.offline;

import java.util.*;
import java.util.Map.Entry;
import parchis.dices.SeedDice;
import parchis.ai.Player;
import parchis.actions.Action;
import parchis.*;

/**
 *
 * @author sortega
 */
public class OfflineMatch implements Runnable {

    public OfflineMatch(Map<Color, Class<? extends Player>> playerClasses) {
        this.players = new EnumMap<Color, Player>(Color.class);
        for (Entry<Color, Class<? extends Player>> entry: playerClasses.entrySet()) {
            try {
                Color color = entry.getKey();
                Class<? extends Player> playerClass = entry.getValue();
                players.put(color, playerClass.getConstructor().newInstance());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        this.generator = new Random();
        this.stats = new MatchStats();
        this.dice = new SeedDice(generator.nextLong()) {

            @Override
            public int roll() {
                int r = super.roll();
                stats.addRoll();
                return r;
            }

        };
    }

    @Override
    public void run() {
        game = new Game(players.keySet(), chooseFirstPlayer());

        int action_count = 0;
        while(!game.isFinished()) {
            Choice choice = game.getChoice(dice);
            Set<Action> actions = choice.getActions();
            Action nextAction;
            if (actions.size() > 1) {
                Player currentPlayer = players.get(game.getCurrentPlayer());
                nextAction = currentPlayer.chooseAction(game, actions);
            } else {
                nextAction = actions.iterator().next();
            }

            game = choice.choose(nextAction);
            action_count++;
        }

        stats.setWinner(game.getWinner());
    }

    public Game getGame() {
        return this.game;
    }

    public MatchStats getStats() {
        return this.stats;
    }

    private final EnumMap<Color, Player> players;
    private final Random generator;
    private final SeedDice dice;
    private Game game;
    private final MatchStats stats;


    private Color chooseFirstPlayer() {
        int maxRoll = 0;
        Color firstPlayer = null;

        for (Color player: players.keySet()) {
            int roll = dice.roll();
            if (roll > maxRoll) {
                maxRoll = roll;
                firstPlayer = player;
            }
        }

        return firstPlayer;
    }
}
