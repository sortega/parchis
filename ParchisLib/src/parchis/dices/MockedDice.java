package parchis.dices;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sortega
 */
public class MockedDice implements Dice {
    public MockedDice(Integer... args) {
        this.rolls = new LinkedList<Integer>();

        for (Integer arg: args) {
            this.rolls.add(arg);
        }
    }

    @Override
    public int roll() {
        return rolls.remove(0);
    }

    private List<Integer> rolls;
}
