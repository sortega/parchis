package parchis.dices;

import java.util.Random;

/**
 *
 * @author sortega
 */
public class SeedDice implements Dice {

    public SeedDice(long seed) {
        this.generator = new Random(seed);
        this.use_count = 0;
        this.seed = seed;
    }

    public SeedDice() {
        this.generator = new Random();
        this.use_count = 0;
        this.seed = null;
    }

    @Override
    public int roll() {
        use_count++;
        return generator.nextInt(6) + 1;
    }

    @Override
    public String toString() {
        return String.format("<Dice(seed=%d,use_count=%d>", seed, use_count);
    }

    private final Random generator;
    private int use_count;
    private final Long seed;
}
