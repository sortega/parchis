package parchis.offline;

import parchis.ai.Player;
import java.util.*;
import parchis.Color;
import parchis.ai.MostAdvancedAhead;
import parchis.ai.RandomPlayer;

/**
 *
 * @author sortega
 */
public class OfflineTournament implements Runnable {
    public OfflineTournament(Map<Color, Class<? extends Player>> playerClasses, int rounds) {
        this.stats = new TournamentStats();
        this.playerClasses = playerClasses;
        this.rounds = rounds;
    }

    private TournamentStats stats;
    private Map<Color, Class<? extends Player>> playerClasses;
    private final int rounds;
    
    @Override
    public void run() {
        for (int round=0; round<rounds; round++) {
            OfflineMatch match = new OfflineMatch(playerClasses);
            match.run();
            stats.addMatch(match.getStats());
        }
    }

    public TournamentStats getStats() {
        return this.stats;
    }

    // TODO: listener for stats updates between matches

    public static void main(String argv[]) {
        EnumMap<Color, Class<? extends Player>> players =
            new EnumMap<Color, Class<? extends Player>>(Color.class) {{
                put(Color.yellow, MostAdvancedAhead.class);
                put(Color.blue, RandomPlayer.class);
                put(Color.red, RandomPlayer.class);
                put(Color.green, RandomPlayer.class);
            }};
        OfflineTournament tournament = new OfflineTournament(players, 1000);

        tournament.run();

        TournamentStats stats = tournament.getStats();
        System.out.format("Tournament of %d rounds:\n", stats.getMatches());
        for (Color player: Color.values()) {
            System.out.format("%s\t% 3d\n", players.get(player),
                    stats.getVictories().get(player));
        }
    }
}
