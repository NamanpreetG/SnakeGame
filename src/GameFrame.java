import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame{
	
	GamePanel game = new GamePanel();
	GameFrame(){
		
		this.add(game);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.setBackground(Color.white);
			this.remove(game);
			new GamePanel();
			this.add(new GamePanel());
			SwingUtilities.updateComponentTreeUI(this);
				
		}
	}
	
		
	
		

}
