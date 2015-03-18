 import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class aFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenuItem playItem, stepItem, clearItem;
	private Boolean playing = false;
	Life life = new Life();

	GridBagConstraints c = new GridBagConstraints();
	
	public aFrame(){
		setLayout(new GridBagLayout());

		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
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
				life.generate();
				life.repaint();
			}
		}
		);
	    
	    clearItem = new JMenuItem("Clear");
	    clearItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Code Here
			}
		}
		);
		
	    //File menu
  		fileMenu = new JMenu("File");
  		menubar.add(fileMenu);
		fileMenu.add(playItem);
		fileMenu.add(stepItem);
		fileMenu.add(clearItem);
			
		//Panel Constructor
		c.weightx=1;
		c.weighty=1;
		c.fill = GridBagConstraints.BOTH;
	    getContentPane().add(life, c);
	}
}
