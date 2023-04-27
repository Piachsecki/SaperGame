package org.example;

import lombok.AllArgsConstructor;

import java.util.Scanner;

/**

 The MSTextController class is responsible for controlling the text-based user interface of a MineSweeper game. It provides the player with instructions on how to play the game, and prompts the player to input their moves through a console-based scanner.

 The class has two instance variables, an MSBoardTextView object and a MineSweeperBoard object, which are used to display the game board and update the game state, respectively. The play() method starts the game and prompts the player to input their moves through a console-based scanner. The player can either reveal a field or flag a field by entering the corresponding options. The game ends when the game state is no longer in the RUNNING state.

 The class itself is responsible for the console game in terminal, so the user cannot see the real
 map during the game. It reveals the fields after the player's move.

 */
@AllArgsConstructor
public class MSTextController {
    private MSBoardTextView msBoardTextView;
    private MineSweeperBoard board;

    /**
     Starts the game and prompts the player to input their moves through a console-based scanner. The player can either reveal a field or flag a field by entering the corresponding options. The game ends when the game state is no longer in the RUNNING state.
     */
    public void play(){
        System.out.println("Welcome in our Saper Game !");
        Scanner scanner = new Scanner(System.in);
//        boolean flag = true;
        while(board.getGameState().equals(MineSweeperBoard.GameState.RUNNING)){
            System.out.println("Enter:");
            System.out.println("1: to Reveal a field:");
            System.out.println("2: to Flag a field:");
            int num = scanner.nextInt();
            System.out.println("Enter a row:");
            int x = scanner.nextInt();
            System.out.println("Enter a column:");
            int y = scanner.nextInt();
            switch (num) {
                case 1 -> {
                    board.revealField(x, y);
                    msBoardTextView.display();
                }
                case 2 -> {
                    board.toggleFlag(x, y);
                    msBoardTextView.display();
                }
            }

        }
    }
}
