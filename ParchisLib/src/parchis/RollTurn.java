package parchis;

import java.util.Arrays;
import static parchis.Color.*;

/**
 *
 * @author sortega
 */
public class RollTurn implements Turn {

    public RollTurn() {
        this(yellow);
    }

    public RollTurn(Color player) {
        this(Color.values(), player);
    }

    public RollTurn(Color[] players, Color player) {
        this.players = players;
        this.rollNumber = 1;
        this.player = player;
    }

    @Override
    public Color getPlayer() {
        return this.player;
    }

    @Override
    public boolean mustRoll() {
        return true;
    }

    @Override
    public Integer getBonus() {
        return null;
    }

    @Override
    public boolean isSuicide(int newRoll) {
        return (newRoll == 6) && rollNumber == 3;
    }

    @Override
    public Turn suicide() {
        return new RollTurn(getNextPlayer());
    }

    @Override
    public Turn createPawn() {
        return new RollTurn(getNextPlayer());
    }

    @Override
    public Turn move(Integer roll, Integer newBonus) {
        assert roll != null;

        Turn nextTurn;
        if (roll == 6) {
            assert rollNumber < 3;
            nextTurn = new RollTurn(players, player, rollNumber + 1);

        } else {
            nextTurn = new RollTurn(getNextPlayer());
        }

        if (newBonus != null) {
            return new BonusTurn(newBonus, this, nextTurn);

        } else {
            return nextTurn;
        }
    }

    @Override
    public RollTurn pass(Integer roll) {
        assert roll != null;

        if (roll == 6) {
            assert rollNumber < 2;
            return new RollTurn(players, player, rollNumber + 1);
        } else {
            return new RollTurn(getNextPlayer());
        }
    }

    private RollTurn(Color[] players, Color player, int rollNumber) {
        this.players = players;
        this.player = player;
        this.rollNumber = rollNumber;
    }

    private final Color[] players;
    private final Color player;
    private final int rollNumber;

    private Color getNextPlayer() {
        int index = Arrays.binarySearch(players, player);
        return players[(index + 1) % players.length];
    }
}
