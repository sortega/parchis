package parchis;

import static parchis.Cell.*;
import static parchis.Color.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sortega
 */
public class CellTest {

    public CellTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testC_int() {
        Cell instance = C(1);
        assertNull(instance.getColor());
        assertEquals(1, instance.getNumber());
    }

    @Test
    public void testC_Color_int() {
        Cell instance = C(yellow, 7);
        assertEquals(yellow, instance.getColor());
        assertEquals(7, instance.getNumber());
    }

    @Test
    public void testIsHome() {
        assertTrue(C(5).isHome());
        assertTrue(C(22).isHome());
        assertTrue(C(39).isHome());
        assertTrue(C(56).isHome());
    }

    @Test
    public void testIsStair() {
        assertFalse(C(5).isStair());
        assertTrue(C(red, 5).isStair());
        assertTrue(C(green, 8).isStair());
    }

    @Test
    public void testNextCell_Color() {
        assertEquals(C(23), C(22).nextCell(blue));
        assertEquals(C(blue, 1), C(17).nextCell(blue));
        assertEquals(C(18), C(17).nextCell(yellow));
        assertNull(C(blue, 8).nextCell(blue));
    }

    @Test
    public void testNextCell_Color_int() {
        assertEquals(C(blue, 3), C(16).nextCell(blue, 4));
        assertEquals(C(20), C(16).nextCell(yellow, 4));
        assertEquals(C(blue, 8), C(blue, 3).nextCell(blue, 5));
        assertNull(C(blue, 3).nextCell(blue, 6));
    }

    @Test
    public void testIsShelter() {
        assertFalse(C(1).isShelter());
        assertFalse(C(67).isShelter());
        assertFalse(C(yellow, 3).isShelter());

        assertTrue(C(5).isShelter());
        assertTrue(C(12).isShelter());
        assertTrue(C(17).isShelter());
        assertTrue(C(22).isShelter());
        assertTrue(C(29).isShelter());
        assertTrue(C(34).isShelter());
        assertTrue(C(39).isShelter());
        assertTrue(C(46).isShelter());
        assertTrue(C(51).isShelter());
        assertTrue(C(56).isShelter());
        assertTrue(C(63).isShelter());
        assertTrue(C(68).isShelter());
    }

    @Test
    public void testIsGoal() {
        assertFalse(C(8).isGoal());
        assertFalse(C(68).isGoal());

        assertTrue(C(yellow, 8).isGoal());
        assertTrue(C(blue, 8).isGoal());
        assertTrue(C(red, 8).isGoal());
        assertTrue(C(green, 8).isGoal());
    }

    @Test
    public void testEquals() {
        assertEquals(C(1), C(1));
        assertEquals(C(yellow, 1), C(yellow, 1));
        assertNotSame(C(1), C(green, 1));
        assertNotSame(C(yellow, 1), C(green, 1));
    }
}