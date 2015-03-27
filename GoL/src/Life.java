import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * Life is a class that handles all of the background processing of the Cellular Automata
 * @author Yassin Shahid - 12016316
 */	
public class Life extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;

	//Variables
	private Color cellOff = Color.BLACK;
	private Color cellOn = Color.WHITE;
	boolean[][] Cells = new boolean[][] {
			{false,true,false,false,false,false,false,false,false,false},
			{false,false,true,false,false,false,false,false,false,false},
			{true,true,true,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false}
		};	
	
	private int cellSize = 5;
	private int boardSize = 10;
	private int speed = 10;												//The speed at which the automatic generations happen, 1 = 1fps, 2 = 2fps...etc
	private Timer timer;
	private int ca = 0;														//Indicates what the current CA is being ran 0 for GoL, 1 for Neumann, 2 for Seeds, 3 for Test, 4 for Random, 5 for Random Test
	private boolean playing = false;								//True while auto-generating
	private boolean dragCell = false;
	private boolean torus = true;
	/**
	 * Constructs the life class without altering any of the variables
	 */
	Life(){
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/**
	 * Constructs the life class with certain modifiers
	 * @param boardSize - Changes the row and column size of the board
	 * @param cellSize - Changes the pixel length of each cell
	 */
	Life(int boardSize, int cellSize){
		addMouseListener(this);
		addMouseMotionListener(this);
		this.resizeBoard(boardSize);
		this.setCellSize(cellSize);
	}
	
	//Getters and Setters
	/**
	 * Returns the current cell array
	 * @return Cell array
	 */
	public boolean[][] getCells(){
		return Cells;
	}
	/**
	 * Returns the current cell array formatted for file saving usage
	 * @return Cell array in string format
	 */
	public String getFormatedCells(){
		String cellsString = "";
		for (int i = 0 ; i < boardSize; i++){			//Going through the Y axis
			for (int j = 0 ; j < boardSize; j++){		//Going through the X axis
				cellsString += Boolean.toString(Cells[i][j]);
				if (j == boardSize -1){
					cellsString += ";\r\n";
				}
				else{
					cellsString += ",";					
				}
			}
		}
		return cellsString;
	}
	/**
	 * Returns true if program is currently iterating or false if not
	 * @return The current state of program
	 */
	public boolean getPlaying(){
		return playing;
	}
	/**
	 * Sets the program to the selected Cellular automata type
	 * @param ca - Cellular automata
	 */
	public void setCAType(int ca){
		this.ca = ca;
	}
	
	/**
	 * Returns the current cell size in pixels
	 * @return Cell size
	 */
	public int getCellSize(){
		return cellSize;
	}
	
	/**
	 * Sets the cell size in pixels
	 * @param cellSize
	 */
	public void setCellSize(int cellSize){
		this.cellSize = cellSize;
	}
	
	/**
	 * Returns the current Board size of the cell grid
	 * @return Board size
	 */
	public int getboardSize(){
		return boardSize;
	}
	
	/**
	 * Sets the board size of the cell grid
	 * @param boardSize
	 */
	public void setBoardSize(int boardSize){
		this.boardSize = boardSize;
	}
	
	/**
	 * Sets the speed of how fast the grid iterates
	 * @param speed
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 *  Applys the given array of cells onto the current grid
	 * @param Cells
	 */
	public void applyArray(boolean Cells[][]){
		resizeBoard(Cells[0].length);
		this.Cells = Cells;
		repaint();
	}
	
	public void setTorus(boolean torus){
		this.torus = torus;
	}
	
	/**
	 * Returns whether or not the grid is acting like a torus shape
	 * @return Torus
	 */
	public boolean getTorus(){
		return torus;
	}
	
	//Display
	
	/**
	 * Handles all of the drawing onto the Panel
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//Draw Board
		for (int i = 0 ; i < boardSize; i++){			//Going through the Y axis
			for (int j = 0 ; j < boardSize; j++){		//Going through the X axis
				if (Cells[i][j]){
					g.setColor(cellOn);
				}
				else{
					g.setColor(cellOff);
				}
				g.fillRect(offsetcalc(j), offsetcalc(i), cellSize, cellSize);
			}
		}
	}

	//Background
	/**
	 * Returns the co-ordinate where the cells will start and finish depend on how big the cell size is
	 * @param point
	 * @return Offset point
	 */
	public int offsetcalc(int point){
		return point+(cellSize*(point)+1);
	}
	
	/**
	 * Checks whether or not the given cell is within the boundaries of the array
	 * @param xCell
	 * @param yCell
	 * @return true or false depending on whether or not it is within the boundaries
	 */
	public boolean cellInBound(int xCell, int yCell){
		return (yCell < boardSize && yCell >= 0 && xCell < boardSize && xCell >= 0);
	}
	
	/**
	 * Toggles the cell at the given x and y element within Cells
	 * @param x
	 * @param y
	 */
	public void toggleCell (int x, int y){
		if (Cells[y][x] == true){
			Cells[y][x] = false;
		}
		else{
			Cells[y][x] = true;
		}
	}
	
	/**
	 * Clears the board
	 */
	public void clearBoard(){
			for (int i = 0 ; i < boardSize; i++){			//Going through the Y axis
				for (int j = 0 ; j < boardSize; j++){		//Going through the X axis
					Cells[i][j] = false;
				}
			}
			repaint();
	}
	
	/**
	 * Resizes the board to the given new size
	 * @param newSize
	 */
	public void resizeBoard(int newSize){
		int smallerSize = boardSize;
		if (newSize < boardSize){
			smallerSize = newSize;
		}
		boolean[][] tempCells = new boolean[newSize][newSize];
		for (int i = 0; i < smallerSize; i++){
			for (int j = 0; j < smallerSize; j++){
				tempCells[i][j] = Cells[i][j];
			}
		}
		boardSize = newSize;
		Cells = tempCells;
		repaint();
	}
	/**
	 * Handles array out of bound exceptions
	 * and redirects them to the cells on the opposite end to create the torus shape
	 * @param x
	 * @param y
	 * @return The cell on the other end
	 */
	public boolean gridLoop(int x, int y){
		int tempx = x, tempy = y;
		if( x < 0){													//If trying to grab cell from left
			tempx += (boardSize-1);
		}
		if(x >boardSize -1){									//If trying to grab cell from right
			tempx -= (boardSize-1);
		}
		if(y < 0){														//If trying to grab cell from top
			tempy += (boardSize-1);
		}
		if(y > boardSize -1){									//If trying to grab cell from bottom
			tempy -= (boardSize-1);
		}
		return Cells[tempx][tempy];
	}
	
	/**
	 * Checks the Moore neighbourhood of the given cell and applies the Game of Life rules on them
	 * @param x
	 * @param y
	 * @return The outcome of the rules
	 */
	public boolean checkGoLNeighbours (int x, int y){
		int[][] neighboursArray= {
				{-1,-1},	{0,-1},		{1,-1},
				{-1,0},						{1,0},
				{-	1,1},		{0,1},		{1,1}	
		};
		int neighbours = 0;
		for (int[] i : neighboursArray){
			try{
				if ( Cells[x+i[0]][y+i[1]] == true)
					neighbours++;
            } catch(ArrayIndexOutOfBoundsException f){
            	if (torus){
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
            	}
                continue;
            }
		}
		if (neighbours < 2 || neighbours > 3 ){ return false;}
		else if (neighbours == 3){ return true;}
		else return Cells[x][y];
	}
	
	/**
	 * Applies the Game of Life rules on an extended Von Neumann neighbourhood
	 * @param x
	 * @param y
	 * @return The outcome of the rules
	 */
	public boolean checkPlusNeighbours (int x, int y){
		int[][] neighboursArray= {
												{0,-2},
												{0,-1},
				{-2,0},		{-1,0},						{1,0},		{2,0},
												{0,1}	,
												{0,2}
		};
		int neighbours = 0;
		for (int[] i : neighboursArray){
			try{
				if ( Cells[x+i[0]][y+i[1]] == true)
					neighbours++;
            } catch(ArrayIndexOutOfBoundsException f){
            	if (torus){
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
            	}
            	continue;
            }
		}
		if (neighbours < 2 || neighbours > 3 ){ return false;}
		else if (neighbours == 3){ return true;}
		else return Cells[x][y];
	}
	
	/**
	 * Checks the Moore neighbourhood of the given cell and applies the Seeds rules on them
	 * @param x
	 * @param y
	 * @return The outcome of the rules
	 */
	public boolean checkSeedsNeighbours (int x, int y){
		int[][] neighboursArray= {
				{-1,-1},	{0,-1},		{1,-1},
				{-1,0},						{1,0},
				{-	1,1},		{0,1},		{1,1}	
		};
		int neighbours = 0;
		for (int[] i : neighboursArray){
			try{
				if ( Cells[x+i[0]][y+i[1]] == true)
					neighbours++;
            } catch(ArrayIndexOutOfBoundsException f){
            	if (torus){
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
            	}
                continue;
            }
		}
		if (neighbours == 2 && !Cells[x][y]){ return true;}
		else return false;
	}
	
	/**
	 * Checks the Moore neighbourhood at the radius of 2 and applies test rules on them
	 * @param x
	 * @param y
	 * @return The outcome of the rules
	 */
	public boolean checkTestNeighbours (int x, int y){
		int[][] neighboursArray= {
				{-2,-2},	{-1,-2},	{0,-2},		{1,-2},	{2,-2},
				{-2,-1},	{-1,-1},	{0,-1},		{1,-1},	{2,-1},
				{-2,0},		{-1,0},						{1,0},	{2,0},
				{-2,1},		{-1,1},		{0,1},		{1,1}	,	{2,1},
				{-2,2},		{-1,2},		{0,2},		{1,2}	,	{2,2}
		};
		int neighbours = 0;
		for (int[] i : neighboursArray){
			try{
				if ( Cells[x+i[0]][y+i[1]] == true)
					neighbours++;
            } catch(ArrayIndexOutOfBoundsException f){
            	if (torus){
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
            	}
                continue;
            }
		}
		if (neighbours  > 3 ){ return false;}
		else if (neighbours == 1){ return true;}
		else return Cells[x][y];
	}
	
	/**
	 * Checks the Moore neighbourhood at the radius of 3 and applies test rules on them
	 * @param x
	 * @param y
	 * @return The outcome of the rules
	 */
	public boolean checkTest4Neighbours (int x, int y){
		int[][] neighboursArray= {
				{-2,-2},	{-1,-2},	{0,-2},		{1,-2},	{2,-2},
				{-2,-1},	{-1,-1},	{0,-1},		{1,-1},	{2,-1},
				{-2,0},		{-1,0},						{1,0},	{2,0},
				{-2,1},		{-1,1},		{0,1},		{1,1}	,	{2,1},
				{-2,2},		{-1,2},		{0,2},		{1,2}	,	{2,2}
		};
		int neighbours = 0;
		for (int[] i : neighboursArray){
			try{
				if ( Cells[x+i[0]][y+i[1]] == true)
					neighbours++;
            } catch(ArrayIndexOutOfBoundsException f){
            	if (torus){
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
            	}
                continue;
            }
		}
		if (neighbours < 2 || neighbours > 3 ){ return false;}
		else if (neighbours == 3){ return true;}
		else return Cells[x][y];
	}
	
	/**
	 * Iterates through the cell grid once using the rules selected within the frame
	 */
	public void generate(){
		boolean[][] tempCells = new boolean[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				tempCells[i][j] = Cells[i][j];
			}
		}
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (ca == 0){																		//Runs when Conway's is selected
					tempCells[i][j] = checkGoLNeighbours(i,j);
				}
				else if (ca == 1){																//Runs when Neumann is selected
					tempCells[i][j] = checkPlusNeighbours(i,j);
				}
				else if (ca == 2){																//Runs when Seeds is selected
					tempCells[i][j] = checkSeedsNeighbours(i,j);
				}
				else if (ca == 3){																//Runs when Test 1 is selected
					tempCells[i][j] = checkTestNeighbours(i,j);
				}
				else if (ca == 6){																//Runs when Test 4 is selected
					tempCells[i][j] = checkTest4Neighbours(i,j);
				}
				else if (ca == 4){																//Runs when Test 2 is selected
					if (Cells[i][j] == true){
						tempCells[i][j] = false;
						Random rand = new Random();
						int direction = rand.nextInt(4);								//Generates a random number from 0 to 3
						switch(direction){														//Uses the number and moves the cell up, down, left or right depending on the generated number
						case 0:
							try{
							tempCells[i+1][j] = true;
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
								tempCells[(i+1)-boardSize][j] = true;
								}
				                continue;
				            }
							break;
						case 1:
							try{
							tempCells[i-1][j] = true;
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
								tempCells[(i-1)+boardSize][j] = true;
								}
				                continue;
				            }
							break;
						case 2:
							try{
							tempCells[i][j+1] = true;
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
								tempCells[i][(j+1)-boardSize] = true;
								}
				                continue;
				            }
							break;
						case 3:
							try{
							tempCells[i][j-1] = true;
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
								tempCells[i][(j-1)+boardSize] = true;
								}
				                continue;
				            }
							break;
						}
					}
				}
				else if (ca == 5){																//Runs when Test 3 is selected
					if (Cells[i][j] == true){
						Random rand = new Random();
						int direction = rand.nextInt(4);								//Generates a random number from 0 to 3
						switch(direction){														//Uses the number and moves the cell up, down, left or right depending on the generated number if the said space is available
						case 0:
							try{
							if (tempCells[i+1][j] == false){
							tempCells[i+1][j] = true;
							tempCells[i][j] = false;
							}
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
									if (tempCells[(i+1)-boardSize][j] == false){
										tempCells[(i+1)-boardSize][j] = true;
										tempCells[i][j] = false;
									}
								}
				                continue;
				            }
							break;
						case 1:
							try{
							if (tempCells[i-1][j] == false){
							tempCells[i-1][j] = true;
							tempCells[i][j] = false;
							}
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
									if(tempCells[(i-1)+boardSize][j] == false){
										tempCells[(i-1)+boardSize][j] = true;
										tempCells[i][j] = false;
									}
								}
				                continue;
				            }
							break;
						case 2:
							try{
							if(tempCells[i][j+1] == false){
								tempCells[i][j+1] = true;
								tempCells[i][j] = false;
							}
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
									if(tempCells[i][(j+1)-boardSize] == false){
										tempCells[i][(j+1)-boardSize] = true;
										tempCells[i][j] = false;
									}
								}
				                continue;
				            }
							break;
						case 3:
							try{
							if (tempCells[i][j-1] == false){
								tempCells[i][j-1] = true;
								tempCells[i][j] = false;
							}
							} catch(ArrayIndexOutOfBoundsException f){
								if (torus){
									if(tempCells[i][(j-1)+boardSize] ==false){
										tempCells[i][(j-1)+boardSize] = true;
										tempCells[i][j] = false;
									}
								}
				                continue;
				            }
							break;
						}
					}
				}
			}
		}
		Cells = tempCells;
	}
	
	/**
	 * Starts the iteration process
	 */
	public void startGenerating() {
		playing = true;
	    timer = new Timer();
	    timer.schedule( new TimerTask(){
	        public void run(){
	            generate();
	            repaint();
	        }
	    },0,  1000/speed ); 
	}
	
	/**
	 * Stops the iteration process
	 */
	public void stopGenerating() {
		if (playing == true){
			timer.cancel();
			playing = false;
		}
	}

	/**
	 * Handles mouse clicks within the panel
	 * and toggles the one cell clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		int ms_xCord = e.getX();
        int ms_yCord = e.getY();
        
        int xCell = ms_xCord/(cellSize+1);
        int yCell = ms_yCord/(cellSize+1);
        
        if (cellInBound(xCell,yCell)){
        toggleCell(xCell, yCell);
        repaint();
        }
	}
	
	/**
	 * Handles mouse presses and saves whether the first mouse press before a drag was alive or dead
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int ms_xCord = e.getX();
        int ms_yCord = e.getY();
        
        int xCell = ms_xCord/(cellSize+1);
        int yCell = ms_yCord/(cellSize+1);

        if (cellInBound(xCell,yCell)){
	        if (Cells[yCell][xCell]){
	        	dragCell = false;
	        }
	        else{
	        	dragCell = true;
	        }
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Handles mouse drags and changes the state of each cell dragged over
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int ms_xCord = e.getX();
        int ms_yCord = e.getY();
        
        int xCell = ms_xCord/(cellSize+1);
        int yCell = ms_yCord/(cellSize+1);
        
        if (cellInBound(yCell,xCell)){
            Cells[yCell][xCell] = dragCell;
        }
        repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
}