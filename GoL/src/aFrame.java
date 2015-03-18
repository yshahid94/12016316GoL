import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class aFrame extends JFrame implements ActionListener, ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFileChooser fcOpen = new JFileChooser();
	private JMenuBar menubar;
	private JMenu fileMenu, sizeMenu, speedMenu, caMenu;
	private JMenuItem  openItem, saveItem, smallItem, mediumItem, largeItem, slowItem, medItem, fastItem, hyperItem, GoLItem, plusItem, seedsItem;
	private JButton playButton, stepButton, clearButton;
	Life life = new Life();																//Sets up an instance of the JPanel containing Game of Life
	
	GridBagConstraints c = new GridBagConstraints();
	
	public aFrame(){
		
		addComponentListener(this);
		
		setLayout(new GridBagLayout());

		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
	    //File Menu
		openItem = new JMenuItem("Open");
		openItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		}
		);
	    
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
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
				life.setCellSize(25);
				life.resizeBoard(20);
				resizeWindow();
			}
		}
		);
		
		mediumItem = new JMenuItem("Medium");
		mediumItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCellSize(10);
				life.resizeBoard(50);
				resizeWindow();
			}
		}
		);
		
		largeItem = new JMenuItem("Large");
		largeItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCellSize(5);
				life.resizeBoard(100);
				resizeWindow();
			}
		}
		);
		
		//Speed Menu
		slowItem = new JMenuItem("Slow");
		slowItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setSpeed(1);
				stopNStart();
			}
		}
		);
		
		medItem = new JMenuItem("Medium");
		medItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setSpeed(5);
				stopNStart();
			}
		}
		);
		
		fastItem = new JMenuItem("Fast");
		fastItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setSpeed(20);
				stopNStart();
			}
		}
		);
		
		hyperItem = new JMenuItem("Hyper");
		hyperItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setSpeed(100);
				stopNStart();
			}
		}
		);
		
		//CA Menu
		GoLItem = new JMenuItem("GoL");
		GoLItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(0);
			}
		}
		);
		
		plusItem = new JMenuItem("Plus");
		plusItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(1);
			}
		}
		);
		
		seedsItem = new JMenuItem("Seeds");
		seedsItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(2);
			}
		}
		);
	    			
		//File Menu
	    fileMenu = new JMenu("File");
  		menubar.add(fileMenu);
  		fileMenu.add(openItem);
  		fileMenu.add(saveItem);
  		
  		//Size Menu
  		sizeMenu = new JMenu("Grid");
  		menubar.add(sizeMenu);
  		sizeMenu.add(smallItem);
  		sizeMenu.add(mediumItem);
  		sizeMenu.add(largeItem);
  		
  		//Speed Menu
  		speedMenu = new JMenu("Speed");
  		menubar.add(speedMenu);
  		speedMenu.add(slowItem);
  		speedMenu.add(medItem);
  		speedMenu.add(fastItem);
  		speedMenu.add(hyperItem);
  		
		//CA Menu
  		caMenu = new JMenu("CA");
  		menubar.add(caMenu);
  		caMenu.add(GoLItem);
  		caMenu.add(plusItem);
  		caMenu.add(seedsItem);
  		
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
        		
		constructPanel();
	}
	
	public void stopNStart(){
		if(life.getPlaying()){
			life.stopGenerating();
			life.startGenerating();
		}
		else{}
	}
	
	public void resizeWindow(){
		int boardSize = (((life.getCellSize()+1)*life.getboardSize())+1);
		super.setSize(boardSize + 20, boardSize + 100);
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
		c.weightx=0.5;
		c.weighty=0;
		c.gridx=0;
		c.gridy=2;
		c.fill = GridBagConstraints.HORIZONTAL;
	}
	
	//File Handling
	
	public void saveFile() {
		int result = fcOpen.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File myfile = fcOpen.getSelectedFile();
			
			if (setStringToFile(myfile,life.getFormatedCells()))
			super.setTitle("Single saved " + myfile.getAbsolutePath());
		} else {
			super.setTitle("Canceled file save");
		}
	}
	public boolean setStringToFile(File file, String saveString)
	{
		boolean saved = false;
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(file));
			try
			{
				bw.write(saveString);
				saved = true;
			}
			finally{bw.close();}
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		return saved;
	}
	
	public void openFile() {

		int result = fcOpen.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File myfile = fcOpen.getSelectedFile();
			try {
				getCellsFromFile(myfile);
				super.setTitle("opened " + myfile.getAbsolutePath());
			} catch (Exception nfe) {
				super.setTitle("An error occured during opening");
			}
		} else {
			super.setTitle("Cancel file open");
		}
	}
	public boolean getCellsFromFile(File file) {
		boolean opened = false;
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
		try 
		{
			
			try
			{
				boolean[][] cells = null;
				String line;
		        int x = 0;
		        int size = 0;

		        while ((line = buff.readLine()) != null) {
		            String[] cellLine = line.trim().split(",");

		            if (cells == null) {
		                size = cellLine.length;
		                cells = new boolean[size][size];
		            }
		            for (int y = 0; y < size; y++) {
		                cells[x][y] = Boolean.valueOf((cellLine[y]));
		            }
		            x++;
		        }
				life.applyArray(cells);
				
				resizeWindow();
				
				opened = true;
			}
			finally{buff.close(); stream.close();;}
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		return opened;
	}
	
	public void fixPlayButton(){
		if (life.getPlaying()){
			if(this.getWidth() >= 400){
				playButton.setText("Pause");
			}
	        playButton.setIcon(new ImageIcon("images/Pause.gif"));
		}
		else{
			if(this.getWidth() >= 400){
				playButton.setText("Play");
			}
	        playButton.setIcon(new ImageIcon("images/Play.gif"));
		}
	}
	
	//Handlers
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((e.getSource() == this.playButton))	{
			if (life.getPlaying()){
				
				if(this.getWidth() >= 400){
					playButton.setText("Play");
				}
		        playButton.setIcon(new ImageIcon("images/Play.gif"));
				life.stopGenerating();
			}
			else{

				if(this.getWidth() >= 400){
					playButton.setText("Pause");
				}
		        playButton.setIcon(new ImageIcon("images/Pause.gif"));
				life.startGenerating();
			}
		}
	else if ((e.getSource() == this.stepButton))	{
		if (life.getPlaying()){
			life.stopGenerating();
			life.generate();
			fixPlayButton();
		}
		else{
			life.generate();
		}
		life.repaint();
	}
	else if ((e.getSource() == this.clearButton)){
		life.clearBoard();
		life.stopGenerating();
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
			if (life.getPlaying()){
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

	
}
