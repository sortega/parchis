package parchis.offline;

import java.util.EnumMap;
import java.util.Map;
import parchis.Color;

/**
 *
 * @author sortega
 */
public class TournamentStats {
    public TournamentStats() {
        this.matches = 0;
        this.victories = new EnumMap<Color, Integer>(Color.class);
        for (Color player: Color.values()) {
            this.victories.put(player, 0);
        }
    }

    public void addMatch(MatchStats stats) {
        this.matches++;
        this.victories.put(stats.getWinner(), victories.get(stats.getWinner()) + 1);
    }

    public int getMatches() {
        return matches;
    }

    public Map<Color, Integer> getVictories() {
        return victories;
    }

    private int matches;
    private Map<Color, Integer> victories;
}
