import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.util.Random;
import java.util.random.*;

public class GamePanel extends JPanel implements ActionListener{
	
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	
	//These arrays store the snakes location and size
	final int x[]= new int[GAME_UNITS];
	final int y[]= new int[GAME_UNITS];
	//size of the snake
	int bodyParts = 6;
	int applesEaten = 0 ;
	//location of the apple 
	int appleX;  // x co-ord of apple
	int appleY;	// y co-ord of apple
	
	
	//This the direction the snake travels, R for right, L for left, D for down and U for up
	char direction = 'R';
	
	boolean running = false;
	Timer timer;
	Random random;
	
	
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	
	public void draw(Graphics g) {
		
		
		if(running) {
			
			/*
				for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
					g.setColor(Color.cyan);
					g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
					g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
				}
			*/	
				g.setColor(Color.red);
				g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
				
				//creating the snake
				for(int i =0; i< bodyParts; i++) {
					if(i==0) {
						g.setColor(Color.green);
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}else {
						g.setColor(new Color(45,180,0));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
				
				g.setColor(Color.red);
				g.setFont(new Font("Ink free", Font.BOLD, 12));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("SCORE: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("SCORE: " + applesEaten))/2, g.getFont().getSize());
				
		}
		else {
			gameOver(g);
		}
	}
	
	public void newApple() {
		
		// this gives x,y random co-ords 
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
			
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;		
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		}
		
	}
	
	public void checkApple() {
		if ((x[0]==appleX)&& (y[0]==appleY)) {			// checks whether the co-ords of apple are same as the head of the snake
			bodyParts++;			// if they are the same the bodyparts increase in size
			applesEaten++;			// number of apples eaten increases which is practically our score
			newApple();		//the apple is eaten therefore a new one is generated 
		}
	}
	
	public void checkCollisions() {
		
		// This checks if the head collides with the body 
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i]) && (y[0]==y[i])) {
				running = false;
			}
		}
		
		//checks if the head touches left border, which would have a value of less then zero 
		if(x[0]<0) {
			running = false;
		}
		
		//checks if the head touches right border, which would have a value of more then the screen widht 
		if(x[0]> SCREEN_WIDTH) {
			running = false;
		}
		
		//checks if the head touches bottom border, which would have a value of less then zero 
		if(y[0]<0) {
			running = false;
		}
		
		//checks if the head touches top border, which would have a value of more then the screen height 
		if(y[0]> SCREEN_HEIGHT) {
			running = false;
		}
		
		
		//timer stops if running is false
		if(!running) {
			timer.stop();
		}
	
	}

	public void gameOver(Graphics g) {
		
		//displays score after
		g.setColor(Color.red);
		g.setFont(new Font("Ink free", Font.BOLD, 12));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("SCORE: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("SCORE: " + applesEaten))/2, g.getFont().getSize());
		
		//set up the game over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);
	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
			
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override 
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_ENTER:
				
				break;
					
				
			}
			
		}
	}

}
