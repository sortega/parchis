package parchis;

import java.util.*;
import parchis.actions.*;

/**
 *
 * @author sortega
 */
@Deprecated
public class Choice {
    Choice(Game game, Integer roll, Integer bonus) {
        assert (roll == null || bonus == null) && roll != bonus;
        this.game = game;
        this.roll = roll;
        this.bonus = bonus;

        this.actions = new HashSet<Action>();

        if (bonus == null && game.getTurn().isSuicide(roll)) {
            Pawn pawn = game.getLastPlayedPawn();
            if (pawn != null && pawn.getColor() == game.getCurrentPlayer()) {
                actions.add(new Suicide(pawn.getCell()));
            }

        } else if (bonus == null && roll != null && roll == 5 &&
                game.getPawns().canCreatePawn(game.getCurrentPlayer())) {
            actions.add(new CreatePawn());

        } else {
            int advances = getAdvances();
            Set<Cell> allMovablePawns = getMovablePawns(advances);
            Set<Cell> breakingBridgesPawns = filterBreakingBridgePawns(allMovablePawns);
            Set<Cell> possibleMove;

            if (roll != null && roll == 6 && breakingBridgesPawns.size() > 0) {
                possibleMove = breakingBridgesPawns;
            } else {
                possibleMove = allMovablePawns;
            }

            for (Cell pawn: possibleMove) {
                actions.add(new Move(pawn, advances));
            }
        }

        if (actions.isEmpty()) {
            actions.add(new Pass());
        }

    }

    public Set<Action> getActions() {
        return this.actions;
    }

    public Game choose(Action action) {
        if (actions.contains(action)) {
            return game.play(action, roll);
        } else {
            throw new IllegalArgumentException("Illegal action");
        }
    }

    private final Game game;
    private final Integer roll;
    private final Integer bonus;
    private final Set<Action> actions;


    public int getAdvances() {
        if (bonus != null) {
            return bonus;
        } else if (roll == 6 && game.getPawns().getPlayerPawnCount(game.getCurrentPlayer()) == Pawns.MAX_PAWNS) {
            return 7;
        } else {
            return roll;
        }
    }


    private Set<Cell> getMovablePawns(int length) {
        Set<Cell> bridges = game.getPawns().getBridges();
        Set<Cell> moves = new HashSet<Cell>();

        PAWN:
        for (Cell pawn : game.getPawns().getPlayerPawns(game.getCurrentPlayer())) {
            int positionsAhead = length;
            Cell posToCheck = pawn;

            while (positionsAhead > 0) {
                posToCheck = posToCheck.nextCell(game.getCurrentPlayer());

                if (posToCheck == null || bridges.contains(posToCheck)) {
                    // Blocked by a bridge
                    continue PAWN;
                }
                positionsAhead--;
            }

            Cell destination = posToCheck;
            List<Color> pawnsInDestination = game.getPawns().getPawnsIn(destination);

            // There is room or we can capture (not a shelter and capturable pawns)
            if ((pawnsInDestination.size() < 2 || destination.isGoal()) ||
                    (!destination.isShelter() && game.getPawns().getCapturablePawns(game.getCurrentPlayer(), destination).size() > 0)) {
                moves.add(pawn);
            }

        }

        return moves;
    }


    private Set<Cell> filterBreakingBridgePawns(Set<Cell> positions) {
        Set<Cell> breakingBridgeMoves = new HashSet<Cell>();
        Set<Cell> bridges = game.getPawns().getBridges();

        for (Cell pos : positions) {
            if (bridges.contains(pos)) {
                breakingBridgeMoves.add(pos);
            }
        }

        return breakingBridgeMoves;
    }

}
