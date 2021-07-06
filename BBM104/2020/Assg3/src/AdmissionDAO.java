import java.util.ArrayList;
import java.util.Collections;

public class AdmissionDAO implements IDataAccessObject2{
	ArrayList<Admission> admissions = new ArrayList<Admission>();
	
	@Override
	public Object getByID(int ID) {
		for (Admission a : admissions) {
			if(a.getAdmission_number() == ID) {
				return a;
			}
		}
		return null;
	}

	@Override
	public Object deleteByID(int ID) {
		for (Admission a : admissions) {
			if(a.getAdmission_number() == ID) {
				admissions.remove(a);
				return a.getPatient_id();
			}
		}
		return null;
	}

	@Override
	public void add(Object object) {
		if(object.getClass() == Admission.class) {
			admissions.add((Admission) object);
		}
		// sorting for database
		Collections.sort(admissions, (a, b) -> Integer.compare(a.getAdmission_number(),b.getAdmission_number()));	
	}

	@Override
	public ArrayList<Admission> getAll() {
		// TODO Auto-generated method stub
		return admissions;
	}

}
