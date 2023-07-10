package main;

import Entity.Sprites;

public class CollisionChecker {
	
	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Sprites sprites) {
		
		int spritesLeftWorldX = sprites.Worldx + sprites.solidArea.x;
		int spritesRightWorldX = sprites.Worldx + sprites.solidArea.x + sprites.solidArea.width;
		int spritesTopWorldY = sprites.Worldy + sprites.solidArea.y;
		int spritesBottomWorldY = sprites.Worldy + sprites.solidArea.y + sprites.solidArea.height;
		
		int spritesLeftCol = spritesLeftWorldX/gamePanel.Tiles;
		int spritesRightCol = spritesRightWorldX/gamePanel.Tiles;
		int spritesTopRow = spritesTopWorldY/gamePanel.Tiles;
		int spritesBottomRow =spritesBottomWorldY/gamePanel.Tiles;
		
		int tileNum1, tileNum2;
		
		switch (sprites.direction) {
		case "up":
			spritesTopRow = (spritesTopWorldY - sprites.speed)/gamePanel.Tiles;
			tileNum1 = gamePanel.tileM.Map[spritesLeftCol][spritesTopRow];
			tileNum2 = gamePanel.tileM.Map[spritesRightCol][spritesTopRow];
			if(gamePanel.tileM.tiles[tileNum1].collision == true || gamePanel.tileM.tiles[tileNum2].collision == true) {
				sprites.collisionOn = true;
			}
			break;
		case "down":
			spritesBottomRow = (spritesBottomWorldY + sprites.speed)/gamePanel.Tiles;
			tileNum1 = gamePanel.tileM.Map[spritesLeftCol][spritesBottomRow];
			tileNum2 = gamePanel.tileM.Map[spritesRightCol][spritesBottomRow];
			if(gamePanel.tileM.tiles[tileNum1].collision == true || gamePanel.tileM.tiles[tileNum2].collision == true) {
				sprites.collisionOn = true;
			}
			break;
		case "left":
			spritesLeftCol = (spritesLeftWorldX - sprites.speed)/gamePanel.Tiles;
			tileNum1 = gamePanel.tileM.Map[spritesLeftCol][spritesTopRow];
			tileNum2 = gamePanel.tileM.Map[spritesLeftCol][spritesBottomRow];
			if(gamePanel.tileM.tiles[tileNum1].collision == true || gamePanel.tileM.tiles[tileNum2].collision == true) {
				sprites.collisionOn = true;	
			}
			break;
		case "right":
			spritesRightCol = (spritesRightWorldX + sprites.speed)/gamePanel.Tiles;
			tileNum1 = gamePanel.tileM.Map[spritesRightCol][spritesTopRow];
			tileNum2 = gamePanel.tileM.Map[spritesRightCol][spritesBottomRow];
			if(gamePanel.tileM.tiles[tileNum1].collision == true || gamePanel.tileM.tiles[tileNum2].collision == true) {
				sprites.collisionOn = true;	
			}
			break;
		}
	}


}
