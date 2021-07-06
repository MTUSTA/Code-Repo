import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) {
		//reader object
		Reader rd = new Reader();
		//read personnel.txt
		String[] personnel = rd.readfile(args[0]);
		//create personnel objects
		ArrayList<Personnel> personel_objts = rd.create_object(personnel);
		//read monitoring.txt
		String[] monitoring = rd.readfile(args[1]);
		//calculates transactions
		rd.monitoring(monitoring,personel_objts);
		// object for writer
		Salary calc = new Salary();
		//write all personnel object info in file
		calc.calculate_write(personel_objts);

	}

}
