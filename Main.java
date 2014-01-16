import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Main extends JApplet implements KeyListener {
	
	/**
	 * DodgeBlocks
	 * @author:Jeremy Francispillai and Matt Lee
	 * Date: May 30, 2012
	 */
	private static final long serialVersionUID = 1L;
	Block[] blocks = new Block[50];
	
	int counter =0;
	
	Player player = new Player();
	
	int delay = 34;
	int delay2 = 1000;
	
	JButton start=new JButton("Start");
	int scoreCount = 0;
	int highscore = 0;
	JLabel high = new JLabel("Highscore: 0");
	JLabel score = new JLabel("Score: 0");
	JLabel title = new JLabel("DodgeBlocks");
	Font font = new Font("Plain", Font.BOLD,50);
	/**
	 * Initializes application
	 */
	public void init(){
		
		title.setFont(font);
		title.setBounds(250,150,400,100);
		add(title);
		setFocusable(true);
		setSize(800,500);
		setLayout(null);
		start.setBounds(370,300,100,30);
		start.addActionListener(starter);
		add(start);
		high.setBounds(380, 350, 120, 30);
		add(high);
		/*add(start);
		start.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			begin();
		}
	};*/
		//begin();
		
	}
	
	ActionListener starter = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			begin();
		}
	};
	
	ActionListener taskPerformer2 = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	        Block b1 = new Block((int)Math.floor(Math.random()*25)+35,delay);
	        remove(blocks[counter]);
	        blocks[counter]=b1;
	        add(blocks[counter]);
	        //remove(b1);
	        //Timer time = new Timer(10000,delete);
	        counter++;
	        counter = counter%blocks.length;
	        
	       
	      }
	  };
	
	Timer timer = new Timer(delay2,taskPerformer2);
	
	
	Timer timer2 = new Timer(50, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			for (int i = 0; i < blocks.length; i++) {
				
				if(blocks[i].getX()<-100||blocks[i].getX()>900|| blocks[i].getY()<-100 ||blocks[i].getY()>600){
					remove(blocks[i]);
				}
				
				if (blocks[i].getX() < player.getX()) {
					if (blocks[i].getY() < player.getY()) {
						if (player.getX() - blocks[i].getX() < blocks[i].size
								&& player.getY() - blocks[i].getY() < blocks[i].size) {
							endGame();							
						}
					} else if (blocks[i].getY() > player.getY()) {
						if (player.getX() - blocks[i].getX() < blocks[i].size
								&& blocks[i].getY() - player.getY() < 30) {
							endGame();							
						}
					} else {
						if (player.getX() - blocks[i].getX() < blocks[i].size) {
							endGame();							
						}
					}
				}
				//
				if (blocks[i].getX() > player.getX()) {
					if (blocks[i].getY() > player.getY()) {
						if (blocks[i].getX() - player.getX() < 30
								&& blocks[i].getY() - player.getY() < 30) {
							endGame();							
						}
					} else if (blocks[i].getY() < player.getY()) {
						if ((player.getX() - blocks[i].getX()) * (-1) < 30
								&& (blocks[i].getY() - player.getY())
										* (-1) < blocks[i].size) {
							endGame();							
						}
					} else {
						if (blocks[i].getX() - player.getX() < 30) {
							endGame();							
						}
					}
				}
			}
			
			scoreCount++;
			score.setText("Score: "+ scoreCount);
		}
	});
	
	Timer difficulty = new Timer(1250, new ActionListener(){
		
		public void actionPerformed(ActionEvent arg0){ 
			if(delay>1 && delay2%20==0){
				delay--;
			}
				delay2 -= 10;
				timer.setDelay(delay2);
		}
		
	});
	
	
	/**
	 * Starts or restarts the game.
	 */
	public void begin(){
		
		remove(start);
		remove(high);
		remove(title);
		repaint();
		counter =0;
		delay = 34;
		delay2 = 1000;
		player.setLocation(400, 250);
		add(player);
		
		for(int i=0;i<blocks.length;i++){
			blocks[i] = new Block(1,500000);
			add(blocks[i]);
		}
		
		addKeyListener(this);
		timer.start();
		timer2.start();
		difficulty.start();
		
		score.setBounds(10,10,100,30);
		add(score);
		scoreCount = 0;
	}
		/**
		 * Checks if a block exits the screen and deletes it.
		 */
		public void Check(){
			
			for(int i=0;i<blocks.length;i++){
					if(blocks[i].getX()<-100||blocks[i].getX()>900|| blocks[i].getY()<-100 ||blocks[i].getY()>600){
						remove(blocks[i]);
					}
			}
			
		}
		
		
		 public void keyTyped(KeyEvent e) {
		       return;
		   }

		   @Override
		   public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 37:
					player.left = true;
					break;
				case 39:
					player.right = true;
					break;
				case 38:
					player.up = true;
					break;
				case 40:
					player.down = true;
					break;
				}
			}

		    @Override
		    public void keyReleased(KeyEvent e) {
		    	switch (e.getKeyCode()) {
				case 37:
					player.left = false;
					break;
				case 39:
					player.right = false;
					break;
				case 38:
					player.up = false;
					break;
				case 40:
					player.down = false;
					break;
				}
		    }
		
		
		    /**
		     * Clears off all blocks and displays score
		     */
		public void endGame(){
			for(int i=0;i<blocks.length;i++){
				remove(blocks[i]);
		}
			timer.stop();
			timer2.stop();
			difficulty.stop();
			remove(player);
			remove(score);
			repaint();
			add(start);
			start.setText("Retry");
			add(high);
			add(title);
			
			if(scoreCount>highscore){
				highscore = scoreCount;
			}
			high.setText("Highscore: " + highscore);
		}

}
