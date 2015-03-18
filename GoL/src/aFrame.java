import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class aFrame extends JFrame implements ActionListener, ComponentListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar;
	private JMenu sizeMenu;
	private JMenuItem  smallItem, mediumItem, largeItem;
	private JButton playButton, stepButton, clearButton;
	private JSlider sizeBar;
	private Boolean playing = false;											//True while auto-generating
	Life life = new Life();																//Sets up an instance of the JPanel containing Game of Life
	
	GridBagConstraints c = new GridBagConstraints();
	
	public aFrame(){
		
		addComponentListener(this);
		
		setLayout(new GridBagLayout());

		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
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
				
		//Size Menu
		sizeMenu = new JMenu("Size");
  		menubar.add(sizeMenu);
  		sizeMenu.add(smallItem);
  		sizeMenu.add(mediumItem);
  		sizeMenu.add(largeItem);
  		
  		//Buttons
     	playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setIcon(new ImageIcon("images/Play.gif"));
        stepButton = new JButton("Step");
        stepButton.addActionListener(this);
        stepButton.setIcon(new ImageIcon("images/Step.gif"));
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setIcon(new ImageIcon("images/Clear.gif"));
        sizeBar = new JSlider(JSlider.HORIZONTAL);
        sizeBar.setValue(10);
		sizeBar.addChangeListener(this);
		sizeBar.setMinimum(10);
		sizeBar.setMaximum(100);
		
		constructPanel();
	}

	public void constructPanel(){
		//Panel Constructor
        //Top buttons
        c.weightx=1;
		c.weighty=0;
		c.gridx=0;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
	    getContentPane().add(playButton, c);
	    c.weightx=1;
		c.weighty=0;
		c.gridx=1;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
	    getContentPane().add(stepButton, c);
		c.weightx=1;
		c.weighty=0;
		c.gridx=2;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
	    getContentPane().add(clearButton, c);
	    
	    //Grid Panel
	    c.weightx=1;
		c.weighty=1;
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=3;
		c.fill = GridBagConstraints.BOTH;
	    getContentPane().add(life, c);
	    
	    //Bottom buttons
		c.weightx=1;
		c.weighty=0;
		c.gridx=0;
		c.gridy=2;
		c.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(sizeBar,c);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((e.getSource() == this.playButton))	{
			if (playing.equals(false)){
				playing = true;

				if(this.getWidth() >= 400){
					playButton.setText("Pause");
				}
		        playButton.setIcon(new ImageIcon("images/Pause.gif"));
				life.startGenerating();
			}
			else{
				playing = false;
				if(this.getWidth() >= 400){
				playButton.setText("Play");
				}
		        playButton.setIcon(new ImageIcon("images/Play.gif"));
				life.stopGenerating();
			}
		}
	else if ((e.getSource() == this.stepButton))	{
		if (playing.equals(false)){
			life.generate();
			life.repaint();
		}
		else{
			playing = false;
			if(this.getWidth() >= 400){
				playButton.setText("Play");
				}
		        playButton.setIcon(new ImageIcon("images/Play.gif"));
			life.stopGenerating();
			life.generate();
			life.repaint();
		}
	}
	else if ((e.getSource() == this.clearButton)){
		life.clearBoard();
	}
}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		if(this.getWidth() < 400){
			playButton.setText("");
			stepButton.setText("");
			clearButton.setText("");
		}
		else{
			if (playing){
				playButton.setText("Pause");
			}
			else{
				playButton.setText("Play");
			}
			stepButton.setText("Step");
			clearButton.setText("Clear");
		}
		System.out.println("Width");
		System.out.println("Frame: "+ this.getWidth());
		System.out.println("Panel: "+ life.getWidth());
		System.out.println("Difference: "+ ( this.getWidth() - life.getWidth()));
		
		System.out.println("Height");
		System.out.println("Frame: "+ this.getHeight());
		System.out.println("Panel: "+ life.getHeight());
		System.out.println("Difference: "+ ( this.getHeight() - life.getHeight()));
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		life.resizeBoard(sizeBar.getValue());
		int boardSize = (((life.getCellSize()+1)*life.getboardSize())+1);
		this.setSize(boardSize + 20, boardSize + 112);
	}
}
