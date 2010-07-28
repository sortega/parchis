package parchis.exp;

import parchis.dices.Dice;
import parchis.dices.SeedDice;

/**
 *
 * @author sortega
 */
public class TurnsToCreatePawn {
    public static final int MAX_SIMULATIONS = 1000;

    public static int turnsToCreatePawn(Dice dice) {
        int turns = 1;
        while(!turn(dice)) {
            turns++;
        }
        return turns;
    }
    
    public static boolean turn(Dice dice) {
        for (int rollNumber=0; rollNumber<3; rollNumber++) {
            int roll = dice.roll();
            switch (roll) {
                case 5:
                    return true;
                    
                case 6:
                    continue;
                    
                default:
                    break;
            }
        }
        return false;
    }

    private static void averageTurnsToCreatePawn(Dice dice) {
        int turns = 0;
        for (int i = 0; i < MAX_SIMULATIONS; i++) {
            turns += turnsToCreatePawn(dice);
        }
        float averageTurns = ((float) turns) / MAX_SIMULATIONS;
        System.out.format("%f turns on average\n", averageTurns);
    }

    public static void main(String[] args) {
        Dice dice = new SeedDice();

        averageTurnsToCreatePawn( dice);
    }
}
