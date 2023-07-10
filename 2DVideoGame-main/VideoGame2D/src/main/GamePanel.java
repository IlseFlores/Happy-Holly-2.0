package main;

import Entity.Player;
import Tile.Manager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int OriginalTile = 16; //16x16
    final int scale = 3;
    //Tiles=TileSize
    public final int Tiles = OriginalTile * scale; //48x48
    public final int Col = 20;
    public final int Row = 12;
    public final int Width = Tiles * Col; //768 pix
    public final int Height = Tiles * Row; //576 pix

    //World SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = OriginalTile * maxWorldCol;
    public final int worldHeight = OriginalTile * maxWorldRow;
    
    
    
    
    //FPS
    int FPS = 60;

    Manager tileM = new Manager(this);
    Keys key = new Keys();
    Thread gameThread; //60 fps
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,key);

    public GamePanel() {
        this.setPreferredSize(new Dimension(Width, Height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double draw = 1000000000/FPS; //0.016sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long current;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            current = System.nanoTime();
            delta += (current - lastTime) / draw;
            timer += (current - lastTime);
            lastTime = current;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " +drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.Draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
