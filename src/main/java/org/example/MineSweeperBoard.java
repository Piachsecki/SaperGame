package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Getter
@Setter
@With
@AllArgsConstructor
public class MineSweeperBoard {
    private final int width;
    private final int height;
    private Field[][] board;
    private GameState gameState = GameState.RUNNING;

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

    private void initializeFields(GameMode gameMode) {
        createMines(gameMode);
    }

    public void setFieldRevealedValue(int row, int column) {
        board[row][column].setRevealed(true);
    }

//    public void setFieldMineValue(int row, int column) {
//        board[row][column].setHasMine(true);
//    }

    public void setFieldFlagValue(int row, int column) {
        board[row][column].setHasFlag(true);
    }

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


    public void debug_display() {
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width ; j++) {
                System.out.printf("%s", board[i][j]);
            }
            System.out.println();
        }
    }

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


    boolean hasFlag(int row, int col) {
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

    // if the field at (row,col) was not revealed - change flag status for this field
    // Do nothing if any of the following is true
    // - field was already revealed
    // - either row or col is outside board
    // - game is already finished
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




    public enum GameMode {
        DEBUG,
        EASY,
        NORMAL,
        HARD
    }

    public enum GameState {
        RUNNING,
        FINISHED_WIN,
        FINISHED_LOSS
    }


}
