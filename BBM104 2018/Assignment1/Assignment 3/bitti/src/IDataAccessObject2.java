import java.util.List;
import java.util.ArrayList;


public interface IDataAccessObject2 {

    /**
     *  Read a single entry from file
     * @param ID id of the customer
     * @return  Object customer with given ID
     */
            Object getByID(int ID);

    /**
     *   Delete a single entry from file
     * @param ID  id of customer
     * @return result of operation
     */
            boolean deleteByID(int ID);

    /**
     *  Add a new object
     * @param object  object to be added
     */
            void 	add(int object1,int object2);

    /**
     *  Update an entry
     * @param object   entry to be updated
     */
            void 	update(Object object);

    /**
     * Returns all entries  in a list
     * @return   List list of all entries
     */
            ArrayList<Order> getALL(); // get all entries

}
