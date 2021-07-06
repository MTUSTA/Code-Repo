
public class Board {
	private Gem[][] gem = null;

	public Board() {

	}

	public Gem[][] getGem() {
		return gem;
	}

	public void setGem(Gem[][] gem) {
		this.gem = gem;
	}

	public int remove(int x, int y, int i, int j, int k, int l) {
		int puan =gem[x][y].getPoint()+gem[i][j].getPoint()+gem[k][l].getPoint();
		gem[x][y] = new EmptyGem(x, y);
		gem[i][j] = new EmptyGem(i, j);
		gem[k][l] = new EmptyGem(k, l);
		update(x, y, i, j, k, l);
		return puan;
	}

	public void update(int x, int y, int i, int j, int k, int l) {
		if (y != j && y != l && j != l) {

			for (int a = x; 0 < a; a--) {
				Gem temp =gem[a][y];
				gem[a][y] = gem[a - 1][y];
				gem[a - 1][y]=temp;
			}
			for (int a = i; 0 < a; a--) {
				Gem temp =gem[a][j];
				gem[a][j] = gem[a - 1][j];
				gem[a - 1][j]=temp;
			}
			for (int a = k; 0 < a; a--) {
				Gem temp =gem[a][l];
				gem[a][l] = gem[a - 1][l];
				gem[a - 1][l]=temp;
			}
		} else if (y == j && y == l && j == l) {
			int max = Math.max(Math.max(x, i), l);

			while(gem[max][y].getClass()==EmptyGem.class) {
				for (int a = max; 0 < a; a--) {
					Gem temp =gem[a][l];
					gem[a][l] = gem[a - 1][l];
					gem[a - 1][l]=temp;
				}
				
			}
		}
		printboard();
	}

	public void printboard() {
		System.out.println();
		for (Gem[] row : gem) {
			// System.out.println(Arrays.toString(row));
			for (int w = 0; w < row.length; w++) {
				System.out.print(row[w].getGemtype() + " ");

			}
			System.out.println();
		}
		System.out.println();
	}

}
