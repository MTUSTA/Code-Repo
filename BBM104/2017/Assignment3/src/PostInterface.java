import java.util.Date;
import java.util.UUID;
/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 * 
 * PostInterface.java
 * Interface class that has the following methods.
 */

public interface PostInterface {
	
	/**
	 * <p>This method description for setTEXT</p>
	 * */
	public void setTEXT(String interface_txt);
	/**
	 * <p>This method description for getText</p>
	 * */
	public String getText();
	/**
	 * <p>This method description for getID</p>
	 * */
	public UUID getID();
	/**
	 * <p>This method description for getDate</p>
	 * */
	public Date getDate();

}
