import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Block extends JPanel{
	
	/**
	 * The block class, which are the blocks to be avoided by the player
	 */
	private static final long serialVersionUID = 1L;
	
	int x;
	int y;
	int size;
	int side = (int)Math.ceil(Math.random()*4);
	int change = 3;
	boolean clicked = false;
	//int delay = 30; //milliseconds
	/**
	 * 
	 * @param size1 Indicates the size of the block to be created.
	 * @param delay Sets the speed of the block. Lower is faster.
	 */
	public Block(int size1, int delay){
		size = size1;
		switch(side){
			case 1:
				x = 800;
				y = (int)Math.round(Math.random()*450);
			break;
			case 2:
				x = -50;
				y = (int)Math.round(Math.random()*450);
			break;
			case 3:
				x = (int)Math.round(Math.random()*750);
				y = 500;
			break;
			case 4:
				x = (int)Math.round(Math.random()*750);
				y = -50;
			break;
		}
		
		setBounds(x, y, size1, size1);
		setBackground(Color.black);
		
		
		
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		        setLocation(x, y);
		        
		        switch(side){
		        	case 1:
		        		x-=change;
		        	break;
		        	case 2:
		        		x+=change;
		        	break;
		        	case 3:
		        		y-=change;
		        	break;
		        	case 4:
		        		y+=change;
		        	break;
		        }
		      }
		  };
		  
		  /*addActionListener( new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        clicked=true;
		      }
		  });*/

		Timer timer = new Timer(delay,taskPerformer);//moves block
		timer.start();
	}

}
