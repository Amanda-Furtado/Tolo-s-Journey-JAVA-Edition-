package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
	//WINDOW
        JFrame window = new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setResizable(false);
	window.setTitle("Projeto que vale um 10.0");
	
	GamePanel gamePanel = new GamePanel();
	window.add(gamePanel);
	
	window.pack();
	
	window.setLocationRelativeTo(null);
	window.setVisible(true);
	//WINDOW

	gamePanel.startGameThread();
	



	}
}
