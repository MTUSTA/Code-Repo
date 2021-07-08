import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static people[] insanlar = new people[50];
	public static food[] yiyecekler = new food[100];
	public static sport[] sporlar = new sport[100];
	public static final char[] badChar = { 'ï', '»', '¿' };

	public static String[] harf_silme(String[] splitlenmis_satir) {
		for (int i = 0; i < splitlenmis_satir.length; i++) {
			while (true) {
				for (char bad : badChar) {
					if (splitlenmis_satir[i].indexOf(bad) == 0) {
						splitlenmis_satir[i] = splitlenmis_satir[i].substring(1);
					}
					int uzunluk = splitlenmis_satir[i].length();
					if (splitlenmis_satir[i].indexOf(bad) == 0) {
						splitlenmis_satir[i] = splitlenmis_satir[i].substring(0, uzunluk - 1);
					}
				}
				break;
			}

		}
		return splitlenmis_satir;
	}

	public static long gunluk_kalori_ihtiyaci(people gelen_insan) {
		if (gelen_insan.getGender().compareTo("male") == 0) {
			double calc_for_man = (66 + 13.75 * gelen_insan.getWeight() + 5 * gelen_insan.getHeight()- 6.8 * gelen_insan.getDateOfBirth());
			long calc_for_man2 = Math.round(calc_for_man);
			return calc_for_man2;
		} else {
			double calc_for_woman = (665 + 9.6 * gelen_insan.getWeight() + 1.7 * gelen_insan.getHeight()- 4.7 * gelen_insan.getDateOfBirth());
			long calc_for_woman2 = Math.round(calc_for_woman);
			return calc_for_woman2;
		}

	}

	public static void main(String[] args) {
		try {
			Scanner insan = new Scanner(new File("people.txt"));
			Scanner yiyecek = new Scanner(new File("food.txt"));
			Scanner spor = new Scanner(new File("sport.txt"));
			Scanner emirler = new Scanner(new File(args[0]));
			PrintWriter writer = new PrintWriter("monitoring.txt");
			int insan_array = 0, yiyecek_array = 0, spor_array = 0;
			while (insan.hasNextLine()) {
				String satir = insan.nextLine();
				String[] splitlenmis_satir = satir.split("\t");
				splitlenmis_satir = harf_silme(splitlenmis_satir);
				int personid = Integer.parseInt(splitlenmis_satir[0]);
				int weight = Integer.parseInt(splitlenmis_satir[3]);
				int height = Integer.parseInt(splitlenmis_satir[4]);
				int dateOfBirth = Integer.parseInt(splitlenmis_satir[5]);
				people adam = new people(personid, splitlenmis_satir[1], splitlenmis_satir[2], weight, height,dateOfBirth);
				insanlar[insan_array] = adam;
				insan_array++;
			}
			while (yiyecek.hasNextLine()) {
				String satir2 = yiyecek.nextLine();
				String[] splitlenmis_satir2 = satir2.split("\t");
				splitlenmis_satir2 = harf_silme(splitlenmis_satir2);
				int foodID = Integer.parseInt(splitlenmis_satir2[0]);
				int calorieCount = Integer.parseInt(splitlenmis_satir2[2]);
				food yiyeceks = new food(foodID, splitlenmis_satir2[1], calorieCount);
				yiyecekler[yiyecek_array] = yiyeceks;
				yiyecek_array++;

			}
			while (spor.hasNextLine()) {
				String satir3 = spor.nextLine();
				String[] splitlenmis_satir3 = satir3.split("\t");
				splitlenmis_satir3 = harf_silme(splitlenmis_satir3);
				int sportID = Integer.parseInt(splitlenmis_satir3[0]);
				int calorieBurned = Integer.parseInt(splitlenmis_satir3[2]);
				sport sports = new sport(sportID, splitlenmis_satir3[1], calorieBurned);
				sporlar[spor_array] = sports;
				spor_array++;
			}
			people emir_insan = null;
			food emir_food = null;
			sport emir_spor = null;

			while (emirler.hasNextLine()) {
				String satir4 = emirler.nextLine();
				String[] splitlenmis_satir4 = satir4.split("\t");
				splitlenmis_satir4 = harf_silme(splitlenmis_satir4);
				if (splitlenmis_satir4.length == 1) {
					if (splitlenmis_satir4[0].compareTo("printList") == 0) {
						for (people print_insan : insanlar) {
							if (print_insan != null && print_insan.getKullanilan() == 1) {
								long ihtiyac = gunluk_kalori_ihtiyaci(print_insan);
								writer.println(print_insan.getName() + "\t" + print_insan.getDateOfBirth() + "\t"+ ihtiyac + "kcal\t" + print_insan.getCaloriestaken() + "kcal\t"+ print_insan.getCalorisburned() + "kcal\t" + (print_insan.getCaloriestaken()- ihtiyac - print_insan.getCalorisburned() + "kcal"));
							}
						}
						writer.println("***************");
					} else {
						splitlenmis_satir4[0] = splitlenmis_satir4[0].substring(6, 11);
						int bulma = Integer.parseInt(splitlenmis_satir4[0]);
						for (people kisi : insanlar) {
							if (kisi != null && kisi.getPersonID() == bulma) {
								long dailyCalorieNeeds = gunluk_kalori_ihtiyaci(kisi);
								writer.println(kisi.getName() + "\t" + kisi.getDateOfBirth() + "\t"	+ dailyCalorieNeeds + "kcal\t" + kisi.getCaloriestaken() + "kcal\t"+ kisi.getCalorisburned() + "kcal\t" + (kisi.getCaloriestaken()- dailyCalorieNeeds - kisi.getCalorisburned() + "kcal"));
								writer.println("***************");
								break;
							}

						}
					}

				} else {
					int personid_emir = Integer.parseInt(splitlenmis_satir4[0]);
					int food_or_sport = Integer.parseInt(splitlenmis_satir4[1]);
					int portions_or_duration = Integer.parseInt(splitlenmis_satir4[2]);
					for (people insan_karsi : insanlar) {
						if (insan_karsi != null && insan_karsi.getPersonID() == personid_emir) {
							emir_insan = insan_karsi;
							break;
						}
					}

					if (food_or_sport / 1000 == 1) {
						for (food food_karsi : yiyecekler) {
							if (food_karsi != null && food_karsi.getFoodID() == food_or_sport) {
								emir_food = food_karsi;
								emir_insan.setCaloriestaken(emir_insan.getCaloriestaken()+ (emir_food.getCalorieCount() * portions_or_duration));
								emir_insan.setKullanilan(1);
								writer.println(emir_insan.getPersonID() + "\thas\ttaken\t"+ (emir_food.getCalorieCount() * portions_or_duration) + "kcal\tfrom\t"+ emir_food.getNameOfFood());
								writer.println("***************");
								break;
							}
						}
					}
					if (food_or_sport / 1000 == 2) {
						for (sport spor_karsi : sporlar) {
							if (spor_karsi != null && spor_karsi.getSportID() == food_or_sport) {
								emir_spor = spor_karsi;
								emir_insan.setCalorisburned(emir_insan.getCalorisburned()+ emir_spor.getCalorieBurned() * portions_or_duration / 60);
								emir_insan.setKullanilan(1);
								writer.println(emir_insan.getPersonID() + "\thas\tburned\t" + emir_insan.getCalorisburned()+ "kcal\tthanks\tto\t" + emir_spor.getNameOfSport());
								writer.println("***************");
								break;
							}
						}
					}
				}
			}
			writer.close();
			insan.close();
			yiyecek.close();
			spor.close();
			emirler.close();
		} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}
	}

}
