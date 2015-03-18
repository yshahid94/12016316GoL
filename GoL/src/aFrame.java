import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class aFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JMenuBar menubar;
	private JMenu fileMenu, sizeMenu;
	private JMenuItem playItem, stepItem, clearItem, smallItem, mediumItem, largeItem;
	private Boolean playing = false;											//True while auto-generating
	Life life = new Life();																//Sets up an instance of the JPanel containing Game of Life

	GridBagConstraints c = new GridBagConstraints();
	
	public aFrame(){
		setLayout(new GridBagLayout());

		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		//File Menu
		playItem = new JMenuItem("Start/Stop");
	    playItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (playing.equals(false)){
					playing = true;
					life.startGenerating();
				}
				else{
					playing = false;
					life.stopGenerating();
				}
			}
		}
		);
	    
	    stepItem = new JMenuItem("Step");
	    stepItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (playing.equals(false)){
					life.generate();
					life.repaint();
				}
				else{
					playing = false;
					life.stopGenerating();
					life.generate();
					life.repaint();
				}
				
			}
		}
		);
	    
	    clearItem = new JMenuItem("Clear");
	    clearItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.clearBoard();
			}
		}
		);
	    
	    
	    //Size Menu
	    smallItem = new JMenuItem("Small");
	    smallItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.resizeBoard(5);
			}
		}
		);
	    
	    mediumItem = new JMenuItem("Medium");
	    mediumItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.resizeBoard(15);
			}
		}
		);
	    
	    largeItem = new JMenuItem("Large");
	    largeItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.resizeBoard(30);
			}
		}
		);
		
	    //File menu
  		fileMenu = new JMenu("File");
  		menubar.add(fileMenu);
		fileMenu.add(playItem);
		fileMenu.add(stepItem);
		fileMenu.add(clearItem);
		
		//Size Menu
		sizeMenu = new JMenu("Size");
  		menubar.add(sizeMenu);
  		sizeMenu.add(smallItem);
  		sizeMenu.add(mediumItem);
  		sizeMenu.add(largeItem);
			
		//Panel Constructor
		c.weightx=1;
		c.weighty=1;
		c.fill = GridBagConstraints.BOTH;
	    getContentPane().add(life, c);
	}
}
