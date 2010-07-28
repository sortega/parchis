package parchis;

/**
 *
 * @author sortega
 */
@Deprecated
class BonusTurn implements Turn {

    public BonusTurn(int bonus, Turn currentTurn, Turn nextTurn) {
        this.bonus = bonus;
        this.currentTurn = currentTurn;
        this.nextTurn = nextTurn;
    }

    @Override
    public Color getPlayer() {
        return currentTurn.getPlayer();
    }

    @Override
    public Integer getBonus() {
        return bonus;
    }

    @Override
    public boolean isSuicide(int newRoll) {
        throw new UnsupportedOperationException("Not allowed.");
    }

    @Override
    public boolean mustRoll() {
        return false;
    }

    @Override
    public Turn move(Integer roll, Integer newBonus) {
        assert roll == null;
        return nextTurn;
    }

    @Override
    public Turn pass(Integer roll) {
        assert roll == null;
        return nextTurn;
    }

    @Override
    public Turn suicide() {
        throw new UnsupportedOperationException("Not allowed.");
    }

    @Override
    public Turn createPawn() {
        throw new UnsupportedOperationException("Not allowed.");
    }

    private final int bonus;
    private final Turn currentTurn;
    private final Turn nextTurn;
}
