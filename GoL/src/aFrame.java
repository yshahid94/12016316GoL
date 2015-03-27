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

/**
 * aFrame is the class that controllers the simulations and displays them in a GUI form, it extends on the JFrame class
 * @author Yassin Shahid - 12016316
 * @see JFrame
 */	
public class aFrame extends JFrame implements ActionListener, ComponentListener{

	private static final long serialVersionUID = 1L;

	private JFileChooser fcOpen = new JFileChooser();
	private JMenuBar menubar;
	private JMenu fileMenu, sizeMenu, speedMenu, caMenu,torMenu;
	private JMenuItem  openItem, saveItem, smallItem, mediumItem, largeItem, slowItem, medItem, fastItem, hyperItem, GoLItem, plusItem, seedsItem, testItem, randomItem, randomTestItem,toroffItem,toronItem, test4Item;
	private JButton playButton, stepButton, clearButton;
	Life life = new Life(20,25);																//Sets up an instance of the JPanel containing Game of Life
	
	GridBagConstraints c = new GridBagConstraints();
	/**
	 * This is the constructor of the aFrame class, it sets the menubar of the frame, its layout and the content within it
	 */	
	public aFrame(){
		
		this.setTitle("Game of Life Project - Conway's");
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
		GoLItem = new JMenuItem("Conway's");
		GoLItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(0);
				setTitle("Game of Life Project - Conway's");
			}
		}
		);
		
		plusItem = new JMenuItem("Neumann");
		plusItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(1);
				setTitle("Game of Life Project - Neumann");
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
				setTitle("Game of Life Project - Seeds");
			}
		}
		);
		
		testItem = new JMenuItem("Test 1");
		testItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(3);
				setTitle("Game of Life Project - Test 1");
			}
		}
		);
		
		randomItem = new JMenuItem("Test 2");
		randomItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(4);
				setTitle("Game of Life Project - Test 2");
			}
		}
		);
		
		randomTestItem = new JMenuItem("Test 3");
		randomTestItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(5);
				setTitle("Game of Life Project - Test 3");
			}
		}
		);
		
		test4Item = new JMenuItem("Test 4");
		test4Item.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setCAType(6);
				setTitle("Game of Life Project - Test 4");
			}
		}
		);
		
		toroffItem = new JMenuItem("Off");
		toroffItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setTorus(false);
			}
		}
		);
		
		toronItem = new JMenuItem("On");
		toronItem.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				life.setTorus(true);
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
  		caMenu.add(testItem);
  		caMenu.add(randomItem);
  		caMenu.add(randomTestItem);
  		caMenu.add(test4Item);
  		
		//Torus Menu
  		torMenu = new JMenu("Torus");
  		menubar.add(torMenu);
  		torMenu.add(toronItem);
  		torMenu.add(toroffItem);
  		
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
	/**
	 * Restarts the generator if it is currently generating
	 */
	public void stopNStart(){
		if(life.getPlaying()){
			life.stopGenerating();
			life.startGenerating();
		}
		else{}
	}
	/**
	 * Resizes the window taking into account the current board and cell size
	 */
	public void resizeWindow(){
		int boardSize = (((life.getCellSize()+1)*life.getboardSize())+1);
		super.setSize(boardSize + 20, boardSize + 100);
	}
	
	/**
	 *  Constructs the panel with all the buttons and the cell grid
	 */
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
	}
	
	//File Handling
	/**
	 * Saves the current cells grid into a text file
	 */
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
	/**
	 * setStringToFile is used to save the input String to the selected location and as the selected name through the input file
	 * 
	 * @param file -		the location and name for the file are stored in this variable
	 * @param	saveString -	the string to be saved as text at the destination
	 * @return	returns true if the save was a success, or false if it was a failure
	 * @throws IOException if the FileWriter is unable to write the String into a text file
	 */	
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
				super.setTitle("Opened " + myfile.getAbsolutePath());
			} catch (Exception nfe) {
				super.setTitle("An error occured during opening");
			}
		} else {
			super.setTitle("Cancel file open");
		}
	}
	
	/**
	 * getCellsFromFile is used to open the selected file and return true or false depending on the outcome
	 * @param file -	the location and name for the file are stored in this variable
	 * @return	returns true if the open was a success, or false if it was a failure
	 * @throws IOException if the Scanner is unable to read the text file into array form
	 */
	public boolean getCellsFromFile(File file) {
		boolean opened = false;
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
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
	
	/**
	 * Fixes the play/pause button depending on whether or not the program is currently generating
	 */
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
	
	/**
	 * Handles any button press events on the frame
	 * and runs the set of code depending on the button pressed
	 * Play - Starts iterating through the cellular automata
	 * Pause - Stops iteration
	 * Step - Iterates once
	 * Clear - Clears the board
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
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
		if (!life.getPlaying()){
			fixPlayButton();
		}
	}
}

	/**
	 * Runs every the frame is resized
	 */
	@Override
	public void componentResized(ComponentEvent e) {
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
	}

	@Override
	public void componentMoved(ComponentEvent e) {}

	/**
	 * Resizes the window when the program is first ran
	 */
	@Override
	public void componentShown(ComponentEvent e) {
		resizeWindow();
	}

	@Override
	public void componentHidden(ComponentEvent e) {}
}
