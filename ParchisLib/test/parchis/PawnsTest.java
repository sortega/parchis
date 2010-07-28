package parchis;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static parchis.Color.*;
import static parchis.Cell.*;
import parchis.Pawns.MoveResult;

/**
 *
 * @author sortega
 */
public class PawnsTest {

    @Test
    public void testNewBoard() {
        System.out.println("newBoard");

        Pawns instance = new Pawns();
        
        assertEquals(0, instance.getPlayerPawnCount(Color.yellow), 0);
        assertEquals(0, instance.getPlayerPawnCount(Color.blue), 0);
        assertEquals(0, instance.getPlayerPawnCount(Color.red), 0);
        assertEquals(0, instance.getPlayerPawnCount(Color.green), 0);
    }

    @Test
    public void testBreakBridge() {
        System.out.println("breakBridge");

        Pawns instance = new Pawns().addPawns(yellow, C(5), C(5));
        MoveResult result = instance.move(yellow, C(5), 6);

        assertNull(result.getBonus());
        assertEquals(2, result.getPawns().getPlayerPawnCount(yellow));
    }

}