package org.example;

import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class MSTextController {
    private MSBoardTextView msBoardTextView;
    private MineSweeperBoard board;

    public void play(){
        System.out.println("Welcome in our Saper Game !");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(board.getGameState().equals(MineSweeperBoard.GameState.RUNNING)){
            System.out.println("Enter:");
            System.out.println("1: to Reveal a field:");
            System.out.println("2: to Flag a field:");
            int num = scanner.nextInt();
            System.out.println("Enter a row:");
            int x = scanner.nextInt();
            System.out.println("Enter a column:");
            int y = scanner.nextInt();
            switch (num){
                case 1:
                    board.revealField(x, y);
                    msBoardTextView.display();
                    break;
                case 2:
                    board.toggleFlag(x, y);
                    msBoardTextView.display();
                    break;
            }

        }
    }
}
