import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Reader {
	// DAO objects
	IDataAccessObject patient_process = new PatientDAO();
	IDataAccessObject2 admission_process = new AdmissionDAO();

	// line reader
	public String[] readfile(String path) {

		try {
			int i = 0;
			int len = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[len];
			for (String line : Files.readAllLines(Paths.get(path))) {
				// delete newline character
				line = line.replace("\n", "").replace("\r", "");
				results[i++] = line;
			}
			return results;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// process for input.txt
	public void read_input(String[] lines) throws IOException {

		for (int i = 0; i < lines.length; i++) {
			String[] split_line = lines[i].split(" ");

			// addres string concat
			if (split_line[0].compareTo("AddPatient") == 0) {
				int id = Integer.parseInt(split_line[1]);
				String address = "Address: ";
				for (int j = 5; j < split_line.length; j++) {
					address += split_line[j];
					if (j < split_line.length - 1) {
						address += " ";
					}
				}
				// patient adding in database
				patient_process.add(new Patient(id, split_line[2], split_line[3], split_line[4], address));
				Main.writer.write("Patient " + Integer.toString(id) + " " + split_line[2] + " added\n");
			}

			// remove patient from database
			if (split_line[0].compareTo("RemovePatient") == 0) {
				int id = Integer.parseInt(split_line[1]);
				Object b = patient_process.deleteByID(id);
				// if deleted
				if (b != null) {
					Main.writer.write("Patient " + id + " " + b + " removed\n");
				}
				// i cant change DAO so i need to get all list
				for (Admission element : admission_process.getAll()) {
					if (element.getPatient_id() == id) {
						admission_process.deleteByID(element.getAdmission_number());
						break;
					}
				}
			}
			// create admission
			if (split_line[0].compareTo("CreateAdmission") == 0) {
				// number parsing
				int admission_number = Integer.parseInt(split_line[1]);
				int patient_id = Integer.parseInt(split_line[2]);
				// add admission in database
				admission_process.add(new Admission(admission_number, patient_id));
				Main.writer.write("Admission " + split_line[1] + " created\n");
			}
			// add examination in admission
			if (split_line[0].compareTo("AddExamination") == 0) {

				String[] newArray = Arrays.copyOfRange(split_line, 3, split_line.length);

				// outpatient examination
				if (split_line[2].compareTo("Outpatient") == 0) {
					int admission_id = Integer.parseInt(split_line[1]);
					Outpatient out = new Outpatient();
					out.addoperation(newArray);
					// admission_process.getAll().get(index-1).getExaminations().add(out);

					Admission returned = (Admission) admission_process.getByID(admission_id);
					// if admission exist
					if (returned != null) {
						returned.getExaminations().add(out);
						Main.writer.write("Outpatient examination added to admission " + split_line[1] + "\n");
					}

				}
				// inpatient examination
				else if (split_line[2].compareTo("Inpatient") == 0) {
					int admission_id = Integer.parseInt(split_line[1]);
					Inpatient in = new Inpatient();
					in.addoperation(newArray);
					// admission_process.getAll().get(index-1).getExaminations().add(in);

					Admission returned = (Admission) admission_process.getByID(admission_id);
					// if admission exist
					if (returned != null) {
						returned.getExaminations().add(in);
						Main.writer.write("Inpatient examination added to admission " + split_line[1] + "\n");
					}
				}
			}
			// calculate total cost
			if (split_line[0].compareTo("TotalCost") == 0) {
				int admission_id = Integer.parseInt(split_line[1]);
				Admission returned = (Admission) admission_process.getByID(admission_id);
				Main.writer.write("TotalCost for admission " + admission_id + "\n");
				
				int total = 0;
				// total cost calculation
				if (returned != null) {
					for (Examination e : returned.getExaminations()) {
						Main.writer.write("\t" + e.getType() + " ");
						e.printoperations();
						Main.writer.write(" ");
						total += e.cost();
						Main.writer.write(e.cost() + "$");
						Main.writer.write("\n");
					}
				}
				Main.writer.write("\tTotal: " + total + "$" + "\n");
			}
			// list all patient line by line
			if (split_line[0].compareTo("ListPatients") == 0) {
				// create new list without reference
				ArrayList<Patient> new_list = new ArrayList<Patient>(patient_process.getAll());
				// sorted by id --> update database
				Collections.sort(new_list, (a, b) -> a.getName().compareTo(b.getName()));

				Main.writer.write("Patient List:\n");
				for (Patient p : new_list) {
					Main.writer.write(p.getPatiend_id() + " " + p.getName() + " " + p.getSurname() + " "
							+ p.getPhone_number() + " " + p.getAddress() + "\n");
				}
			}
		}
	}

	// read patient database
	public void read_patient() {
		// database txt lines
		String[] lines = readfile("patient.txt");
		for (String line : lines) {
			// split lines
			String[] split_line = line.split("\t");
			// parse integer
			int id = Integer.parseInt(split_line[0]);
			// split name and surname
			String[] name_surname = split_line[1].split(" ");

			patient_process.add(new Patient(id, name_surname[0], name_surname[1], split_line[2], split_line[3]));
		}
	}

	// read admission database
	public void read_admission() {
		String[] lines = readfile("admission.txt");
		int admission_number = 0, patient_id = 0;
		for (String line : lines) {
			String[] split_line = line.split("\t");
			// create outpatient examination
			if (split_line[0].compareTo("Outpatient") == 0) {
				// creeate outpatient
				Outpatient out = new Outpatient();
				String[] splitted = split_line[1].split(" ");
				out.addoperation(splitted);

				Admission returned = (Admission) admission_process.getByID(admission_number);
				if (returned != null) {
					returned.getExaminations().add(out);
				}

			}// create Inpatient examination 
			else if (split_line[0].compareTo("Inpatient") == 0) {

				Inpatient in = new Inpatient();
				String[] splitted = split_line[1].split(" ");
				in.addoperation(splitted);

				Admission returned = (Admission) admission_process.getByID(admission_number);
				if (returned != null) {
					returned.getExaminations().add(in);
				}

			}
			// create admission
			else {

				admission_number = Integer.parseInt(split_line[0]);
				patient_id = Integer.parseInt(split_line[1]);
				admission_process.add(new Admission(admission_number, patient_id));
			}
		}

	}

	// save database in file
	public void writer_patient() throws IOException {
		ArrayList<Patient> list1 = patient_process.getAll();
		for (int i = 0; i < list1.size(); i++) {
			Main.writer.write(
					list1.get(i).getPatiend_id() + "\t" + list1.get(i).getName() + " " + list1.get(i).getSurname()
							+ "\t" + list1.get(i).getPhone_number() + "\t" + list1.get(i).getAddress());
			if (i != list1.size() - 1) {
				Main.writer.write("\n");
			}
		}

	}

	public void writer_admission() throws IOException {
		ArrayList<Admission> list1 = admission_process.getAll();

		for (int i = 0; i < list1.size(); i++) {
			Main.writer.write(list1.get(i).getAdmission_number() + "\t" + list1.get(i).getPatient_id() + "\n");

			for (int j = 0; j < list1.get(i).getExaminations().size(); j++) {
				Main.writer.write(list1.get(i).getExaminations().get(j).getType() + "\t");
				list1.get(i).getExaminations().get(j).printoperations();
				if (!(i == list1.size() - 1 && j == list1.get(i).getExaminations().size() - 1)) {
					Main.writer.write("\n");
				}
			}

		}
	}

}
