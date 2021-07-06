import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readfile {

	public readfile(Board tahta, String[] args) {
		try {
			Gamegrid doldurma = new Gamegrid(tahta);
			System.out.println("Game grid:");
			
			tahta.printboard();				
			Scanner testcase = new Scanner(new File(args[0]));

			int quit = 0;
				while (testcase.hasNextLine()&&quit<2) {

					String line = testcase.nextLine();
					String[] line2 = line.split(" ");
					
					if (line2[0].compareTo("E") == 0) {
						System.out.println("Select coordinate or enter E to end the game: "+line);
						quit++;
						System.out.println();
						System.out.println("Total score: "+doldurma.kazanilan_puan+" points.");
						System.out.println();
					} else if (quit == 1) {
						System.out.println("Enter name: "+line);
						leaderboard lead = new leaderboard();
						lead.okuma_yazma(line,doldurma.kazanilan_puan);
						System.out.println();
						System.out.println("Good bye!");
						quit++;
						
					} else if (line2.length == 2) {
						System.out.println("Select coordinate or enter E to end the game: "+line);
						int x = Integer.parseInt(line2[0]);
						int y = Integer.parseInt(line2[1]);
						if (tahta.getGem()[x][y].getClass() == Diamond.class) {
							doldurma.kontrol1(x, y, tahta);
						} else if (tahta.getGem()[x][y].getClass() == Square.class) {
							doldurma.kontrol2(x, y, tahta);
						} else if (tahta.getGem()[x][y].getClass() == Triangle.class) {
							doldurma.kontrol3(x, y, tahta);
						} else if (tahta.getGem()[x][y].getClass() == Wildcard.class) {
							doldurma.kontrol4(x, y, tahta);
						} else if (tahta.getGem()[x][y].getClass() == Mathematical_Symbols.class) {
							doldurma.kontrol1(x, y, tahta);
						}
					}
				}				
		} catch (FileNotFoundException e) {
			System.out.println("testcase file not found");

		}

	}

	public readfile(Board tahta) {
		Gamegrid doldurma = new Gamegrid(tahta);
		int quit=0;
		while(quit<1) {
			Scanner keyboard = new Scanner (System.in);
			System.out.println("Select coordinate or enter E to end the game from the keyboard: ");
			String keyboard_input = keyboard.nextLine();
			String[] line = keyboard_input.split(" ");
			if(keyboard_input.compareTo("E")==0) {
				quit++;
				System.out.println("You entered E to end the game from the keyboard");
				Scanner keyboard2 = new Scanner (System.in);
				System.out.println("Please enter your name: ");
				String keyboard_input2 = keyboard2.nextLine();
				
				System.out.println("Enter name: "+keyboard_input2);
				leaderboard lead = new leaderboard();
				lead.okuma_yazma(keyboard_input2,doldurma.kazanilan_puan);
				System.out.println();
				System.out.println("Good bye!");
			}
			else if (line.length == 2) {
				System.out.println("Select coordinate or enter E to end the game: "+keyboard_input);
				int x = Integer.parseInt(line[0]);
				int y = Integer.parseInt(line[1]);
				System.out.println(tahta.getGem()[x][y].getClass());
				if (tahta.getGem()[x][y].getClass() == Diamond.class) {
					doldurma.kontrol1(x, y, tahta);
				} else if (tahta.getGem()[x][y].getClass() == Square.class) {
					doldurma.kontrol2(x, y, tahta);
				} else if (tahta.getGem()[x][y].getClass() == Triangle.class) {
					doldurma.kontrol3(x, y, tahta);
				} else if (tahta.getGem()[x][y].getClass() == Wildcard.class) {
					doldurma.kontrol4(x, y, tahta);
				} else if (tahta.getGem()[x][y].getClass() == Mathematical_Symbols.class) {
					doldurma.kontrol1(x, y, tahta);
				}
			}
		}

	}

}