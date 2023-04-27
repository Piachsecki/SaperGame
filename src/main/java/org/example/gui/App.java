package org.example.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import org.example.MineSweeperBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

@Getter
@Setter
@With
@AllArgsConstructor
public class App extends JFrame implements Runnable {
    public static Font ARIAL_40 = new Font("Arial", Font.PLAIN, 40);
    public static Font ARIAL_80B = new Font("Arial", Font.BOLD, 80);

    public static int SPACING = 5;
    private Thread gameThread;

    private ImageIcon flag;
    private ImageIcon mine;
    private ImageIcon revealField;
    private MineSweeperBoard board;
    private JLabel label;
    private MouseMoveHandler ms;
    private int xCoordinates;
    private int yCoordinates;

    public App(MineSweeperBoard board) {
        this.board = board;
        this.setTitle("MinesweeperGame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.board.getWidth() * (50 + SPACING - 2), this.board.getHeight() * (50 + SPACING - 2) + 26);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        Board board1 = new Board();
        this.setContentPane(board1);

        MouseMoveHandler mouseMoveHandler = new MouseMoveHandler();
        this.addMouseMotionListener(mouseMoveHandler);

        MouseClickHandler mouseClickHandler = new MouseClickHandler();
        this.addMouseListener(mouseClickHandler);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long currentTime;

        long lastTime = System.nanoTime();
        double delta = 0;
        double drawInterval = 1000000000 / 60;
        long timer = 0;

        int drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            repaint();
            delta--;
            drawCount++;
        }
        if (timer >= 1000000000) {
            System.out.println("FPS:" + drawCount);
            drawCount = 0;
            timer = 0;
        }


    }


    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
            flag = new ImageIcon("./src/main/resources/flag.png");
            mine = new ImageIcon("./src/main/resources/mine.png");
            revealField = new ImageIcon("./src/main/resources/revealField.png");
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.darkGray);
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.getBoard()[i][j].isRevealed() && board.getBoard()[i][j].isHasMine()) {
                        g2.drawImage(mine.getImage(), i * 50 + SPACING,
                                j * 50 + SPACING, 50 - 2 * SPACING, 50 - 2 * SPACING, null);

                    } else if (board.getBoard()[i][j].isRevealed()) {
                        int minesCount = board.countMinesAround(i, j);
                        ImageIcon number;
                        switch (minesCount) {
                            case 0 -> g2.drawImage(revealField.getImage(), i * 50 + SPACING,
                                    j * 50 + SPACING, 50 - 2 * SPACING, 50 - 2 * SPACING, null);
                            case 1 -> {
                                number = new ImageIcon("./src/main/resources/number1.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 2 -> {
                                number = new ImageIcon("./src/main/resources/number2.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 3 -> {
                                number = new ImageIcon("./src/main/resources/number3.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 4 -> {
                                number = new ImageIcon("./src/main/resources/number4.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 5 -> {
                                number = new ImageIcon("./src/main/resources/number5.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 6 -> {
                                number = new ImageIcon("./src/main/resources/number6.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 7 -> {
                                number = new ImageIcon("./src/main/resources/number7.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                            case 8 -> {
                                number = new ImageIcon("./src/main/resources/number8.png");
                                g2.drawImage(number.getImage(), i * 50 + SPACING + 10,
                                        j * 50 + SPACING + 10, 20, 20, null);
                            }
                        }
                    } else if (board.getBoard()[i][j].isHasFlag()) {
                        g2.drawImage(flag.getImage(), i * 50 + SPACING,
                                j * 50 + SPACING, 50 - 2 * SPACING, 50 - 2 * SPACING, null);
                    } else {
                        g2.setColor(Color.gray);
                        g2.fillRect(
                                SPACING + i * 50,
                                SPACING + j * 50,
                                50 - 2 * SPACING,
                                50 - 2 * SPACING
                        );

                        if (board.getGameState().equals(MineSweeperBoard.GameState.FINISHED_WIN)) {

                            String text;
                            int textLength;
                            int x;
                            int y;
                            FontMetrics metrics = g.getFontMetrics(ARIAL_80B);

                            text = "Congratulations, you won!";
                            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                            x = board.getWidth() * (50 + SPACING - 2) - metrics.stringWidth(text) / 2;
                            y = (board.getHeight() * (50 + SPACING - 2) + 26 - metrics.getHeight()) / 2 + metrics.getAscent();
                            g2.drawString(text, x, y);
                        } else if (board.getGameState().equals(MineSweeperBoard.GameState.FINISHED_LOSS)) {

                            int x;
                            int y;
                            String text;
                            int textLength;

                            text = "You LOST!, aaaaaaaaaaaaaaaaa";
                            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

                            x = board.getWidth() * 20 - textLength / 2;
                            y = (board.getHeight() * (50 + SPACING - 2)) / 2 + 150;
                            g2.drawString(text, x, y);

                        }
                    }


                }


            }
        }
    }


    public class MouseMoveHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            xCoordinates = e.getX();
            yCoordinates = e.getY();
        }
    }


    public class MouseClickHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (board.getGameState().equals(MineSweeperBoard.GameState.RUNNING)) {
                System.out.println("coordinates: [" + xParameterOfABox() + ", " + yParameterOfABox() + "]");
                if (xParameterOfABox() != -1 && yParameterOfABox() != -1 && isLeftMouseButton(e)) {
                    board
                            .setFieldRevealedValue(
                                    xParameterOfABox(),
                                    yParameterOfABox()
                            );
                    if (board.getBoard()[xParameterOfABox()][yParameterOfABox()].isHasMine()) {
                        System.out.println("Elo");
                        board.setGameState(MineSweeperBoard.GameState.FINISHED_LOSS);
                        gameThread = null;
                    }
                }
                if (xParameterOfABox() != -1 && yParameterOfABox() != -1 && isRightMouseButton(e)) {
                    board
                            .setFieldFlagValue(
                                    xParameterOfABox(),
                                    yParameterOfABox()
                            );
                }

            }



        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        public int xParameterOfABox() {
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (
                            xCoordinates >= 8 + SPACING + i * 50 &&
                                    xCoordinates < 8 + SPACING + i * 50 + 50 - 2 * SPACING &&
                                    yCoordinates >= 31 + SPACING + j * 50 &&
                                    yCoordinates < 31 + SPACING + j * 50 + 50 + -2 * SPACING

                    ) {
                        return i;
                    }
                }
            }
            return -1;
        }

        public int yParameterOfABox() {
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (
                            xCoordinates >= 8 + SPACING + i * 50 &&
                                    xCoordinates < 8 + SPACING + i * 50 + 50 - 2 * SPACING &&
                                    yCoordinates >= 31 + SPACING + j * 50 &&
                                    yCoordinates < 31 + SPACING + j * 50 + 50 + -2 * SPACING
                    ) {
                        return j;
                    }
                }
            }
            return -1;

        }
    }
}



