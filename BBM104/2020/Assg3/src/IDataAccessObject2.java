import java.util.ArrayList;

public interface IDataAccessObject2 {
	/**
	 * Read a single entry from file
	 * 
	 * @param ID id of the patient
	 * @return Object patient with given ID
	 */
	Object getByID(int ID);

	/**
	 * Delete a single entry from file
	 * 
	 * @param ID id of patient
	 * @return result of operation
	 */
	Object deleteByID(int ID);

	/**
	 * Add a new object
	 * 
	 * @param object object to be added
	 */
	void add(Object object);

	/**
	 * Returns all entries in a list
	 * 
	 * @return List list of all entries
	 */
	ArrayList<Admission> getAll();

}
