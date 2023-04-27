import org.example.MineSweeperBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MineSweeperBoardTest {


    @Test
    void createMinesTest(){
        MineSweeperBoard.GameMode gameMode = MineSweeperBoard.GameMode.DEBUG;
        MineSweeperBoard board = new MineSweeperBoard(10, 10, gameMode);
        board.revealField(5,7);
        board.revealField(1,0);
        board.revealField(1,2);
        board.revealField(8,9);
        board.revealField(4,3);
        assertEquals(1, board.countMinesAround(5, 7));
        assertEquals(4, board.countMinesAround(1, 0));
        assertEquals(5, board.countMinesAround(1, 2));
        assertEquals(2, board.countMinesAround(8, 9));
        assertEquals(2, board.countMinesAround(4, 3));

        //this field is not revealed
        assertEquals(-1, board.countMinesAround(2, 2));


    }


    @Test
    void toggleFlagTest(){
        MineSweeperBoard.GameMode gameMode = MineSweeperBoard.GameMode.DEBUG;
        MineSweeperBoard board = new MineSweeperBoard(10, 10, gameMode);

        board.toggleFlag(0, 0);
        board.toggleFlag(1, 1);
        assertFalse(board.hasFlag(-1, 0));
        assertTrue(board.hasFlag(1, 1));
        board.toggleFlag(2, 2);
        assertTrue(board.hasFlag(2, 2));

    }


    @Test
    void getGameStateTest(){
        MineSweeperBoard.GameMode gameMode = MineSweeperBoard.GameMode.DEBUG;
        MineSweeperBoard board = new MineSweeperBoard(3, 3, gameMode);
        board.debug_display();
        board.setFieldFlagValue(0, 0);
        board.setFieldFlagValue(0, 1);
        board.setFieldFlagValue(0, 2);
        board.setFieldFlagValue(2, 0);
        board.setFieldFlagValue(2, 2);
        board.setFieldRevealedValue(1, 0);
        board.setFieldRevealedValue(2, 1);
        board.setFieldRevealedValue(1, 2);
        assertEquals(MineSweeperBoard.GameState.FINISHED_WIN, board.getGameState());
        MineSweeperBoard board1 = new MineSweeperBoard(3, 3, gameMode);
        assertEquals(MineSweeperBoard.GameState.RUNNING, board1.getGameState());
        MineSweeperBoard board2 = new MineSweeperBoard(3, 3, gameMode);
        board2.revealField(0, 0);
        assertEquals(MineSweeperBoard.GameState.FINISHED_LOSS, board2.getGameState());

    }
}
