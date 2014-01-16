import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Player extends JPanel {
	/**
	 * The player class, which is the block that the player controls
	 */
	
	int direction = 0;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	int speed = 8;
	public Player(){
		setBackground(Color.red);
		setBounds(400,250,30,30);
		
		
		Timer moveP = new Timer(25,move);
		moveP.start();
	}
	
	
	ActionListener move = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
				if(getY()<=465 && down)
				setLocation(getX(), getY()+speed);
			
				if(getY()>=5 && up)
				setLocation(getX(), getY()-speed);
			
				if(getX()>=5 && left)
				setLocation(getX()-speed, getY());
			
				if(getX()<=765 && right)
				setLocation(getX()+speed, getY());
		}	
	};
}
