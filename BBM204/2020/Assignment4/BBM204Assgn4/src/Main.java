import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static FileWriter writer = null;

	public static List<String> ngrams(int n, String str) {
		List<String> ngrams = new ArrayList<String>();
		for (int i = 0; i < str.length() - n + 1; i++) {
			// Add the substring or size n
			ngrams.add(str.substring(i, i + n));
			// In each iteration, the window moves one step forward
			// Hence, each n-gram is added to the list
		}
		return ngrams;
	}

	public static double compute_weight(String key, double PO, double LO) {
		double weight = 0;
		if (PO > 0 && LO == 0) {
			weight = 1; // a very unique n-gram for phishing medium
		} else if (LO > 0 && PO == 0) {
			weight = -1; // a very unique n-gram for legitimate medium
		} else if (PO > 0 && LO > 0) {
			double min = Math.min(PO, LO);
			double max = Math.max(PO, LO);
			if (PO > LO) {
				weight =  min / max; // (0,1)
			} else if (PO < LO) {
				weight = -(min / max); // (-1,0)
			} else {
				weight = 0; // the n-gram appears equally in both of the mediums
			}
		}
		
		return weight;
	}

	// if https:// or http:// contain in line , discard it
	// else return self
	public static String checker(String line) {
		// s = s.replace(" (with nice players)", "");
		String s ="";
		//ignore empty line 
		if(line.length()>11) {
			// why 12 ? because --> legitimate.txt line 1885 start with "-" -> "-https://"
			s = line.substring(0,12);
		}
		if (s.contains("https")) {
			line = line.replace("https", "");
		}
		if (s.contains("http")) {
			line = line.replace("http", "");
		}
		if (s.contains("www")) {
			line = line.replace("www", "");
		}
		return line;
	}

	// function to sort hashmap by integer values
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
	// function to sort hashmap by double values
	public static HashMap<String, Double> sortByValueDouble(HashMap<String, Double> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
	
	
	//program starts
	public static void main(String[] args) throws IOException {
		System.out.println("n-gram based phishing detection via TST");
		
		// set by hand
		int n_gram_size = 4;
		int feat_size = 5000;
		
		System.out.println("Feat_Size: " + feat_size);
		System.out.println("n_gram_size: " + n_gram_size);

		Scanner sc = null;

		// legitimate train data
		ArrayList<String> legitimate_train = new ArrayList<String>();

		sc = new Scanner(new File("legitimatetrain.txt"));

		// reading legimate train
		while (sc.hasNext()) {
			String line = sc.nextLine();
			legitimate_train.add(checker(line).toLowerCase());
		}
		System.out.println("Legitimate training file has been loaded with [" + legitimate_train.size() + "] instances");

		// legitimate test data
		ArrayList<String> legitimate_test = new ArrayList<String>();

		// reading legimate test
		sc = new Scanner(new File("legitimatetest.txt"));
		while (sc.hasNext()) {
			String line = sc.nextLine();
			legitimate_test.add(checker(line).toLowerCase());
		}
		System.out.println("Legitimate test file has been loaded with [" + legitimate_test.size() + "] instances");

		// phishing train test data
		ArrayList<String> phishing_train = new ArrayList<String>();

		sc = new Scanner(new File("phishingtrain.txt"));

		// reading phishingtrain
		while (sc.hasNext()) {
			String line = sc.nextLine();
			phishing_train.add(checker(line).toLowerCase());
		}
		System.out.println("Phishing training file has been loaded with [" + phishing_train.size() + "] instances");

		// phishing test test data
		ArrayList<String> phishing_test = new ArrayList<String>();

		// reading phishingtest
		sc = new Scanner(new File("phishingtest.txt"));
		while (sc.hasNext()) {
			String line = sc.nextLine();
			phishing_test.add(checker(line).toLowerCase());
		}

		System.out.println("Phishing test file has been loaded with [" + phishing_test.size() + "] instances");
		// build symbol table from standard input
		TST<Integer> tst_legitimate_train = new TST<Integer>();
		// adding or increment freq
		for (String str : legitimate_train) {
			List<String> n_gramlist = ngrams(n_gram_size, str);
			for (String str2 : n_gramlist) {
				if (tst_legitimate_train.contains(str2)) {
					// increment freq
					int increment = tst_legitimate_train.get(str2) + 1;
					// overwrite
					tst_legitimate_train.put(str2, increment);
				} else {
					// adding first time
					tst_legitimate_train.put(str2, 1);
				}
			}
		}

		System.out.println("TST has been loaded with " + legitimate_train.size() + " n-grams");
		
		
		TST<Integer> tst_phishing_train = new TST<Integer>();
		
		for (String str : phishing_train) {
			//n-gram list
			List<String> n_gramlist = ngrams(n_gram_size, str);
			for (String str2 : n_gramlist) {
				// if contain, increase freq
				if (tst_phishing_train.contains(str2)) {
					int increment = tst_phishing_train.get(str2) + 1;
					//overwrite
					tst_phishing_train.put(str2, increment);

				} else {
					// enter data
					tst_phishing_train.put(str2, 1);
				}
			}

		}
		System.out.println("TST has been loaded with " + phishing_train.size() + " n-grams");

		writer = new FileWriter(new File("strong_phishing_features.txt"));

		// hashmap for sorting n-grams
		HashMap<String, Integer> hash_map_tst_phishing_train = new HashMap<String, Integer>();
		for (String key : tst_phishing_train.keys()) {
			// System.out.println(key + " " + tst_legitimate_train.get(key));
			hash_map_tst_phishing_train.put(key, tst_phishing_train.get(key));
		}
		// sorting hashmap
		writer.write("Most important phishing n_grams\n");
		hash_map_tst_phishing_train = sortByValue(hash_map_tst_phishing_train);
		// writing in file
		int loop1 = 0;
		for (String string : hash_map_tst_phishing_train.keySet()) {
			loop1++;
			if (loop1 == feat_size + 1) {
				break;
			}
			//write data in file
			writer.write(loop1 + ". " + string + " - freq: " + hash_map_tst_phishing_train.get(string) + "\n");
		}

		System.out.println(
				feat_size + " strong phishing n-grams have been saved to the file \"strong_phishing_features.txt\"");

		writer.flush();
		// hashmap for sorting n-grams
		HashMap<String, Integer> hash_map_tst_legitimate_train = new HashMap<String, Integer>();
		// adding in hashmap
		for (String key : tst_legitimate_train.keys()) {
			hash_map_tst_legitimate_train.put(key, tst_legitimate_train.get(key));
		}

		// sorting hashmap
		writer = new FileWriter(new File("strong_legitimate_features.txt"));
		writer.write("Most important legitimate n_grams\n");
		
		// sorting hashmap
		hash_map_tst_legitimate_train = sortByValue(hash_map_tst_legitimate_train);

		// writing in file
		int loop2 = 0;
		for (String string : hash_map_tst_legitimate_train.keySet()) {
			loop2++;
			if (loop2 == feat_size + 1) {
				break;
			}
			writer.write(loop2 + ". " + string + " - freq: " + hash_map_tst_legitimate_train.get(string) + "\n");
		}

		System.out.println(
				feat_size + " strong legitimate n-grams have been saved to the file \"strong_legitmate_features.txt\"");

		// These fields are "phish-occurrence", "legitimate-occurrence" and "weight".
		writer.flush();

		writer = new FileWriter(new File("all_feature_weights.txt"));
		writer.write("All N-gram Weights\n");
		// mark if already added in list
		ArrayList<String> marked = new ArrayList<String>();
		// hashmap for key -> weight
		HashMap<String, Double> weights = new HashMap<String, Double>();
		
		for (String key : hash_map_tst_phishing_train.keySet()) {
			int Po = hash_map_tst_phishing_train.get(key);
			int Lo = 0;
			try {
				Lo = hash_map_tst_legitimate_train.get(key);
			} catch (Exception e) {
				// if not found, lo will be 0
				Lo = 0;
			}
			// save computed key
			marked.add(key);
			//compute weight
			weights.put(key, compute_weight(key, Po, Lo));
		}
		for (String key : hash_map_tst_legitimate_train.keySet()) {
			// if marked -> do nothing
			if (!marked.contains(key)) {
				int Po = 0;
				int Lo = hash_map_tst_legitimate_train.get(key);
				try {
					Po = hash_map_tst_phishing_train.get(key);
				} catch (Exception e) {
					// if not found lo will be 0
					Po = 0;
				}
				//compute weight
				weights.put(key, compute_weight(key, Po, Lo));
			}
		}
		
		//sorting hashmap by double value
		weights = sortByValueDouble(weights);
		
		for (String key : weights.keySet()) {
			writer.write(key + " - weight: " + Double.toString(weights.get(key))+"\n");
		}
		writer.flush();
		System.out.println(weights.keySet().size()+" n-grams + weights have been saved to the file \"all_feature_weights.txt\"");
		
		
		// create new TST with strong features 
		TST<Double> new_tst_legi_and_phish = new TST<Double>();
		
		int loop3 = 0;
		for (String string : hash_map_tst_phishing_train.keySet()) {
			loop3++;
			if (loop3 == feat_size + 1) {
				break;
			}
			//adding element
			new_tst_legi_and_phish.put(string,  weights.get(string));
		}
		
		loop3 = 0;
		for (String string2 : hash_map_tst_legitimate_train.keySet()) {
			loop3++;
			if (loop3 == feat_size + 1) {
				break;
			}
			// adding element / if already exist , overwrite same data
			new_tst_legi_and_phish.put(string2,  weights.get(string2));

		}
		int removed = weights.size()-new_tst_legi_and_phish.size();
		System.out.println(removed+" insignificant n-grams have been removed from the TST");
		
		
		/*
		 True Positive (TP): indicates the number of cases where actual URL is phishing and predicted is also true saying it is phishing.
		 False Negative (FN): indicates the number of cases where actual URL is phishing but the prediction was wrongly made - saying it is legitimate.
		 True Negative (TN): indicates the number of cases where actual URL is legitimate and the prediction is also true saying it is legitimate.
		 False Positive (FP): indicates the number of cases where actual URL is legitimate but the prediction was wrongly made - saying it is phishing.
		 Unpredictable Phishing (UP): indicates the number of cases where actual URL is phishing but computed total score is zero.
		 Unpredictable Legitimate (UL): indicates the number of cases where actual URL is legitimate but computed total score is zero.
		*/
		double TP = 0,FN = 0,TN = 0,FP = 0,UP = 0,UL = 0;
		// Po = 1 , between 0 and 1
		// Lo = -1, between -1 and 0
		for (String str : phishing_test) {
			List<String> n_gramlist = ngrams(n_gram_size, str);
			double result = 0;
			for (String str2 : n_gramlist) {
				if(new_tst_legi_and_phish.contains(str2)) {
					
					result += new_tst_legi_and_phish.get(str2);
				}
			}
			if(result >0) {
				TP++;
			}
			else if(result == 0) {
				UP++;
			}
			else if(result<0) {
				FN++;
			}
		}
		// Lo = -1, between -1 and 0
		for (String str : legitimate_test) {
			List<String> n_gramlist = ngrams(n_gram_size, str);
			double result = 0;
			for (String str2 : n_gramlist) {
				if(new_tst_legi_and_phish.contains(str2)) {
					result += new_tst_legi_and_phish.get(str2);
				}
			}
			if(result < 0) {
				TN++;
			}
			else if(result == 0) {
				UL++;
			}
			else if(result > 0) {
				FP++;
			}
			
		}
		// Calculate Accuracy
		System.out.println("TP:" + TP + " FN:" + FN + " TN:"+ TN +" FP:"+FP +" Unpredictable Phishing:"+ UP + " Unpredictable Legitimate:"+UL);
		double accuracy = (TP + TN)/(TP + TN + FP + FN + UP + UL);
		System.out.println("Accuracy: "+ accuracy);
		
		System.out.println("Program Finish");
		writer.close();
		sc.close();
	}
}
