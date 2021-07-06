import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	/**
	 * Unit tests the {@code Graph} data type.
	 *
	 * @param args the command-line arguments
	 * @throws FileNotFoundException
	 */
	public static HashMap<String, Integer> capacity = new HashMap<String, Integer>();
	public static HashMap<String, Integer> dict = new HashMap<String, Integer>();
	public static HashMap<Integer, String> dict2 = new HashMap<Integer, String>();

	public static HashMap<Integer, Integer> used = new HashMap<Integer, Integer>();
	// public static HashMap<Integer, ArrayList<Integer>> Graph_Structure = new
	// HashMap<Integer, ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File(args[0]));
		FileWriter writer = new FileWriter(new File(args[1]));
		
		String line = sc.nextLine();
		String[] splitted = line.split(" ");
		int max = 0;
		// save capacity and transform vertex string to int
		for (int i = 0; i < splitted.length; i += 2) {

			int value = Integer.parseInt(splitted[i + 1]);
			capacity.put(splitted[i], value);
			dict.put(splitted[i], i / 2);
			dict2.put(i / 2, splitted[i]);
			used.put(i / 2, 0);
			if(value>max) {
				max = value;
			}

		}

		int vertices_number = splitted.length / 2;
		// starting node for input.txt
		String starting_node = sc.nextLine();


		ArrayList<String[]> edge_lines = new ArrayList<String[]>();
		// read input.txt line
		while (sc.hasNext()) {
			String line2 = sc.nextLine();
			String[] splitted_line = line2.split(" ");
			edge_lines.add(splitted_line);
		}

		// edge number
		int edges_number = edge_lines.size();
		Graph GraphStructure = new Graph(vertices_number);
		// election graph
		Graph G2 = new Graph(vertices_number);

		if (edges_number < 0)
			throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
		
		// checking cycle if it cycle no adding edge
		for (int i = 0; i < edges_number; i++) {

			int v = dict.get(edge_lines.get(i)[0]);
			int w = dict.get(edge_lines.get(i)[1]);
			// i need use v
			if (used.get(v) <= capacity.get(dict2.get(v))) {
				// only for graph structure
				GraphStructure.addEdge(v, w);
				// create new graph without reference
				Graph tGraph = new Graph(G2);
				tGraph.addEdge(v, w);
				// if tGraph has cycle no adding
				Cycle finder = new Cycle(tGraph);
				if (finder.hasCycle()) {

				} else {
					G2.addEdge(v, w);
					used.replace(v, used.get(v) + 1);
				}

			}

		}

		HashMap<Integer, ArrayList<Integer>> Gstructure = GraphStructure.toString2();
		
		writer.write("Graph structure:\n");
		// print all graph structure
		for (Integer g : Gstructure.keySet()) {
			String harf = dict2.get(g);
			writer.write(harf + "(" + capacity.get(harf) + ")" + "-->");

			for (int i = Gstructure.get(g).size() - 1; i >= 0; i--) {
				writer.write(dict2.get(Gstructure.get(g).get(i)) + " ");
			}
			writer.write("\n");
		}
		// all vertices
		HashMap<Integer, ArrayList<Integer>> G2s = G2.toString2();

		//
		writer.write("Broadcast steps:\n");

		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> will_visit = new ArrayList<Integer>();
		// starting node adding arraylist
		will_visit.add(dict.get(starting_node));
		// BFS object
		BreadthFirstPaths bfs = null;

		while (will_visit.size() > 0) {
			int start_node = will_visit.get(0);

			// bfs
			bfs = new BreadthFirstPaths(G2, start_node);
			for (int v = 0; v < G2.V(); v++) {
				if (bfs.hasPathTo(v)) {
					// path stack
					Stack<Integer> stack1 = (Stack<Integer>) bfs.pathTo(v);

					for (int x : stack1) {
						// limit size
						if (stack1.size() == 2) {
							if (x == start_node && !visited.contains(x)) {
								visited.add(x);
								will_visit.remove(new Integer(x));

							} else if (!visited.contains(x)) {
								// adding arraylist
								will_visit.add(x);
								// remove visited
								if (G2s.get(x) != null && G2s.get(x).contains(new Integer(start_node))) {
									G2s.get(x).remove(new Integer(start_node));
								}
							}
						}

					}
				}

				else {
					StdOut.printf("%d to %d (-):  not connected\n", start_node, v);
				}
			}
		}
		
		// fix output  
		ArrayList<Integer> visited2 = new ArrayList<Integer>();
		ArrayList<Integer> will_visit2 = new ArrayList<Integer>();
		will_visit2.add(dict.get(starting_node));

		while (will_visit2.size() > 0) {
			int g = will_visit2.get(0);
			will_visit2.remove(0);
			String harf = dict2.get(g);
			if (G2s.get(g).size() != 0) {
				writer.write(harf + "-->");
				visited2.add(g);
				for (int i = G2s.get(g).size() - 1; i >= 0; i--) {
					writer.write(dict2.get(G2s.get(g).get(i)) + " ");
					will_visit2.add(G2s.get(g).get(i));

				}
				writer.write("\n");

			} else {
				visited2.add(g);
			}
		}
		
		writer.write("Message passing:\n");


		int strt = dict.get(starting_node);
		// bfs
		BreadthFirstPaths bfs2 = new BreadthFirstPaths(G2, strt);

		for (int v = visited2.size() - 1; v >= 0; v--) {
			// calculate every path
			if (bfs2.hasPathTo(visited2.get(v))) {
				Stack<Integer> stack1 = (Stack<Integer>) bfs2.pathTo(visited2.get(v));
				String[] splitted_stack1 = stack1.toString().split(" ");
				int[] array = Arrays.stream(splitted_stack1).mapToInt(Integer::parseInt).toArray();

				if (array.length > 1) {
					writer.write(dict2.get(array[array.length - 1]) + "--->[" + dict2.get(array[array.length - 1])
							+ "," + capacity.get(dict2.get(array[array.length - 1])) + "]--->"
							+ dict2.get(array[array.length - 2])+"\n");
				}

			}
		}
		
		// best node
		ArrayList<String> best_node = new ArrayList<String>(); 
		for (String s : capacity.keySet()) {
			if(capacity.get(s)== max) {
				best_node.add(s);
			}
			
		}
		writer.write("Best node-->");
		String b = "";
		for (String s : best_node) {
			b += s+", ";
		}
		// set string 
		b = b.substring(0, b.length()-2);
		writer.write(b+"\n");
		
		// possible roots and calculate min cost
		HashMap<Integer, Integer> cost = new HashMap<Integer, Integer>();
		int min = -1;
		for (int j = 0; j < visited2.size(); j++) {
			int state = visited2.get(j);
			// bfs for every node
			BreadthFirstPaths bfs3 = new BreadthFirstPaths(G2, state);
			int total = 0;
			// paths for every node
			for (int k = 0; k < visited2.size(); k++) {
				if (bfs3.hasPathTo(visited2.get(k))) {
					// path = stack
					Stack<Integer> stack1 = (Stack<Integer>) bfs3.pathTo(visited2.get(k));
					// stack to string array
					String[] splitted_stack1 = stack1.toString().split(" ");
					// string array to int array
					int[] array = Arrays.stream(splitted_stack1).mapToInt(Integer::parseInt).toArray();
					total += array.length - 1;
				}
			}
			if(min == -1 || total < min) {
				// set min value
				min = total;
			}
			cost.put(state, total);
		}
		
		writer.write("Possible roots-->");
		String last = "";
		for (Integer i : cost.keySet()) {
			if(cost.get(i) == min) {
				last += dict2.get(i)+", ";	
			}
		}
		last = last.substring(0, last.length()-2);

		writer.write(last);
		
		
		sc.close();
		writer.close();
	}
}
