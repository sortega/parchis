package parchis;

import java.util.*;
import parchis.Pawns.MoveResult;
import static parchis.Color.*;
import parchis.actions.*;
import parchis.dices.Dice;

/**
 *
 * @author sortega
 */
public class Game {

    public Game(Set<Color> players, Color firstPlayer) {
        this(new RollTurn(sortPlayers(players), firstPlayer), new Pawns());
    }

    public Game() {
        this(ALL_PLAYERS, yellow);
    }

    public Game(Turn turn, Pawns pawns) {
        this.turn = turn;
        this.pawns = pawns;
        this.lastPlayedPawn = null;
    }

    public Pawns getPawns() {
        return this.pawns;
    }

    public Color getCurrentPlayer() {
        return this.turn.getPlayer();
    }

    public Choice getChoice(Dice dice) {
        if (turn.mustRoll()) {
            return new Choice(this, dice.roll(), null);
        } else {
            return new Choice(this, null, turn.getBonus());
        }
    }

    public Pawn getLastPlayedPawn() {
        return this.lastPlayedPawn;
    }

    public boolean isFinished() {
        return pawns.isFinished();
    }

    public Color getWinner() {
        return pawns.getWinner();
    }

    //////////////////////////////////////////

    private final Turn turn;
    private final Pawns pawns;
    private final Pawn lastPlayedPawn;



    Game(Turn turn, Pawns pawns, Pawn lastPlayedPawn) {
        this.turn = turn;
        this.pawns = pawns;
        this.lastPlayedPawn = lastPlayedPawn;
    }


    Turn getTurn() {
        return this.turn;
    }

    Game play(Action action, Integer roll) {
        Turn nextTurn;
        Pawns nextPawns;
        Pawn playedPawn;
        
        
        if (action instanceof Move) {
            Move move = (Move) action;

            MoveResult result = pawns.move(getCurrentPlayer(),
                    move.getPawn(), move.getAdvances());
            
            nextTurn = turn.move(roll, result.getBonus());
            nextPawns = result.getPawns();
            playedPawn = new Pawn(getCurrentPlayer(), result.getDestination());

        } else if (action instanceof Suicide) {
            Suicide suicide = (Suicide) action;

            nextTurn = turn.suicide();
            nextPawns = pawns.removePawns(getCurrentPlayer(), suicide.getPawn());
            playedPawn = null;

        } else if (action instanceof Pass) {
            nextTurn = turn.pass(roll);
            nextPawns = pawns;
            playedPawn = lastPlayedPawn;

        } else if (action instanceof CreatePawn) {
            nextTurn = turn.createPawn();
            nextPawns = pawns.createPawn(getCurrentPlayer());
            playedPawn = null;

        } else {
            throw new IllegalArgumentException("Unknown movement.");
        }
        
        if (nextTurn.getPlayer() != turn.getPlayer()) {
            playedPawn = null;
        }
        
        return new Game(nextTurn, nextPawns, playedPawn);
    }

    
    private static Color[] sortPlayers(Set<Color> players) {
        List<Color> sortedPlayers = new ArrayList<Color>(players);
        Collections.sort(sortedPlayers);
        Color[] sortedPlayerArray = new Color[players.size()];
        return sortedPlayers.toArray(sortedPlayerArray);
    }

}
