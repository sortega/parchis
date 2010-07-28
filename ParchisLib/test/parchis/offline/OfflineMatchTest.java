package parchis.offline;

import parchis.ai.Player;
import java.util.HashMap;
import parchis.Color;
import parchis.ai.RandomPlayer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sortega
 */
public class OfflineMatchTest {

    /**
     * Test of run method, of class OfflineMatch.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        OfflineMatch instance = new OfflineMatch(new HashMap<Color, Class<? extends Player>>() {{
            put(Color.yellow, RandomPlayer.class);
            put(Color.blue, RandomPlayer.class);
            put(Color.red, RandomPlayer.class);
            put(Color.green, RandomPlayer.class);
        }});
        instance.run();

        assertNotNull(instance.getGame());
        System.out.println(instance.getGame());
    }


}