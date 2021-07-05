import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class leaderboard implements Comparable<ArrayList<Player>> {
	public void okuma_yazma(String line2, int kazanilan_puan) {
		ArrayList<Player> siralama = new ArrayList<Player>();
		try {
			Scanner leader = new Scanner(new File("leaderboard.txt"));
			while (leader.hasNextLine()) {
				String line = leader.nextLine();
				String[] line3=line.split(" ");
				int puan = Integer.parseInt(line3[1]);
				siralama.add(new Player(line3[0], puan));
			}
			siralama.add(new Player(line2, kazanilan_puan));		

			compareTo(siralama);
		} catch (FileNotFoundException e) {

		}
		try {
			PrintWriter writer = new PrintWriter("leaderboard.txt");
			for (Player yazma : siralama) {
				writer.println(yazma.getName()+" "+yazma.getPoint());

			}
			writer.close();
		} catch (FileNotFoundException e) {

		}
		

	}

	@Override
	public int compareTo(ArrayList<Player> o) {
		int bikere_yazildi=0;
		String son_yazma=", your score is ";
		int rank=1;
		Player son_eleman = o.get(o.size()-1);
		int son_eleman_int = son_eleman.getPoint();
		int i = 0;
		while (i < o.size()-1) {
			int kont_int =o.get(i).getPoint();
			
			if(son_eleman_int>kont_int) {
				if(bikere_yazildi==1) {
					son_yazma+=" and ";
				}
				son_yazma+=son_eleman_int-kont_int+" points higher than "+o.get(i).getName();
				bikere_yazildi=1;
			}
			if(son_eleman_int == kont_int) {
				
			}			
			if(son_eleman_int < kont_int) {
				if(bikere_yazildi==1) {
					son_yazma+=" and ";
				}
				son_yazma+=kont_int-son_eleman_int+" points lower than "+o.get(i).getName();
				bikere_yazildi=1;
				rank++;
			}				
			i++;

		}
		System.out.println("Your rank is "+rank+"/"+o.size()+" "+son_yazma);
		
		return 0;
	}
}
