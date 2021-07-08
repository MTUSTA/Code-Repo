import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {
	// file line reader method
	private ArrayList<Personnel> personnel = new ArrayList<Personnel>();

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
	
	/*create object and add objectt in arraylist --> then return private arraylist*/
	public ArrayList<Personnel> create_object(String[] lines) {
		for (String string1 : lines) {
			// split line
			String[] split_string = string1.split("\t");
			// split name and surname
			String[] name_surname = split_string[0].split(" ");
			// string to int for yearOfstart
			int year_start = Integer.parseInt(split_string[3]);
			// create object and add arraylist
			if (split_string[2].compareTo("FACULTY_MEMBER") == 0) {
				this.personnel.add(new FacultyMember(name_surname[0], name_surname[1], split_string[1], split_string[2],year_start));
			} else if (split_string[2].compareTo("RESEARCH_ASSISTANT") == 0) {
				this.personnel.add(new ResearchAssistant(name_surname[0], name_surname[1], split_string[1],split_string[2], year_start));
			} else if (split_string[2].compareTo("WORKER") == 0) {
				this.personnel.add(new Worker(name_surname[0], name_surname[1], split_string[1], split_string[2], year_start));
			} else if (split_string[2].compareTo("CHIEF") == 0) {
				this.personnel.add(new Chief(name_surname[0], name_surname[1], split_string[1], split_string[2], year_start));
			} else if (split_string[2].compareTo("PARTTIME_EMPLOYEE") == 0) {
				this.personnel.add(new PartTimeEmployee(name_surname[0], name_surname[1], split_string[1],split_string[2], year_start));
			} else if (split_string[2].compareTo("SECURITY") == 0) {
				this.personnel.add(new Security(name_surname[0], name_surname[1], split_string[1], split_string[2], year_start));
			} else if (split_string[2].compareTo("OFFICER") == 0) {
				this.personnel.add(new Officer(name_surname[0], name_surname[1], split_string[1], split_string[2], year_start));
			}
		}
		return this.personnel;
	}
	// parse weekyly hours and calculate extra money(addcoursefee, overworksalary,hoursofwork)
	public void monitoring(String[] monitoring, ArrayList<Personnel> personel_objts) {
		for (String string2 : monitoring) {
			String[] split_string2 = string2.split("\t");
			//foreach arraylist
			for (Personnel personnel : personel_objts) {
				// find personnel by registernumber
				if(personnel.getRegisterNumber().compareTo(split_string2[0])== 0) {
					// find personnel class for calculate total_salary --> type casting
					if(personnel.getClass() == FacultyMember.class) {
						FacultyMember var1 = ((FacultyMember)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_addCourseFee(weekly_hours);
						}
					}
					else if(personnel.getClass() == ResearchAssistant.class) {
						ResearchAssistant var1 = ((ResearchAssistant)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_addCourseFee(weekly_hours);
						}
					}
					else if(personnel.getClass() == Officer.class) {
						Officer var1 = ((Officer)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_overWorkSalary(weekly_hours);
						}
					}
					else if(personnel.getClass() == Security.class) {
						Security var1 = ((Security)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_hourOfWork(weekly_hours);
						}
					}
					else if(personnel.getClass() == PartTimeEmployee.class) {
						PartTimeEmployee var1 = ((PartTimeEmployee)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_overWorkSalary(weekly_hours);
						}
					}
					else if(personnel.getClass() == Worker.class) {
						Worker var1 = ((Worker)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_overWorkSalary(weekly_hours);
						}
					}
					else if(personnel.getClass() == Chief.class) {
						Chief var1 = ((Chief)personnel);
						for (int i = 1; i < split_string2.length; i++) {
							int weekly_hours = Integer.parseInt(split_string2[i]);
							var1.calculate_overWorkSalary(weekly_hours);
						}
					}
				}
			}
		}

	}


}
