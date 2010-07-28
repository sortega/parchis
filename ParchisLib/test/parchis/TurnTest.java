package parchis;

import parchis.*;
import static parchis.Color.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sortega
 */
public class TurnTest {


    @Test
    public void testRoll() {
        System.out.println("roll");

        Turn instance = new RollTurn(Color.values(), yellow);
        assertTrue(instance.mustRoll());

        Turn result = instance.move(3, null);
        assertEquals(blue, result.getPlayer());
        assertTrue(result.mustRoll());
        assertFalse(result.isSuicide(6));
        assertNull(result.getBonus());
    }

    @Test
    public void testSuicide() {
        System.out.println("suicide");
        Cell pawn = new Cell(0);

        Turn instance = new RollTurn(Color.values(), yellow);
        assertTrue(instance.mustRoll());
        
        Turn first6 = instance.move(6, null);
        assertTrue(first6.mustRoll());
        assertFalse(first6.isSuicide(6));

        Turn second6andbonus = first6.move(6, 20);
        assertFalse(second6andbonus.mustRoll());

        Turn bonusMove = second6andbonus.move(null, null);
        assertTrue(bonusMove.isSuicide(6));

        Turn third6 = bonusMove.suicide();
        assertTrue(third6.mustRoll());
        assertEquals(blue, third6.getPlayer());
    }

    @Test
    public void testPass() {
        System.out.println("pass");

        Turn instance = new RollTurn(Color.values(), yellow);
        assertTrue(instance.mustRoll());
        assertEquals(yellow, instance.getPlayer());

        Turn nextTurn = instance.pass(3);
        assertEquals(Color.blue, nextTurn.getPlayer());
        assertTrue(nextTurn.mustRoll());
    }
}