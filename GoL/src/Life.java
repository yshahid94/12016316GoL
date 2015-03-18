import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class Life extends JPanel implements MouseListener, MouseMotionListener{
	/**
	 * 
	 */
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
	private int ca = 0;
	private boolean playing = false;								//True while auto-generating
	private boolean dragCell = false;
		
	Life(){
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public boolean[][] getCells(){
		return Cells;
	}
	
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
	
	public boolean getPlaying(){
		return playing;
	}
	
	public void setPlaying(boolean playing){
		this.playing = playing;
	}
	
	public int getCAType(){
		return ca;
	}
	
	public void setCAType(int ca){
		this.ca = ca;
	}
	
	public int getCellSize(){
		return cellSize;
	}
	
	public void setCellSize(int cellSize){
		this.cellSize = cellSize;
	}
	
	public int getboardSize(){
		return boardSize;
	}
	
	public void setBoardSize(int boardSize){
		this.boardSize = boardSize;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
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
	
	public int offsetcalc(int point){
		return point+(cellSize*(point)+1);
	}
		
	//Background Stuff
	
	public boolean cellInBound(int xCell, int yCell){
		return (yCell < boardSize && yCell >= 0 && xCell < boardSize && xCell >= 0);
	}
	
	public void toggleCell (int x, int y){
		if (Cells[y][x] == true){
			Cells[y][x] = false;
		}
		else{
			Cells[y][x] = true;
		}
	}
	
	public void clearBoard(){
			for (int i = 0 ; i < boardSize; i++){			//Going through the Y axis
				for (int j = 0 ; j < boardSize; j++){		//Going through the X axis
					Cells[i][j] = false;
				}
			}
			repaint();
	}
	
	public void applyArray(boolean Cells[][]){
		boardSize = Cells[0].length;
		this.Cells = Cells;
		repaint();
	}
	
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
	
	public boolean gridLoop(int x, int y){
		int tempx = x, tempy = y;
		if( x < 0){													//If trying to grab cell from left
			tempx += (boardSize-1);
		}
		if(x >boardSize -1){								//If trying to grab cell from right
			tempx -= (boardSize-1);
		}
		if(y < 0){												//If trying to grab cell from top
			tempy += (boardSize-1);
		}
		if(y > boardSize -1){								//If trying to grab cell from bottom
			tempy -= (boardSize-1);
		}
		System.out.println("hi");
		boolean test = Cells[tempx][tempy];
		return Cells[tempx][tempy];
	}
	
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
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
                continue;
            }
		}
		if (neighbours < 2 || neighbours > 3 ){ return false;}
		else if (neighbours == 3){ return true;}
		else return Cells[x][y];
	}
	
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
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
            	continue;
            }
		}
		if (neighbours < 2 || neighbours > 3 ){ return false;}
		else if (neighbours == 3){ return true;}
		else return Cells[x][y];
	}
	
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
            	if (gridLoop(x+i[0],y+i[1])){
            		neighbours++;
            		}
                continue;
            }
		}
		if (neighbours == 2 && !Cells[x][y]){ return true;}
		else return false;
	}
		
	public void generate(){
		boolean[][] tempCells = new boolean[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				tempCells[i][j] = Cells[i][j];
			}
		}
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (ca == 0){
					tempCells[i][j] = checkGoLNeighbours(i,j);
				}
				else if (ca == 1){
					tempCells[i][j] = checkPlusNeighbours(i,j);
				}
				else if (ca == 2){
					tempCells[i][j] = checkSeedsNeighbours(i,j);
				}
			}
		}
		Cells = tempCells;
	}
	
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
	
	public void stopGenerating() {
		if (playing == true){
			timer.cancel();
			playing = false;
		}
	}

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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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