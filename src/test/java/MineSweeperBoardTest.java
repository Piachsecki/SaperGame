import org.example.MineSweeperBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MineSweeperBoardTest {


    @Test
    void createMinesTest(){
        MineSweeperBoard.GameMode gameMode = MineSweeperBoard.GameMode.DEBUG;
        MineSweeperBoard board = new MineSweeperBoard(6, 7, gameMode);


    }
}
