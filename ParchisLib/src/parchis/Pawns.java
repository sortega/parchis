package parchis;

import java.util.*;
import java.util.Map.Entry;
import static parchis.Cell.*;

/**
 *
 * @author sortega
 */
public class Pawns {
    public static final int MAX_PAWNS = 4;


    /**
     * Initial empty board
     */
    public Pawns() {
        this.pawns = new EnumMap<Color, List<Cell>>(Color.class);
        for (Color player: Color.ALL_PLAYERS) {
            this.pawns.put(player, Collections.EMPTY_LIST);
        }
    }

    
    public Pawns(Map<Color, List<Cell>> pawns) {
        this.pawns = pawns;
    }

    /**
     * Lists all the pawns contained in a cell.
     *
     * @param cell
     * @return List of players (repeated allowed)
     */
    public List<Color> getPawnsIn(Cell cell) {
        List<Color> result = new LinkedList<Color>();
        for (Entry<Color, List<Cell>> entry: this.pawns.entrySet()) {
            for (Cell pawnPosition: entry.getValue()) {
                if (pawnPosition.equals(cell)) {
                    result.add(entry.getKey());
                }
            }
        }
        return result;
    }

    public List<Cell> getPlayerPawns(Color player) {
        return Collections.unmodifiableList(pawns.get(player));
    }

    public int getPlayerPawnCount(Color color) {
        return pawns.get(color).size();
    }

    public boolean isFinished() {
        for (Color player : Color.values()) {
            if (hasWon(player)) {
                return true;
            }
        }
        return false;
    }

    public Color getWinner() {
        for (Color player : Color.values()) {
            if (hasWon(player)) {
                return player;
            }
        }
        return null;
    }

    public boolean hasWon(Color player) {
        int finals = 0;
        for (Cell position: pawns.get(player)) {
            if (position.isGoal()) {
                finals++;
            }
        }

        return finals == MAX_PAWNS;
    }
    
    /**
     * Retuns the list of bridges.
     *
     * A bridge is a pair of same-colored pawns in a shelter position.
     * @return
     */
    public Set<Cell> getBridges() {
        Set<Cell> bridges = new HashSet<Cell>();

        for (List<Cell> playerPawns: pawns.values()) {
            Map<Cell, Integer> count = countByCell(playerPawns);

            for (Entry<Cell, Integer> countEntry: count.entrySet()) {
                if (countEntry.getValue() == 2 && countEntry.getKey().isShelter()) {
                    bridges.add(countEntry.getKey());
                }
            }
        }

        return bridges;
    }

    public List<Color> getCapturablePawns(Color player, Cell cell) {
        List<Color> capturablePawns = new LinkedList<Color>();

        if (!cell.isShelter() && cell.getColor() == null) {
            for (Color pawn: getPawnsIn(cell)) {
                if (!player.equals(pawn)) {
                    capturablePawns.add(pawn);
                }
            }
        }

        return capturablePawns;
    }

    
    public boolean canCreatePawn(Color player) {
        return (this.getPlayerPawnCount(player) < MAX_PAWNS) &&
                (this.getPawnsIn(PLAYER_HOMES.get(player)).size() < 2);
    }


    public Pawns addPawns(Color player, Cell... cells) {
        Map<Color, List<Cell>> newPawns = new EnumMap<Color, List<Cell>>(Color.class);
        newPawns.putAll(pawns);

        List<Cell> playerPawns = new LinkedList<Cell>(pawns.get(player));
        playerPawns.addAll(Arrays.asList(cells));
        newPawns.put(player, playerPawns);

        return new Pawns(newPawns);
    }


    public Pawns removePawns(Color player, Cell... cells) {
        Map<Color, List<Cell>> newPawns = new EnumMap<Color, List<Cell>>(Color.class);
        newPawns.putAll(pawns);

        List<Cell> playerPawns = new LinkedList<Cell>(pawns.get(player));
        for (Cell cellToRemove: cells) {
            playerPawns.remove(cellToRemove);
        }
        newPawns.put(player, playerPawns);

        return new Pawns(newPawns);
    }

    public Pawns createPawn(Color player) {
        return this.addPawns(player, PLAYER_HOMES.get(player));
    }


    public static class MoveResult {
        private MoveResult(Pawns pawns, Cell destination, Integer bonus) {
            this.pawns = pawns;
            this.destination = destination;
            this.bonus = bonus;
        }

        public Integer getBonus() {
            return bonus;
        }

        public Cell getDestination() {
            return destination;
        }
        

        public Pawns getPawns() {
            return pawns;
        }

        private final Pawns pawns;
        private final Cell destination;
        private final Integer bonus;
    }

    
    public MoveResult move(Color player, Cell pawn, int advances) {
        Pawns newPawns = this;
        Cell destination = pawn.nextCell(player, advances);
        Integer bonus = null;

        if (destination.isGoal()) {
            // Goal bonus
            bonus = 10;
            
        } else if (!destination.isShelter() && destination.getColor() == null) {
            // Kill bonus

            List<Color> pawnsInDestintation = getPawnsIn(destination);

            if (pawnsInDestintation.size() > 0) {
                for (Color otherPlayer: pawnsInDestintation) {
                    if (otherPlayer != player) {
                        newPawns = newPawns.removePawns(otherPlayer, destination);
                        bonus = ((bonus != null)? bonus : 0) + 20;
                    }
                }
            }
        }

        // Advance the pawn
        newPawns = newPawns.removePawns(player, pawn).addPawns(player, destination);
        
        return new MoveResult(newPawns, destination, bonus);
    }

 
    @Override
    public String toString() {
        return this.pawns.toString();
    }

    private final Map<Color, List<Cell>> pawns;

    private Map<Cell, Integer> countByCell(List<Cell> positions) {
        Map<Cell, Integer> count = new HashMap<Cell, Integer>();
        for (Cell pos: positions) {
            Integer positionCount = count.get(pos);
            count.put(pos, (positionCount != null)? (positionCount + 1) : 1);
        }
        return count;
    }

}
