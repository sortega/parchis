package parchis;

/**
 *
 * @author sortega
 */
public interface Turn {
    Color getPlayer();
    Integer getBonus();
    
    boolean isSuicide(int newRoll);
    boolean mustRoll();

    Turn move(Integer roll, Integer newBonus);
    Turn pass(Integer roll);
    Turn suicide();
    Turn createPawn();

}
