import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {	
	public static FileWriter writer = null;
	
	public static void main(String[] args) throws IOException {
		writer = new FileWriter(new File("output.txt"));
		
		Reader rd = new Reader();
		
		// read patient database
		rd.read_patient();
		// read admission database
		rd.read_admission();
		// input txt and process
		String[] lines = rd.readfile(args[0]);
		rd.read_input(lines);
		// write patient database
		writer.flush();
		writer = new FileWriter(new File("patient.txt"));
		rd.writer_patient();
		// write admission database
		writer.flush();
		writer = new FileWriter(new File("admission.txt"));
		rd.writer_admission();
		
		writer.close();
	}
}
