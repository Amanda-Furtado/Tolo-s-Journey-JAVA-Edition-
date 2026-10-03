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
import java.awt.Rectangle;

public class Player extends Entity {
	GamePanel gp;
	KeyManager keyM;

	public Player(GamePanel gp, KeyManager keyM){
		this.gp = gp;
		this.keyM = keyM;
		
		hurtBox = new Rectangle(gp.tileSize/2, gp.tileSize/2, gp.tileSize - 16, gp.tileSize - 16);
		collisionBox = new Rectangle(gp.tileSize/2, gp.tileSize/2, gp.tileSize - 16, gp.tileSize - 16);

		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues(){
		life = 3;
		x = gp.tileSize * 5;
		y = gp.screenHeight/2 - (gp.tileSize / 2);
		speed = 4;
		direction = "left";
	}

	public void getPlayerImage(){
		try{
			regular = ImageIO.read(getClass().getResourceAsStream("/res/player/player_regular_spr.png"));
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override 
	public void gotHurt(){
		if(canHurt){
			life--;
		}

		System.out.printf("Life: %d\n", life);
		canHurt = false;

		try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Deu erro no tempo de espera.\n");
        }
		canHurt = true;
    }

	@Override 
	public void gotStuck(){

	}
	
	public void update(){
		if(keyM.upPressed == true){
			direction = "up";
			y -= speed;
		}
		if(keyM.downPressed == true){
			direction = "down";
			y += speed;
		}
		if(keyM.leftPressed == true){
			direction = "left";
			x -= speed;
		}
		if(keyM.rightPressed == true){
			direction = "right";
			x += speed;
		}

		collisionOn = false;
		gp.collisionM.checkTile(this);

	}

	public void draw(Graphics2D g2){
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		image = regular;
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

}