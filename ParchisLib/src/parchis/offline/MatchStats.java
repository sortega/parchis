package parchis.offline;

import parchis.Color;

/**
 *
 * @author sortega
 */
public class MatchStats {
    public MatchStats() {
        this.diceRolls = 0;
        this.winner = null;
    }

    public void addRoll() {
        this.diceRolls++;
    }

    public int getDiceRolls() {
        return diceRolls;
    }

    public void setWinner(Color player) {
        this.winner = player;
    }

    public Color getWinner() {
        return this.winner;
    }

    @Override
    public String toString() {
        return String.format("<%s wins, %d dice rolls>", this.winner, this.diceRolls);
    }

    private int diceRolls;
    private Color winner;
}
