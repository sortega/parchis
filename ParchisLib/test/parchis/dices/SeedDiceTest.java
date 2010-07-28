package parchis.dices;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sortega
 */
public class SeedDiceTest {

    public SeedDiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of roll method, of class SeedDice.
     */
    @Test
    public void testRoll() {
        System.out.println("roll");

        int[] values = new int[7];

        SeedDice instance = new SeedDice(123);

        for (int i=0; i<10000; i++) {
            values[instance.roll()]++;
        }

        assertEquals(0, values[0]);
        for (int i=1; i<=6; i++) {
            assertEquals(1f/6f, values[i]/10000f, 0.01f);
        }
    }


}