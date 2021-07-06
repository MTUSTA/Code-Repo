import java.util.ArrayList;

public class Admission {
	private ArrayList<Examination> examinations = new ArrayList<Examination>();
	private int admission_number,patient_id;

	public Admission(int index, int patient_id) {
		this.admission_number = index;
		this.patient_id = patient_id;
	}

	public int getAdmission_number() {
		return admission_number;
	}

	public void setAdmission_number(int admission_number) {
		this.admission_number = admission_number;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}


	/**
	 * @return the examinations
	 */
	public ArrayList<Examination> getExaminations() {
		return examinations;
	}


	/**
	 * @param examinations the examinations to set
	 */
	public void setExaminations(ArrayList<Examination> examinations) {
		this.examinations = examinations;
	}
	
}
