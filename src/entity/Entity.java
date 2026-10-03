package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class Entity {
	public int life;

	public int x, y;
	public int speed;
	
	public BufferedImage regular;
	public String direction;

	public Rectangle collisionBox;
	public boolean collisionOn = false;

	public Rectangle hurtBox;
	public boolean canHurt = true;

	public Rectangle hitBox;
	public boolean canHit = false;
	
	

	public abstract void gotHurt();
	public abstract void gotStuck();
}