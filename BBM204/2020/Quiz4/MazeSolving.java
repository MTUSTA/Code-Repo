import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MazeSolving {
	static int N = 0;
	static int path_cost = -1;
	static int[] path = null;
	
	//print 2d array
	public static void printer_2d(int[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				System.out.print(maze[i][j]+ " ");
			}
			System.out.println();
		}	
	}
	// print shortest path
	public static void print_path() {
		System.out.print("Path:");
		for (int i = 0; i < path.length; i++) {
			if( i+1<path.length && path[i+1] != -1 ) {
				System.out.print(path[i]+"->"+path[i+1]+" ");
			}
		}
	}
	
	private static void shortest_path_finder(int[][] maze, int row, int column, int result, int[] pathF, int place) {
		
		result += maze[row][column];
		pathF[place] = row*N + column;
		//System.out.println(row +" "+column+ " "+maze[row][column]);
		//right
		if(column+1<N) {
			shortest_path_finder(maze, row, column+1, result, Arrays.copyOf(pathF, pathF.length),place+1);
		}
		//down
		if(row+1<N) {	
			shortest_path_finder(maze, row+1, column,result, Arrays.copyOf(pathF, pathF.length),place+1);		
		}
		if(row==4 && column == 4) {
			//System.out.println("----------------------------------------------------> "+result);
			if(path_cost == -1) {
				path_cost=result;
				path = pathF;
			}
			else if(result<path_cost) {
				path_cost=result;
				path = pathF;
			}
		}
	}	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(args[0]));
		// NxN matrix ---> parse N
		N = Integer.parseInt(sc.nextLine());
		path = new int[N*N];
		// create 2d array
		int[][] maze = new int[N][N];
		// read file and fill 2d array
		int row = 0;
		while(sc.hasNextLine()) {
			String[] splitted_line = sc.nextLine().split(",");
			for (int i = 0; i < N; i++) {
				maze[row][i]= Integer.parseInt(splitted_line[i]);
			}
			row++;
		}
		// creat path array maximum size
		int[] path_array = new int[N*N];
		Arrays.fill(path_array, -1);
		//process start
		shortest_path_finder(maze,0,0,0,path_array,0);
		//print result
		print_path();
	}
	
}
