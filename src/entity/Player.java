package entity;

import main.GamePanel;
import main.KeyManager;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Image;

public class Player extends Entity {
	GamePanel gp;
	KeyManager keyM;

	public Player(GamePanel gp, KeyManager keyM){
		this.gp = gp;
		this.keyM = keyM;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues(){
		x = gp.tileSize * 5;
		y = gp.screenHeight/2 - (gp.tileSize / 2);
		speed = 4;
		// direction = "left";
	}

	public void getPlayerImage(){
		try{
			regular = ImageIO.read(getClass().getResourceAsStream("/res/player/player_regular_spr.png"));
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update(){
		if(keyM.upPressed == true){
			y -= speed;
		}
		else if(keyM.downPressed == true){
			y += speed;
		}
		else if(keyM.leftPressed == true){
			x -= speed;
		}
		if(keyM.rightPressed == true){
			x += speed;
		}	
	}

	public void draw(Graphics2D g2){
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		image = regular;
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

}