import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gamegrid {
	ArrayList<String[]> lines = new ArrayList<String[]>();
	public int kazanilan_puan =0;
	public Gamegrid(Board tahta) {
		try {
			Scanner gamegrid = new Scanner(new File("gameGrid.txt"));
			while (gamegrid.hasNextLine()) {
				String line = gamegrid.nextLine();
				String[] line2 = line.split(" ");
				lines.add(line2);
			}
			tahta.setGem(new Gem[lines.size()][lines.get(0).length]);
			int k = 0;

			while (k < lines.size()) {
				int i = 0;
				while (i < lines.get(k).length) {

					if (lines.get(k)[i].equals("D")) {
						tahta.getGem()[k][i] = new Diamond(k, i);
						i++;
					} else if (lines.get(k)[i].equals("S")) {
						tahta.getGem()[k][i] = new Square(k, i);
						i++;
					} else if (lines.get(k)[i].equals("T")) {
						tahta.getGem()[k][i] = new Triangle(k, i);
						i++;
					} else if (lines.get(k)[i].equals("W")) {
						tahta.getGem()[k][i] = new Wildcard(k, i);
						i++;
					} else if (lines.get(k)[i].equals("/") || lines.get(k)[i].equals("-") || lines.get(k)[i].equals("+") || lines.get(k)[i].equals("\\") || lines.get(k)[i].equals("|")) {
						tahta.getGem()[k][i] = new Mathematical_Symbols(lines.get(k)[i], k, i);
					} else {
						Scanner keyboard = new Scanner (System.in);
						System.out.println("enter new jewel type and new jewel point with space");
						String keyboard_input = keyboard.nextLine();
						String[] keyboard_line = keyboard_input.split(" ");
						int key_int = Integer.parseInt(keyboard_line[1]);
						tahta.getGem()[k][i] = new new_jewel(keyboard_line[0], key_int, k, i);
					}
				}
				k++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("gameGrid.txt file not found");

		}
	}

	public Boolean yerkont(int x, int y, Board tahta) {
		// If x,y is outside 2d array, return false.
		if ((0 > x) || (x == lines.size()) || (0 > y) || (y == lines.get(0).length)) {
			return false;
		}
		return true;
	}

	public void kontrol1(int x, int y, Board tahta) {
		int donen_puan =0;
		/* North West-1 */
		if (yerkont(x - 1, y - 1, tahta) == true && yerkont(x - 2, y - 2, tahta) == true && tahta.getGem()[x - 1][y - 1].getClass() == Diamond.class && tahta.getGem()[x - 2][y - 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x - 1, y - 1, x - 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South East-9 */
		else if (yerkont(x + 1, y + 1, tahta) == true && yerkont(x + 2, y + 2, tahta) == true && tahta.getGem()[x + 1][y + 1].getClass() == Diamond.class && tahta.getGem()[x + 2][y + 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x + 1, y + 1, x + 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North East-3 */
		else if (yerkont(x - 1, y + 1, tahta) == true && yerkont(x - 2, y + 2, tahta) == true && tahta.getGem()[x - 1][y + 1].getClass() == Diamond.class && tahta.getGem()[x - 2][y + 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x - 1, y + 1, x - 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South West-7 */
		else if (yerkont(x + 1, y - 1, tahta) == true && yerkont(x + 2, y - 2, tahta) == true && tahta.getGem()[x + 1][y - 1].getClass() == Diamond.class && tahta.getGem()[x + 2][y - 2].getClass() == Diamond.class) {
			donen_puan = tahta.remove(x, y, x + 1, y - 1, x + 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		else {
			System.out.println("no matching matches found in the specified direction.Enter new coordinate");
		}
	}

	public void kontrol2(int x, int y, Board tahta) {
		int donen_puan =0;
		/* West-4 */
		if (yerkont(x, y - 1, tahta) == true && yerkont(x, y - 2, tahta) == true && tahta.getGem()[x][y - 1].getClass() == Square.class && tahta.getGem()[x][y - 2].getClass() == Square.class) {
			donen_puan = tahta.remove(x, y, x , y - 1, x , y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* East-6 */
		else if (yerkont(x, y + 1, tahta) == true && yerkont(x, y + 2, tahta) == true && tahta.getGem()[x][y + 1].getClass() == Square.class && tahta.getGem()[x][y + 2].getClass() == Square.class) {
			donen_puan = tahta.remove(x, y, x , y + 1, x , y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		else {
			System.out.println("no matching matches found in the specified direction.Enter new coordinate");
		}
	}

	public void kontrol3(int x, int y, Board tahta) {
		int donen_puan = 0;
		
		/* North-2 */
		if (yerkont(x - 1, y, tahta) == true && yerkont(x - 2, y, tahta) == true && tahta.getGem()[x - 1][y].getClass() == Triangle.class  && tahta.getGem()[x - 2][y].getClass() == Triangle.class ) {
			donen_puan = tahta.remove(x, y, x - 1, y , x - 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		
		/* South-8 */
		else if (yerkont(x + 1, y, tahta) == true && yerkont(x + 2, y, tahta) == true && tahta.getGem()[x + 1][y].getClass() == Triangle.class  && tahta.getGem()[x + 2][y].getClass() == Triangle.class ) {
			donen_puan = tahta.remove(x, y, x + 1, y , x + 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		else {
			System.out.println("no matching matches found in the specified direction.Enter new coordinate");
		}
	}

	public void kontrol4(int x, int y, Board tahta) {
		int donen_puan =0;
		/* North-2 w-w-T,w-w- +|*/
		if (yerkont(x - 1, y, tahta) == true && yerkont(x - 2, y, tahta) == true && tahta.getGem()[x - 1][y].getClass() == Wildcard.class && (tahta.getGem()[x - 2][y].getClass() == Wildcard.class ||tahta.getGem()[x - 2][y].getClass() == Triangle.class||(tahta.getGem()[x - 2][y].getClass().getTypeName().equals("+")||tahta.getGem()[x - 2][y].getClass().getTypeName().equals("|")))) {
			donen_puan = tahta.remove(x, y, x - 1, y , x - 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North-2 W-T-T*/
		else if (yerkont(x - 1, y, tahta) == true && yerkont(x - 2, y, tahta) == true && tahta.getGem()[x - 1][y].getClass() == Triangle.class && tahta.getGem()[x - 2][y].getClass() == Triangle.class ) {
			donen_puan = tahta.remove(x, y, x - 1, y , x - 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North-2 and w + +|*/
		else if (yerkont(x - 1, y, tahta) == true && yerkont(x - 2, y, tahta) == true && (tahta.getGem()[x - 1][y].getClass().getTypeName().equals("+") ||tahta.getGem()[x - 1][y].getClass().getTypeName().equals("|"))&& (tahta.getGem()[x - 2][y].getClass().getTypeName().equals("+")||tahta.getGem()[x - 2][y].getClass().getTypeName().equals("+"))) {
			donen_puan = tahta.remove(x, y, x - 1, y , x - 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South-8 w-w-T,w-w- +|*/
		else if (yerkont(x + 1, y, tahta) == true && yerkont(x + 2, y, tahta) == true && tahta.getGem()[x + 1][y].getClass() == Wildcard.class && (tahta.getGem()[x + 2][y].getClass() == Wildcard.class||tahta.getGem()[x + 2][y].getClass() == Triangle.class || (tahta.getGem()[x + 2][y].getClass().getTypeName().equals("+") || tahta.getGem()[x + 2][y].getClass().getTypeName().equals("|") ))) {
			donen_puan = tahta.remove(x, y, x + 1, y , x + 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South-8 W-T-T*/
		else if (yerkont(x + 1, y, tahta) == true && yerkont(x + 2, y, tahta) == true && tahta.getGem()[x + 1][y].getClass() == Triangle.class && tahta.getGem()[x + 2][y].getClass() == Triangle.class ) {
			donen_puan = tahta.remove(x, y, x + 1, y , x + 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South-8 and w + +|*/
		else if (yerkont(x + 1, y, tahta) == true && yerkont(x + 2, y, tahta) == true && (tahta.getGem()[x + 1][y].getClass().getTypeName().equals("+") ||tahta.getGem()[x + 1][y].getClass().getTypeName().equals("|"))&& (tahta.getGem()[x + 2][y].getClass().getTypeName().equals("+")||tahta.getGem()[x + 2][y].getClass().getTypeName().equals("+"))) {
			donen_puan = tahta.remove(x, y, x + 1, y , x + 2, y );
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* West-4 w-w-S,w-w- +-*/
		else if (yerkont(x, y - 1, tahta) == true && yerkont(x, y - 2, tahta) == true && tahta.getGem()[x][y - 1].getClass() == Wildcard.class 	&& (tahta.getGem()[x][y - 2].getClass() == Square.class	|| tahta.getGem()[x][y - 2].getClass() == Wildcard.class||(tahta.getGem()[x][y-2].getClass().getTypeName().equals("+") || tahta.getGem()[x][y-2].getClass().getTypeName().equals("-")))) {
			donen_puan = tahta.remove(x, y, x , y - 1, x , y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* West-4 w-S-S */
		else if (yerkont(x, y - 1, tahta) == true && yerkont(x, y - 2, tahta) == true && tahta.getGem()[x][y - 1].getClass() == Square.class 	&& tahta.getGem()[x][y - 2].getClass() == Square.class	) {
			donen_puan = tahta.remove(x, y, x , y - 1, x , y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* West-4 w-+-+, w - - */
		else if (yerkont(x, y - 1, tahta) == true && yerkont(x, y - 2, tahta) == true && (tahta.getGem()[x][y-1].getClass().getTypeName().equals("+") ||tahta.getGem()[x][y-1].getClass().getTypeName().equals("-"))&& (tahta.getGem()[x][y-2].getClass().getTypeName().equals("+")||tahta.getGem()[x][y-2].getClass().getTypeName().equals("-")) ) {
			donen_puan = tahta.remove(x, y, x , y - 1, x , y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* East-6 w-w-S w-w- +-*/
		else if (yerkont(x, y + 1, tahta) == true && yerkont(x, y + 2, tahta) == true && tahta.getGem()[x][y + 1].getClass() == Wildcard.class  && (tahta.getGem()[x][y + 2].getClass() == Square.class	|| tahta.getGem()[x][y + 2].getClass() == Wildcard.class||(tahta.getGem()[x][y+2].getClass().getTypeName().equals("+") || tahta.getGem()[x][y+2].getClass().getTypeName().equals("-")))) {
			donen_puan = tahta.remove(x, y, x , y + 1, x , y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* East-6 w-S-S*/
		else if (yerkont(x, y + 1, tahta) == true && yerkont(x, y + 2, tahta) == true && tahta.getGem()[x][y + 1].getClass() == Square.class  &&  tahta.getGem()[x][y + 2].getClass() == Square.class) {
			donen_puan = tahta.remove(x, y, x , y + 1, x , y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* East-6 w-+-+*/
		else if (yerkont(x, y + 1, tahta) == true && yerkont(x, y + 2, tahta) == true && (tahta.getGem()[x][y+1].getClass().getTypeName().equals("+") ||tahta.getGem()[x][y+1].getClass().getTypeName().equals("-"))&& (tahta.getGem()[x][y+2].getClass().getTypeName().equals("+")||tahta.getGem()[x][y+2].getClass().getTypeName().equals("-"))) {
			donen_puan = tahta.remove(x, y, x , y + 1, x , y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North West-1 w-w-d w-w-w w-w-\*/
		else if (yerkont(x - 1, y - 1, tahta) == true && yerkont(x - 2, y - 2, tahta) == true && tahta.getGem()[x - 1][y - 1].getClass() == Wildcard.class&& (tahta.getGem()[x - 2][y - 2].getClass() == Diamond.class|| tahta.getGem()[x - 2][y - 2].getClass() == Wildcard.class||tahta.getGem()[x -2][y-2].getClass().getTypeName().equals("\\") )) {
			donen_puan = tahta.remove(x, y, x - 1, y - 1, x - 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North West-1 W-D-D */
		else if (yerkont(x - 1, y - 1, tahta) == true && yerkont(x - 2, y - 2, tahta) == true && tahta.getGem()[x - 1][y - 1].getClass() == Diamond.class&& tahta.getGem()[x - 2][y - 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x - 1, y - 1, x - 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North West-1 W-\-\ */
		else if (yerkont(x - 1, y - 1, tahta) == true && yerkont(x - 2, y - 2, tahta) == true &&tahta.getGem()[x-1][y-1].getClass().getTypeName().equals("\\") && tahta.getGem()[x-2][y-2].getClass().getTypeName().equals("\\")) {
			donen_puan = tahta.remove(x, y, x - 1, y - 1, x - 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South East-9 W-W-D W-W-W W-W-\*/
		else if (yerkont(x + 1, y + 1, tahta) == true && yerkont(x + 2, y + 2, tahta) == true && tahta.getGem()[x + 1][y + 1].getClass() == Wildcard.class && (tahta.getGem()[x + 2][y + 2].getClass() == Diamond.class|| tahta.getGem()[x + 2][y + 2].getClass() == Wildcard.class||tahta.getGem()[x + 2][y+2].getClass().getTypeName().equals("\\") )) {
			donen_puan = tahta.remove(x, y, x + 1, y + 1, x + 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South East-9 W-D-D\*/
		else if (yerkont(x + 1, y + 1, tahta) == true && yerkont(x + 2, y + 2, tahta) == true && tahta.getGem()[x + 1][y + 1].getClass() == Diamond.class && tahta.getGem()[x + 2][y + 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x + 1, y + 1, x + 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South East-9 W-\-\*/
		else if (yerkont(x + 1, y + 1, tahta) == true && yerkont(x + 2, y + 2, tahta) == true &&tahta.getGem()[x+1][y+1].getClass().getTypeName().equals("\\") && tahta.getGem()[x+2][y+2].getClass().getTypeName().equals("\\")) {
			donen_puan = tahta.remove(x, y, x + 1, y + 1, x + 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North East-3 W-W-D W-W-W W-W-/*/
		else if (yerkont(x - 1, y + 1, tahta) == true && yerkont(x - 2, y + 2, tahta) == true && tahta.getGem()[x - 1][y + 1].getClass() == Wildcard.class	&& (tahta.getGem()[x - 2][y + 2].getClass() == Diamond.class || tahta.getGem()[x - 2][y + 2].getClass() == Wildcard.class||tahta.getGem()[x - 2][y+2].getClass().getTypeName().equals("/"))) {
			donen_puan = tahta.remove(x, y, x - 1, y + 1, x - 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North East-3 W-D-D\*/
		else if (yerkont(x -1, y + 1, tahta) == true && yerkont(x - 2, y + 2, tahta) == true && tahta.getGem()[x - 1][y + 1].getClass() == Diamond.class && tahta.getGem()[x - 2][y + 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x - 1, y + 1, x - 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* North East-3 W-/-/*/
		else if (yerkont(x - 1, y + 1, tahta) == true && yerkont(x - 2, y + 2, tahta) == true &&tahta.getGem()[x-1][y+1].getClass().getTypeName().equals("/") && tahta.getGem()[x-2][y+2].getClass().getTypeName().equals("/")) {
			donen_puan = tahta.remove(x, y, x - 1, y + 1, x - 2, y + 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South West-7 W-W-D W-W-W W-W-/*/
		else if (yerkont(x + 1, y - 1, tahta) == true && yerkont(x + 2, y - 2, tahta) == true && tahta.getGem()[x + 1][y - 1].getClass() == Wildcard.class  && (tahta.getGem()[x + 2][y - 2].getClass() == Diamond.class || tahta.getGem()[x + 2][y - 2].getClass() == Wildcard.class||tahta.getGem()[x + 2][y-2].getClass().getTypeName().equals("/"))) {
			donen_puan = tahta.remove(x, y, x + 1, y - 1, x + 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South West-7 W-D-D */
		else if (yerkont(x + 1, y - 1, tahta) == true && yerkont(x + 2, y - 2, tahta) == true && tahta.getGem()[x + 1][y - 1].getClass() == Diamond.class && tahta.getGem()[x + 2][y - 2].getClass() == Diamond.class ) {
			donen_puan = tahta.remove(x, y, x + 1, y - 1, x + 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		/* South West-7 W-/-/*/
		else if (yerkont(x + 1, y - 1, tahta) == true && yerkont(x + 2, y - 2, tahta) == true && tahta.getGem()[x+1][y-1].getClass().getTypeName().equals("/") && tahta.getGem()[x+2][y-2].getClass().getTypeName().equals("/")) {
			donen_puan = tahta.remove(x, y, x + 1, y - 1, x + 2, y - 2);
			System.out.println("Score: "+donen_puan+" points.");
			kazanilan_puan +=donen_puan;
		}
		else {
			System.out.println("no matching matches found in the specified direction.Enter new coordinate");
		}
	
	}

	public void kontrol5(int x, int y, Board tahta) {
		int donen_puan =0;
		
		if (tahta.getGem()[x][y].getGemtype().compareTo("/") == 0) {
			
			/* North East-3 */
			if (yerkont(x - 1, y + 1, tahta) == true && yerkont(x - 2, y + 2, tahta) == true && tahta.getGem()[x - 1][y + 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x - 2][y + 2].getClass() == Mathematical_Symbols.class) {	
				donen_puan = tahta.remove(x, y, x - 1, y + 1, x - 2, y + 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* South West-7 */
			else if (yerkont(x + 1, y - 1, tahta) == true && yerkont(x + 2, y - 2, tahta) == true && tahta.getGem()[x + 1][y - 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x + 2][y + 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x + 1, y - 1, x + 2, y - 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			else {
				System.out.println("no matching matches found in the specified direction.Enter new coordinate");
			}

		} else if (tahta.getGem()[x][y].getGemtype().compareTo("-") == 0) {
			
			/* West-4 */
			if (yerkont(x, y - 1, tahta) == true && yerkont(x, y - 2, tahta) == true && tahta.getGem()[x][y - 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x][y - 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x , y - 1, x , y - 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* East-6 */
			else if (yerkont(x, y + 1, tahta) == true && yerkont(x, y + 2, tahta) == true && tahta.getGem()[x][y + 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x][y + 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x , y + 1, x , y + 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			else {
				System.out.println("no matching matches found in the specified direction.Enter new coordinate");
			}

		} else if (tahta.getGem()[x][y].getGemtype().compareTo("+") == 0) {
			
			/* West-4 */
			if (yerkont(x, y - 1, tahta) == true && yerkont(x, y - 2, tahta) == true && tahta.getGem()[x][y - 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x][y - 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x , y - 1, x , y - 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* East-6 */
			else if (yerkont(x, y + 1, tahta) == true && yerkont(x, y + 2, tahta) == true && tahta.getGem()[x][y + 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x][y + 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x , y + 1, x , y + 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* North-2 */
			else if (yerkont(x - 1, y, tahta) == true && yerkont(x - 2, y, tahta) == true && tahta.getGem()[x - 1][y].getClass() == Mathematical_Symbols.class && tahta.getGem()[x - 2][y].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x - 1, y , x - 2, y );
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* South-8 */
			else if (yerkont(x + 1, y, tahta) == true && yerkont(x + 2, y, tahta) == true && tahta.getGem()[x + 1][y].getClass() == Mathematical_Symbols.class && tahta.getGem()[x + 2][y].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x + 1, y , x + 2, y );
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			else {
				System.out.println("no matching matches found in the specified direction.Enter new coordinate");
			}

		} else if (tahta.getGem()[x][y].getGemtype().compareTo("\\") == 0) {
			
			/* North West-1 */
			if (yerkont(x - 1, y - 1, tahta) == true && yerkont(x - 2, y - 2, tahta) == true && tahta.getGem()[x - 1][y - 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x - 2][y - 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x - 1, y - 1, x - 2, y - 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* South East-9 */
			else if (yerkont(x + 1, y + 1, tahta) == true && yerkont(x + 2, y + 2, tahta) == true && tahta.getGem()[x + 1][y + 1].getClass() == Mathematical_Symbols.class && tahta.getGem()[x + 2][y + 2].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x + 1, y + 1, x + 2, y + 2);
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			else {
				System.out.println("no matching matches found in the specified direction.Enter new coordinate");
			}

		} else if (tahta.getGem()[x][y].getGemtype().compareTo("|") == 0) {
			
			/* North-2 */
			if (yerkont(x - 1, y, tahta) == true && yerkont(x - 2, y, tahta) == true && tahta.getGem()[x - 1][y].getClass() == Mathematical_Symbols.class && tahta.getGem()[x - 2][y].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x - 1, y , x - 2, y );
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			
			/* South-8 */
			else if (yerkont(x + 1, y, tahta) == true && yerkont(x + 2, y, tahta) == true && tahta.getGem()[x + 1][y].getClass() == Mathematical_Symbols.class && tahta.getGem()[x + 2][y].getClass() == Mathematical_Symbols.class) {
				donen_puan = tahta.remove(x, y, x + 1, y , x + 2, y );
				System.out.println("Score: "+donen_puan+" points.");
				kazanilan_puan +=donen_puan;
			}
			else {
				System.out.println("no matching matches found in the specified direction.Enter new coordinate");
			}
		}

	}
}
