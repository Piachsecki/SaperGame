package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class MSBoardTextView {
    private MineSweeperBoard board;

    public void display() {
        Field[][] temporaryBoard = this.board.getBoard();
        int height = this.board.getHeight();
        int width = this.board.getWidth();

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                if (temporaryBoard[i][j].isRevealed()) {
                    System.out.printf("%s", temporaryBoard[i][j]);
                } else if (temporaryBoard[i][j].isHasFlag()) {
                    System.out.printf("%s", temporaryBoard[i][j].printFlagField());
                } else {
                    System.out.printf("%s", temporaryBoard[i][j].printBareField());
                }
            }

            System.out.println();
        }

    }
}
