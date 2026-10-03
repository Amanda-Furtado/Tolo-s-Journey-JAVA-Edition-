package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
	final int genericTileSize = 16;
	final int scale = 3;

	public final int tileSize = genericTileSize * scale;
	public final int maxScreenCollum = 16 + 2;
	public final int maxScreenRow = 12 + 2;
	public final int screenWidth = tileSize * (maxScreenCollum - 2);
	public final int screenHeight = tileSize * (maxScreenRow - 2);
	
	Color bgColor = new Color(50, 51, 83);
	
	int FPS = 60;


	TileManager tileM = new TileManager(this);
	KeyManager keyM = new KeyManager();
	Thread gameThread;
	public CollisionManager collisionM = new CollisionManager(this);
	Player player = new Player(this, keyM);
	
	

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(bgColor);
		this.setDoubleBuffered(true);	
		this.addKeyListener(keyM);
		this.setFocusable(true);
	

	}

	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null){
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;

			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000){
				//System.out.println("FPS: "+ drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}

	public void update() {
		player.update();
	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		tileM.draw(g2);
		player.draw(g2);

		g2.dispose();
	}
	
	
}