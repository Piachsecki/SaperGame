package org.example;

import org.example.gui.App;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main{
    public static void main(String[] args) {

        MineSweeperBoard board4 = new MineSweeperBoard(10, 10, MineSweeperBoard.GameMode.DEBUG);

        App app = new App(board4);
        app.startGameThread();



    }

}