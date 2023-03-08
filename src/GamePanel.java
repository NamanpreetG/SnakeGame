import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.util.Random;
import java.util.random.*;

public class GamePanel extends JPanel implements ActionListener{
	
	
	static final int SCREEN_WIDHT = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDHT*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	
	//These arrays store the snakes location and size
	final int x[]= new int[GAME_UNITS];
	final int y[]= new int[GAME_UNITS];
	//size of the snake
	int bodyParts = 6;
	int applesEaten = 0 ;
	//location of the apple 
	int appleX;
	
	
	//This the direction the snake travels, R for right, L for left, D for down and U for up
	char direction = 'R';
	
	boolean running = false;
	Timer timer;
	Random random;
	
	
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	
	
	public void startGame() {
		
	}
	
	public void paintComponent(Graphics g) {
		
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void move() {
		
	}
	
	public void checkApple() {
		
	}
	
	public void checkCollision() {
		
	
	}

	public void gameOver(Graphics g) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override 
		public void keyPressed(KeyEvent e) {
			
		}
	}

}
