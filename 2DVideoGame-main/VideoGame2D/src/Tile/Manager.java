package Tile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Manager {
    GamePanel gamePanel;
    public tile[] tiles;
    public int Map[][];

    public Manager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new tile[10];//kinds of tiles
        Map = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        LoadMaps("/Map/1World.txt");
    }

    public void getTileImage(){
        try {
            tiles[0] = new tile();
            tiles[0].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tiles[1] = new tile();
            tiles[1].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png"));
            tiles[1].collision = true; 
            
            tiles[2] = new tile();
            tiles[2].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
            tiles[2].collision =true;
            
            tiles[3] = new tile();
            tiles[3].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            
            tiles[4] = new tile();
            tiles[4].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4].collision = true;
            
            tiles[5] = new tile();
            tiles[5].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void LoadMaps(String Path){
        try {
            InputStream is = getClass().getResourceAsStream(Path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    Map[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void Draw(Graphics2D graphics2D){
        int Worldcolumn = 0;
        int Worldrow = 0;
        int col = 0;
        int row = 0;
        int x =0;
        int y = 0;
;        

        while (Worldcolumn < gamePanel.maxWorldCol && Worldrow < gamePanel.maxWorldRow){
            int Tile_Number = Map[Worldcolumn][Worldrow];
            
            int Worldx = Worldcolumn * gamePanel.Tiles;
            int Worldy = Worldrow * gamePanel.Tiles;
            int Screenx = Worldx - gamePanel.player.Worldx + gamePanel.player.Screenx;
            int Screeny = Worldy - gamePanel.player.Worldy + gamePanel.player.Screeny;
            
            if (Worldx + gamePanel.Tiles> gamePanel.player.Worldx - gamePanel.player.Screenx &&
        		Worldx - gamePanel.Tiles< gamePanel.player.Worldy + gamePanel.player.Screenx &&
        		Worldy + gamePanel.Tiles> gamePanel.player.Worldy - gamePanel.player.Screeny &&
        		Worldy - gamePanel.Tiles< gamePanel.player.Worldy + gamePanel.player.Screeny) {
            graphics2D.drawImage(tiles[Tile_Number].Image,Screenx,Screeny,gamePanel.Tiles, gamePanel.Tiles, null);      	
            }
            Worldcolumn++;
           

            if(Worldcolumn == gamePanel.maxWorldCol){
            	Worldcolumn = 0;
            	Worldrow++;
            }
                
            }
        }
    }

