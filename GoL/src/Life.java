import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Life extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Variables
	private Color cellOff = Color.BLACK;
	private Color cellOn = Color.WHITE;
	boolean[][] Cells = new boolean[][] {
			{false,true,false,false,false},
			{false,false,true,false,false},
			{true,true,true,false,false},
			{false,false,false,false,false},
			{false,false,false,false,false}
		};	
	int ylength = Cells.length;
	int xlength = Cells[0].length;
	int cellSize = 20;
	int boardSize = 5;
	int speed = 50;												//The speed at which the automatic generations happen, 1 = 1fps, 2 = 2fps...etc
	private Timer timer;
		
	Life(){
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
	public int CheckNeighbours (int x, int y){
		int[][] neighboursArray= {
				{-1,-1},	{0,-1},		{1,-1},
				{-1,0},						{1,0},
				{-	1,1},		{0,1},		{1,1}				
		};
		int neighbours = 0;
		for (int[] i : neighboursArray){
			try{
				if ( Cells[y+i[0]][x+i[1]] == true)
					neighbours++;
            } catch(ArrayIndexOutOfBoundsException f){
                continue;
            }
		}
		return neighbours;
	}
		
	public void generate(){
		boolean[][] tempCells = new boolean[ylength][xlength];
		for (int i = 0; i < ylength; i++){
			for (int j = 0; j < xlength; j++){
				tempCells[i][j] = Cells[i][j];
			}
		}
		for (int i = 0; i < ylength; i++){
			for (int j = 0; j < xlength; j++){
			int neighbours = CheckNeighbours(j,i);
			
			if (neighbours < 2 || neighbours > 3 ){ tempCells[i][j] = false;}
			else if (neighbours == 3){tempCells[i][j] = true;}
			else;
			}
		}
		Cells = tempCells;
	}
	
	public void startGenerating() {
	    timer = new Timer();
	    timer.schedule( new TimerTask(){
	        public void run(){
	            generate();
	            repaint();
	        }
	    },0,  1000/speed ); 
	}
	
	public void stopGenerating() {
	   timer.cancel();
	}
	
}