class Sudoku
{ 
	public static boolean isSafe(int[][] a,int row, int col,int num)  
	{ 
		for(int d=0; d<a.length;d++)  
			if(a[row][d]==num || a[d][col]==num) //Number placed should not exist in the row and column    
				return false; 
		int s=(int)Math.sqrt(a.length); //length of sub-grid in this case N=9 => s=3 
		int rs=row-row%s; //initial row-boundary of grid
		int cs=col-col% s; //initial column-boundary of grid
		//Checking for each inner sub-grid 
		for(int r=rs;r<(rs+ s);r++)  
			{ 
				for(int d=cs;d<(cs+s);d++)  
					{ 
						if(a[r][d]==num)  
							return false; 
					} 
			} 
    return true; 
	} 
	public static boolean solveSudoku(int[][] a, int n)  
	{ 
		int row=-1; 
		int col=-1; 
		boolean isEmpty=true; 
		for(int i=0;i<n;i++) 
			{ 
				for(int j=0;j<n;j++)  
					{ 
						if(a[i][j]==0)  
						{ 
							row=i; 
							col=j; 
							isEmpty = false;  
							break; 
						} 
					} 
				if(!isEmpty) 
				break; 
			} 
		// Checking for empty space left in Grid
		if (isEmpty)  
			return true; 
    // else for each-row backtrack 
		for (int num=1;num<=n;num++) 
			{ 
				if(isSafe(a,row,col,num)) 
				{ 
					a[row][col]=num; //assigning num value after satisfying isSafe function
					if(solveSudoku(a, n))  
					{ 
						// printing (a, n); 
						return true; 
					}  
					else
					{ 
                a[row][col] = 0; // replacing it with zero
					} 
				} 
			} 
		return false; 
	} 
	public static void print(int[][] a, int N) 
	{ 
		//Matrix Printing in Sudoku Grid Format
		for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++) 
					{
						if(j==3||j==6)
						System.out.print(" | ");
						System.out.print(a[i][j]+" ");
					}
				if(i==2||i==5) 
				{
					System.out.println();
					for(int k=0;k<N;k++)
						System.out.print("---");
				}
				System.out.println();
			}
	} 
	// Driver Code 
	public static void main(String args[]) 
	{ 
		int[][] a =new int[][] 
		{ 
        {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
        {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
        {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
        {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
        {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
        {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
        {0, 0, 5, 2, 0, 6, 3, 0, 0} 
		}; 
		int N=a.length; 
		System.out.println("\nGiven Input Sudoko:\n");
		print(a,N);
		System.out.println("\nSolution for Given Sudoko:\n");
		if(solveSudoku(a, N)) 
			print(a, N); // print solution 
		else
			System.out.println("No solution for given Sudoku Grid"); 
	} 
} 