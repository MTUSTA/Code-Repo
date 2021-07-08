import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		/*
		 * setmemory hides memory space,val is for bst,ilerletme stores elements to be deleted
		 * Page_Fault hides page fault number,select_road choose algorithm
		 * second_array fills array(int[]second)
		 */
		int setmemory = 0, val = 0, ilerletme = 0, Page_Fault = 0, select_road = 0, second_array = 0;
		/* int array hides 2nd chance situation */
		int[] second = null;
		/* liste contain pq elements but unsorted */
		ArrayList<String> liste = null;
		/* StringBuilder(for_second_change) for printing */
		StringBuilder for_second_change = null;
		/* queue structure */
		Queue<String> queue = null;
		/* priority queue structure */
		MaxPQ<String> pq = null;
		/* binary search tree structure */
		BST<String, Integer> bst = null;
		try {
			/* reading file */
			Scanner girdi = new Scanner(new File(args[0]));
			/* create output file */
			PrintWriter writer = new PrintWriter("output.txt");

			while (girdi.hasNextLine()) {
				String line = girdi.nextLine();
				String[] line2 = line.split(" ");
				/* this statement sets memory */
				if (line2[0].compareTo("SetMemory") == 0) {
					setmemory = Integer.parseInt(line2[1]);
					writer.println("Memory " + setmemory);
				}
				/* this statement choose algorithm and create objects */
				if (line2[0].compareTo("setReplacement") == 0) {
					if (line2[1].compareTo("FIFO") == 0) {
						queue = new Queue<String>();
						select_road = 1;
						writer.println("FIFO Page Replacement");
					} else if (line2[1].compareTo("SecondChance") == 0) {
						queue = new Queue<String>();
						second = new int[setmemory];
						for_second_change = new StringBuilder();
						select_road = 2;
						writer.println("SecondChance Page Replacement");
					} else if (line2[1].compareTo("PriorityQueue") == 0) {
						pq = new MaxPQ<String>();
						liste = new ArrayList<String>();
						select_road = 3;
						writer.println("PriorityQueue Page Replacement");
					}
				}
				/* create objects(Binary Search Tree) */
				if (line2[0].compareTo("setSearchStructure") == 0) {
					bst = new BST<String, Integer>();
					writer.print("Binary Search Tree\n");
				}
				if (line2[0].compareTo("Read") == 0) {
					/* this statement is fifo */
					if (select_road == 1) {
						/*ensure that the input file element is not repeated*/
						if (bst.contains(line2[1]) == false) {
							/**/
							if (queue.size() == setmemory) {
								/**/
								Queue<String> gecici_queue = new Queue<String>();
								int dongu = 0;
								while (dongu < setmemory) {
									if (dongu == ilerletme) {
										gecici_queue.enqueue(line2[1]);
										bst.delete(queue.dequeue());
										bst.put(line2[1], val);
										val++;
									} else {
										gecici_queue.enqueue(queue.dequeue());
									}
									dongu++;
								}
								queue = gecici_queue;
								ilerletme++;
								Page_Fault++;
								if (ilerletme == setmemory) {
									ilerletme = 0;
								}
							} else {
								queue.enqueue(line2[1]);
								bst.put(line2[1], val);
								val++;
								Page_Fault++;
							}
							writer.println("Page Fault\t" + queue.toString());
						} else if (bst.contains(line2[1]) == true) {
							writer.println("\t\t\t" + queue.toString());
						}
					}
					/* this statement is SecondChance */
					else if (select_road == 2) {
						/*ensure that the input file element is not repeated*/
						if (bst.contains(line2[1]) == false) {
							if (queue.size() == setmemory) {
								if (ilerletme == setmemory) {
									ilerletme = 0;
								}
								int yazi = 0;
								for (int ilerletme2 = ilerletme; ilerletme2 < setmemory; ilerletme2++) {
									if (second[ilerletme2] == 1) {
										second[ilerletme2] = 0;
										String[] que = queue.toString().split(" ");
										if (yazi == 0) {
											for_second_change.append("Second Chance");
											yazi++;
										}
										for_second_change.append(" " + que[ilerletme2]);
										if (ilerletme2 == setmemory-1) {
											ilerletme2 = -1;
										}
									} else if (second[ilerletme2] == 0) {
										Queue<String> gecici_queue = new Queue<String>();
										int dongu = 0;
										while (dongu < setmemory) {
											if (dongu == ilerletme2) {
												gecici_queue.enqueue(line2[1]);
												bst.delete(queue.dequeue());
												bst.put(line2[1], val);
												val++;
											} else {
												gecici_queue.enqueue(queue.dequeue());
											}
											dongu++;
										}
										queue = gecici_queue;
										ilerletme2++;
										ilerletme = ilerletme2;
										Page_Fault++;
										break;
									}
								}
							} 
							/*filling memory*/
							else {
								queue.enqueue(line2[1]);
								bst.put(line2[1], val);
								val++;
								Page_Fault++;
								second[second_array++] = 0;
							}
							writer.println("Page Fault\t" + queue.toString() + for_second_change.toString());
							for_second_change.setLength(0);
						} else if (bst.contains(line2[1]) == true) {
							Queue<String> gecici_queue = new Queue<String>();
							int yer = 0;
							while (queue.size() > 0) {
								String kont = queue.dequeue();

								if (kont.equals(line2[1])) {
									second[yer] = 1;
								}
								yer++;
								gecici_queue.enqueue(kont);
							}
							queue = gecici_queue;
							writer.println("\t\t\t" + queue.toString());
						}
					}
					/* this statement is PriorityQueue */
					else if (select_road == 3) {
						/*ensure that the input file element is not repeated*/
						if (bst.contains(line2[1]) == false) {
							if (pq.size() == setmemory) {
								String a = pq.delMax();
								pq.insert(line2[1]);
								int key = bst.get(a);
								bst.deleteMax();
								bst.put(line2[1], key);
								liste.set(liste.indexOf(Collections.max(liste)), line2[1]);
								Page_Fault++;
								writer.print("Page Fault \t");
								for (String asd : liste) {
									writer.print(asd + " ");
								}
								writer.println();
							} 
							/*filling memory*/
							else {
								pq.insert(line2[1]);
								bst.put(line2[1], val);
								liste.add(line2[1]);
								val++;
								Page_Fault++;
								writer.print("Page Fault \t");
								for (String asd : liste) {
									writer.print(asd + " ");
								}
								writer.println();
							}
						} else if (bst.contains(line2[1]) == true) {
							writer.print("\t\t\t");
							for (String asd : liste) {
								writer.print(asd + " ");
							}
							writer.println();
						}
					}
				}
			}
			writer.println(Page_Fault);
			long end = System.currentTimeMillis();
			/* formatter set run time output */
			NumberFormat formatter = new DecimalFormat("#0.00000");
			writer.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
			writer.close();
			girdi.close();
		} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}
	}
}
