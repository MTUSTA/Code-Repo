import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args) throws IOException {
		List<String[]> veriler = new ArrayList<String[]>();
		try {
			Scanner scanner1 = new Scanner(new File(args[0]));
			Scanner str_sayi = new Scanner(args[1]);
			Scanner TF = new Scanner(args[2]);
			int sayi = str_sayi.nextInt();// define feature number
			String girdi_duzenleme = TF.next();// true false
			if(7<sayi && sayi<=84) {
				/* this while function reads input .csv file */
				while (scanner1.hasNextLine()) {
					String line = scanner1.nextLine();
					String[] duzenlenmis_line = line.split(",");
					veriler.add(duzenlenmis_line);
				}
				FileWriter writer = null;
				if (girdi_duzenleme.equals("T")) {
					writer = new FileWriter(args[0]);
				}
				veriler.remove(0);
				List<Double> veriler_selection_double = new ArrayList<Double>();
				for (String[] str : veriler) {
					double cast = Double.parseDouble(str[sayi-1]);
					veriler_selection_double.add(cast);
				}
				List<Double> veriler_quick_double = new ArrayList<Double>(veriler_selection_double);
				List<Double> veriler_merge_double = new ArrayList<Double>(veriler_selection_double);

				SelectionSort selectsort = new SelectionSort();
				selectsort.selectionSort(veriler, sayi-1, veriler_selection_double);

				if (girdi_duzenleme.equals("T")) {
					writer.append("Flow ID, Source IP, Source Port, Destination IP, Destination Port, Protocol, Timestamp, Flow Duration, Total Fwd Packets, Total Backward Packets,Total Length of Fwd Packets, Total Length of Bwd Packets, Fwd Packet Length Max, Fwd Packet Length Min, Fwd Packet Length Mean, Fwd Packet Length Std,Bwd Packet Length Max, Bwd Packet Length Min, Bwd Packet Length Mean, Bwd Packet Length Std,Flow Bytes/s, Flow Packets/s, Flow IAT Mean, Flow IAT Std, Flow IAT Max, Flow IAT Min,Fwd IAT Total, Fwd IAT Mean, Fwd IAT Std, Fwd IAT Max, Fwd IAT Min,Bwd IAT Total, Bwd IAT Mean, Bwd IAT Std, Bwd IAT Max, Bwd IAT Min,Fwd PSH Flags, Bwd PSH Flags, Fwd URG Flags, Bwd URG Flags, Fwd Header Length, Bwd Header Length,Fwd Packets/s, Bwd Packets/s, Min Packet Length, Max Packet Length, Packet Length Mean, Packet Length Std, Packet Length Variance,FIN Flag Count, SYN Flag Count, RST Flag Count, PSH Flag Count, ACK Flag Count, URG Flag Count, CWE Flag Count, ECE Flag Count, Down/Up Ratio, Average Packet Size, Avg Fwd Segment Size, Avg Bwd Segment Size, Fwd Header Length,Fwd Avg Bytes/Bulk, Fwd Avg Packets/Bulk, Fwd Avg Bulk Rate, Bwd Avg Bytes/Bulk, Bwd Avg Packets/Bulk,Bwd Avg Bulk Rate,Subflow Fwd Packets, Subflow Fwd Bytes, Subflow Bwd Packets, Subflow Bwd Bytes,Init_Win_bytes_forward, Init_Win_bytes_backward, act_data_pkt_fwd, min_seg_size_forward,Active Mean, Active Std, Active Max, Active Min,Idle Mean, Idle Std, Idle Max, Idle Min00\n");
					for(String[] as:veriler) {
						for(int i=0;i<as.length;i++) {
							if(i!=as.length-1) {
								writer.append(as[i]+ ",");
							}
							else if(i==as.length-1) {
								writer.append(as[i]+"\n");
							}
						}
					}
				}

				QuickSort quick = new QuickSort();
				quick.sort(0, veriler_quick_double.size() - 1, veriler_quick_double);

				MergeSort merge = new MergeSort();
				merge.sort(veriler_merge_double);

				if (girdi_duzenleme.equals("T")) {
					writer.flush();
					writer.close();
				}
			}
			scanner1.close();
			str_sayi.close();
			TF.close();
		} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}
	}
}