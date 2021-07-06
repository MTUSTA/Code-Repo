import java.util.ArrayList;
import java.util.Collections;

public class PatientDAO implements IDataAccessObject{
	ArrayList<Patient> patients = new ArrayList<Patient>();

	@Override
	public Object getByID(int ID) {
		for (Patient p : patients) {
			if(p.getPatiend_id()== ID) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Object deleteByID(int ID) {
		for (Patient p : patients) {
			if(p.getPatiend_id() == ID) {
				patients.remove(p);
				return p.getName();
			}
		}
		return null;
	}

	@Override
	public void add(Object object) {
		if(object.getClass() == Patient.class) {
			patients.add((Patient) object);
		}
		// sorted by id --> update database
		Collections.sort(patients, (a, b) -> Integer.compare(a.getPatiend_id(), b.getPatiend_id()));
	}

	@Override
	public ArrayList<Patient> getAll() {
		// TODO Auto-generated method stub
		return patients;
	}



}
