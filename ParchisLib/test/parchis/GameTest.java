package parchis;

import java.util.*;
import parchis.dices.Dice;
import org.junit.Test;
import parchis.actions.*;
import parchis.dices.MockedDice;
import static org.junit.Assert.*;
import static parchis.Color.*;
import static parchis.Cell.*;

/**
 *
 * @author sortega
 */
public class GameTest {


    /**
     * Test of isFinished method, of class Game.
     */
    @Test
    public void testInitialGame() {
        System.out.println("testInitialGame");
        
        final Game instance = new Game(ALL_PLAYERS, yellow);

        assertEquals(yellow, instance.getCurrentPlayer());
        assertEquals(0, instance.getPawns().getPlayerPawnCount(yellow));
        assertEquals(0, instance.getPawns().getPlayerPawnCount(blue));
        assertEquals(0, instance.getPawns().getPlayerPawnCount(red));
        assertEquals(0, instance.getPawns().getPlayerPawnCount(green));

        final Game instance2 = new Game();

        assertEquals(yellow, instance2.getCurrentPlayer());
    }

    @Test
    public void testNormalMoves() {
        System.out.println("testNormalMoves");

        final Game instance = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(5), C(65), C(16))
                    .addPawns(green, C(20))
            );

        final Dice dice = new MockedDice(4);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(new Move(C(5), 4));
            add(new Move(C(65), 4));
            add(new Move(C(16), 4));
        }};
        assertEquals(expectedActions, actions);

        final Game instance2 = choice.choose(new Move(C(16), 4));
        final Choice choice2 = instance2.getChoice(dice);
        Set<Action> actions2 = choice2.getActions();
        Set<Action> expectedActions2 = new HashSet<Action> () {{
            add(new Move(C(5), 20));
            add(new Move(C(20), 20));
        }};
        assertEquals(expectedActions2, actions2);

    }

    @Test
    public void testBridgeBlockedMoves() {
        System.out.println("testBridgeBlockedMoves");
        final Game instance = new Game(new RollTurn(green),
                new Pawns()
                    .addPawns(yellow, C(5), C(5), C(12), C(12))
                    .addPawns(green,C(1), C(11)));

        final Dice dice = new MockedDice(3);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action>() {{
            add(new Move(C(1), 3));
        }};

        assertEquals(expectedActions, actions);
    }

    @Test
    public void testBreakBridgeOn6() {
        System.out.println("testBreakBridgeOn6");
        final Game instance = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(5), C(5), C(12), C(13))
                    .addPawns(green, C(1), C(11)));

        final Dice dice = new MockedDice(6);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(new Move(C(5), 7));
        }};

        assertEquals(expectedActions, actions);
    }

    @Test
    public void test5Moves() {
        System.out.println("test5Moves");

        final Game instance = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(5), C(65), C(yellow, 7)));

        final Dice dice = new MockedDice(5, 5, 5);

        final Choice choice = instance.getChoice(dice);
        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> ();
        expectedActions.add(new CreatePawn());
        assertEquals(expectedActions, actions);

        final Game instance2 = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(5), C(65), C(yellow, 7), C(yellow, 7)));
        final Choice choice2 = instance2.getChoice(dice);
        Set<Action> actions2 = choice2.getActions();
        Set<Action> expectedActions2 = new HashSet<Action> ();
        expectedActions2.add(new Move(C(5), 5));
        expectedActions2.add(new Move(C(65), 5));
        assertEquals(expectedActions2, actions2);

        final Game instance3 = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(5), C(5), C(65)));
        final Choice choice3 = instance3.getChoice(dice);
        Set<Action> actions3 = choice3.getActions();
        Set<Action> expectedActions3 = new HashSet<Action> () {{
            add(new Move(new Cell(5), 5));
            add(new Move(new Cell(65), 5));
        }};
        assertEquals(expectedActions3, actions3);
    }

    @Test
    public void testShelter() {
        System.out.println("testShelter");

        final Game instance = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(12))
                    .addPawns(green, C(11)));

        final Dice dice = new MockedDice(1);
        final Choice choice = instance.getChoice(dice);

        Action moveAction = choice.getActions().iterator().next();
        final Game result = choice.choose(moveAction);

        assertEquals(1, result.getPawns().getPlayerPawnCount(yellow));

    }

    public void testGoalBonus() {
        System.out.println("testGoalBonus");

        final Game instance = new Game(new RollTurn(yellow),
                new Pawns()
                    .addPawns(yellow, C(yellow, 7), C(5)));

        final Dice dice = new MockedDice(1);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        final Move moveToGoal = new Move(C(yellow, 7), 1);
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(moveToGoal);
            add(new Move(C(5), 1));
        }};

        assertEquals(expectedActions, actions);

        Set<Action> actions2 = choice.choose(moveToGoal)
                .getChoice(dice).getActions();
        Set<Action> expectedActions2 = new HashSet<Action> () {{
            add(new Move(C(5), 10));
        }};
        assertEquals(expectedActions2, actions2);

    }

    @Test
    public void testGoal() {
        System.out.println("testGoal");
        final Game instance = new Game(new RollTurn(yellow),
                new Pawns().addPawns(yellow, C(yellow, 8), C(yellow, 8),
                    C(yellow, 8), C(yellow, 8)));

        assertTrue(instance.isFinished());
    }

    @Test
    public void testBonus10() {
        System.out.println("testBonus10");

        final Game instance = new Game(new RollTurn(yellow),
                new Pawns().addPawns(yellow, C(yellow, 3), C(5), C(yellow,8), C(yellow, 8)));

        final Dice dice = new MockedDice(5);
        final Choice choice = instance.getChoice(dice);

        final Action moveToGoal = new Move(C(yellow, 3), 5);
        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(moveToGoal);
            add(new Move(C(5), 5));
        }};
        assertEquals(expectedActions, actions);

        final Game instance2 = choice.choose(moveToGoal);
        assertEquals(yellow, instance2.getCurrentPlayer());

        final Choice choice2 = instance2.getChoice(dice);
        final Action moveOther = new Move(C(5), 10);
        Set<Action> actions2 = choice2.getActions();
        Set<Action> expectedActions2 = new HashSet<Action> () {{
            add(moveOther);
        }};
        assertEquals(expectedActions2, actions2);

        final Game instance3 = choice2.choose(moveOther);
        assertEquals(blue, instance3.getCurrentPlayer());
        assertTrue(instance3.getTurn().mustRoll());
    }

    @Test
    public void testSuicide() {
        System.out.println("testSuicide");
        final Game instance = new Game(new RollTurn(yellow),
                new Pawns().addPawns(yellow, C(5)));

        final Dice dice = new MockedDice(6, 6, 6);
        final Choice choice = instance
                .getChoice(dice).choose(new Move(C(5), 6))   // 1st six
                .getChoice(dice).choose(new Move(C(11), 6))  // 2nd six
                .getChoice(dice);                            // oops

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(new Suicide(C(17)));
        }};

        assertEquals(expectedActions, actions);

        final Game instance2 = choice.choose(actions.iterator().next());
        assertEquals(0, instance2.getPawns().getPlayerPawnCount(yellow));
    }

    @Test
    public void testPass() {
        System.out.println("testPass");

        final Game instance = new Game(new RollTurn(green),
                new Pawns().addPawns(yellow, C(12)));

        final Dice dice = new MockedDice(1);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(new Pass());
        }};
        assertEquals(expectedActions, actions);
    }

    @Test
    public void testCreateAndMove() {
        System.out.println("testPass");

        final Game instance = new Game(new RollTurn(yellow), new Pawns());

        final Dice dice = new MockedDice(5, 3, 2);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(new CreatePawn());
        }};
        assertEquals(expectedActions, actions);

        final Game instance2 = choice.choose(actions.iterator().next());
        assertEquals(blue, instance2.getCurrentPlayer());
        assertEquals(1, instance2.getPawns().getPlayerPawnCount(yellow));

        final Choice choice2 = instance2.getChoice(dice);
        Set<Action> actions2 = choice2.getActions();
        Set<Action> expectedActions2 = new HashSet<Action> () {{
            add(new Pass());
        }};
        assertEquals(expectedActions2, actions2);

        final Game instance3 = choice2.choose(new Pass());
        final Choice choice3 = instance3.getChoice(dice);
        assertNotNull(choice3);
    }

    @Test
    public void testOneSix() {
        System.out.println("oneSix");

        final Game instance = new Game(new RollTurn(yellow),
                new Pawns().addPawns(yellow, C(5)));

        final Dice dice = new MockedDice(6, 1);
        final Choice choice = instance.getChoice(dice);

        Set<Action> actions = choice.getActions();
        Set<Action> expectedActions = new HashSet<Action> () {{
            add(new Move(C(5), 6));
        }};
        assertEquals(expectedActions, actions);

        final Game instance2 = choice.choose(actions.iterator().next());
        assertEquals(yellow, instance2.getCurrentPlayer());

        final Choice choice2 = instance2.getChoice(dice);
        Set<Action> actions2 = choice2.getActions();
        Set<Action> expectedActions2 = new HashSet<Action> () {{
            add(new Move(C(11), 1));
        }};
        assertEquals(expectedActions2, actions2);

        final Game instance3 = choice2.choose(actions2.iterator().next());
        assertEquals(blue, instance3.getCurrentPlayer());
    }

    // TODO: no es lo mismo pasar por no poder mover que por querer dejar de
    // tirar. Si te sale 6 y no tienes fichas tienes derecho a volver a
    // intentarlo
    // TODO: el suicidio no funciona
    // TODO: matar dos pájaros de un tiro
}