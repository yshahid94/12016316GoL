
public class Life {

	 boolean[][] Cells = new boolean[][] {
		{false,true,false,false,false},
		{false,false,true,false,false},
		{true,true,true,false,false},
		{false,false,false,false,false},
		{false,false,false,false,false}
	};	
	 int ylength = Cells.length;
	 int xlength = Cells[0].length;
	
	public Life()
	{
	}
	
	public boolean[][] getCells(){
		return Cells;
	}

	public int CheckNeighbours (int x, int y){
		int[][] neighboursArray= {
				{-1,-1},{0,-1},{1,-1},
				{-1,0},{1,0},
				{-	1,1},{0,1},{1,1}				
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
	
//	public void display(){
//		for (int i = 0; i < ylength; i++){
//			for (int j = 0; j < xlength; j++){
//			if (Cells[i][j] == true){ System.out.print("X\t");}
//			else System.out.print("-\t");
//			}
//			System.out.println();
//		}
//	}
	

}
