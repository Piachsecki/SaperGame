package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
/**

 This class represents a Minesweeper board which is a 2D array of Field objects. The board

 dimensions and number of mines are determined by the GameMode parameter that is passed in the

 constructor.
 */
@Getter
@Setter
@With
@AllArgsConstructor
public class MineSweeperBoard {
    private final int width;
    private final int height;
    private Field[][] board;
    private GameState gameState = GameState.RUNNING;
    /**

     Constructor that creates a new Minesweeper board with the specified width and height, and
     initializes each Field object with a false value for hasMine and isRevealed. The number of
     mines on the board is determined by the GameMode parameter.
     @param width The width of the Minesweeper board.
     @param height The height of the Minesweeper board.
     @param gameMode The GameMode that determines the number of mines on the board.
     */

    public MineSweeperBoard(int width, int height, GameMode gameMode) {
        this.width = width;
        this.height = height;
        board = new Field[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Field(false, false, false);
            }
        }
        initializeFields(gameMode);
    }
    /**

     Private method that initializes the fields on the Minesweeper board. This method calls
     the createMines method which sets the specified number of mines on the board according
     to the GameMode parameter.
     @param gameMode The GameMode that determines the number of mines on the board.
     */
    private void initializeFields(GameMode gameMode) {
        createMines(gameMode);
    }
    /**

     Method that sets the revealed value of the specified field on the Minesweeper board.
     @param row The row of the field to set the revealed value.
     @param column The column of the field to set the revealed value.
     */

    public void setFieldRevealedValue(int row, int column) {
        board[row][column].setRevealed(true);
    }


    /**
     * Method that sets the flag value of the specified field on the Minesweeper board.
     *
     * @param row       The row of the field to set the flag value.
     * @param column    The column of the field to set the flag value.
     */

    public void setFieldFlagValue(int row, int column) {
        board[row][column].setHasFlag(true);
    }

    /**
     * Private method that sets the specified number of mines on the Minesweeper board according
     * to the GameMode parameter. If GameMode. DEBUG is specified, then a specific set of mines are
     * set on the board.
     *
     * @param gameMode  The GameMode that determines the number of mines on the board.
     */

    private void createMines(GameMode gameMode) {
        BigDecimal howManyMines = BigDecimal.ONE;
        if (GameMode.EASY.equals(gameMode)) {
            howManyMines = BigDecimal.valueOf((long) width * height).multiply(BigDecimal.valueOf(0.1)).setScale(0, RoundingMode.CEILING);
        }
        if (GameMode.NORMAL.equals(gameMode)) {
            howManyMines = BigDecimal.valueOf((long) width * height).multiply(BigDecimal.valueOf(0.2)).setScale(0, RoundingMode.CEILING);
        }
        if (GameMode.HARD.equals(gameMode)) {
            howManyMines = BigDecimal.valueOf((long) width * height).multiply(BigDecimal.valueOf(0.3)).setScale(0, RoundingMode.CEILING);
        }
        if (GameMode.DEBUG.equals(gameMode)) {
            for (int i = 0; i < height ; i++) {
                for (int j = 0; j < width ; j++) {
                    if (i == 0) {
                        board[i][j].setHasMine(true);
                    }
                    if (j == 0 && i % 2== 0) {
                        board[i][j].setHasMine(true);
                    }
                    if (i == j) {
                        board[i][j].setHasMine(true);
                    }
                }
            }
            return;
        }
        Random random = new Random();

        for (int i = 0; i < howManyMines.intValue(); i++) {
            while (true) {
                int x = random.nextInt(height - 1);
                int y = random.nextInt(width - 1);
                if (!board[x][y].isHasMine()) {
                    board[x][y].setHasMine(true);
                    break;
                }
            }
        }
    }

    /**
     * Method that displays in console the view of the map
     * with every initialized field - in a debug mode, so
     * the programmer is able to check the correctness of
     * the implemented methods and code.
     *
     */


    public void debug_display() {
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width ; j++) {
                System.out.printf("%s", board[i][j]);
            }
            System.out.println();
        }
    }
    /**

     This method counts the number of mines surrounding a given cell.

     @param row the row of the cell to check

     @param col the column of the cell to check

     @return the number of mines surrounding the cell or -1 if the given cell is out of bounds or not revealed
     */

    public int countMinesAround(int row, int col) {
        int result = 0;

        if (row >= this.height || row < 0 || col >= width || col < 0) {
            return -1;
        } else if (!board[row][col].isRevealed()) {
            return -1;
        } else if (row == 0 && col == 0) {

            if (board[row][col + 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col + 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col].isHasMine()) {
                result++;
            }

        } else if (row == 0 && col == width - 1) {
            System.out.println(row);
            if (board[row][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col].isHasMine()) {
                result++;
            }


        } else if (row == height - 1 && col == 0) {
            if (board[row - 1][col].isHasMine()) {
                result++;
            }
            if (board[row ][col + 1].isHasMine()) {
                result++;
            }
            if (board[row][col + 1].isHasMine()) {
                result++;
            }

        } else if (col == 0 && row != width - 1 && row != 0) {
            if (board[row - 1][col].isHasMine()) {
                result++;
            }
            if (board[row - 1][col + 1].isHasMine()) {
                result++;
            }
            if (board[row][col + 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col + 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col].isHasMine()) {
                result++;
            }

        } else if (row == 0 && col != width - 1 && col != 0) {
            if (board[row][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col].isHasMine()) {
                result++;
            }
            if (board[row + 1][col + 1].isHasMine()) {
                result++;
            }
            if (board[row][col + 1].isHasMine()) {
                result++;
            }


        } else if (col == width - 1 && row != height - 1 && row != 0) {
            if (board[row][col - 1].isHasMine()) {
                result++;
            }
            if (board[row - 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row - 1][col].isHasMine()) {
                result++;
            }
            if (board[row + 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col].isHasMine()) {
                result++;
            }

        } else if (row == height - 1 && col != width - 1 && col != 0) {
            if (board[row][col - 1].isHasMine()) {
                result++;
            }
            if (board[row - 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row - 1][col].isHasMine()) {
                result++;
            }
            if (board[row - 1][col + 1].isHasMine()) {
                result++;
            }
            if (board[row][col + 1].isHasMine()) {
                result++;
            }


        } else {
            if (board[row - 1][col].isHasMine()) {
                result++;
            }
            if (board[row - 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row - 1][col + 1].isHasMine()) {
                result++;
            }
            if (board[row][col + 1].isHasMine()) {
                result++;
            }
            if (board[row][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col - 1].isHasMine()) {
                result++;
            }
            if (board[row + 1][col].isHasMine()) {
                result++;
            }
            if (board[row + 1][col + 1].isHasMine()) {
                result++;
            }

        }

        return result;
    }

    public int getMineCount() {
        int result = 0;
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (board[i][j].isHasMine()) {
                    result++;
                }
            }

        }
        return result;
    }


    public boolean hasFlag(int row, int col) {
        if (
                row < 0 ||
                        col < 0 ||
                        row > height - 1 ||
                        col > width - 1 ||
                        board[row][col].isRevealed() ||
                        !board[row][col].isHasFlag()

        ) {
            return false;
        }

        return true;

    }

    /**
     * Method that is responsible for toggling a Flag on a
     * specified field, given by the player under some
     * significant conditions.
     * @param row
     * @param col
     */
    public void toggleFlag(int row, int col) {
        if (
                !board[row][col].isRevealed() ||
                        row < 0 ||
                        col < 0 ||
                        row > height - 1 ||
                        col > width - 1 ||
                        !gameState.equals(GameState.FINISHED_LOSS) ||
                        !gameState.equals(GameState.FINISHED_WIN)

        ) {
            board[row][col].setHasFlag(true);
        }
    }
    /**
     * Method that is responsible for revealing a
     * specified field, given by the player under some
     * significant conditions.
     * @param row
     * @param col
     */

    public void revealField(int row, int col) {
        if (
                row < 0 ||
                        col < 0 ||
                        row > height - 1 ||
                        col > width - 1 ||
                        !gameState.equals(GameState.FINISHED_LOSS) ||
                        !gameState.equals(GameState.FINISHED_WIN) ||
                        !board[row][col].isRevealed() ||
                        !board[row][col].isHasFlag()


        ) {
            if (board[row][col].isHasMine()) {
                board[row][col].setRevealed(true);
                gameState = GameState.FINISHED_LOSS;
            }
            board[row][col].setRevealed(true);
        }
    }

    /**
     * Method that is returning the current
     * Game State of the MineSweeperGame
     *
     */
    public GameState getGameState() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j].isRevealed() && board[i][j].isHasMine()) {
                    return GameState.FINISHED_LOSS;
                } else if (!board[i][j].isRevealed() && !board[i][j].isHasMine()) {
                    return GameState.RUNNING;
                }
            }
        }
        return GameState.FINISHED_WIN;
    }



/**
 The GameMode enumeration represents the different modes that a game can have.
 */
    public enum GameMode {
        DEBUG,
        EASY,
        NORMAL,
        HARD
    }
/**

 The GameState enumeration represents the different states that a game can be in.
*/

    public enum GameState {
        RUNNING,
        FINISHED_WIN,
        FINISHED_LOSS
    }


}
