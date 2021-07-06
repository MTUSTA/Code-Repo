/**
 * @author Mehmet Taha USTA
 * @version 1.0,12 April 2017
 */


import java.util.Date;
import java.util.List;
import java.util.UUID;

public  abstract class Post implements PostInterface{

	private UUID ID= UUID.randomUUID();
	private String Text;
	private Date date1;
	private Location konum= new Location(0, 0);
	private List<String> collection_of_tagged_friends;
	/**
	 * @param Text	the post text
	 * @param alinan_longitude	the longitude
	 * @param alinan_latitude	the latitude
	 * @param alinan_tagged_user	the target tagged user list or users list
	 * */
	
	public Post(String Text,double alinan_longitude,double alinan_latitude,List<String> alinan_tagged_user) {
		this.Text = Text;
		konum.setLongitude(alinan_longitude);
		konum.setLatitude(alinan_latitude);
		setCollection_of_tagged_friends(alinan_tagged_user);
		this.date1= new Date();
	}
	/**
	 * <p>this function overrides interface class method</p>
	 * @param interface_txt for set person text message
	 * */
	@Override
	public void setTEXT(String interface_txt) {	
		Text= interface_txt;	
	}
	/**
	 * <p>this function overrides interface class method</p>
	 * @return	the person text message
	 * */
	@Override
	public String getText() {	
		return Text;
	}
	/**
	 * <p>this function overrides interface class method</p>
	 * @return	the person text id
	 * */
	@Override
	public UUID getID() {	
		return ID;
	}

	/**
	 * <p>this function overrides interface class method</p>
	 * @return	the person text message date (when the post originated)
	 * */
	@Override
	public Date getDate() {
		return date1;
	}
	/**
	 * @return	the target tagged user list
	 * */
	public List<String> getCollection_of_tagged_friends() {
		return collection_of_tagged_friends;
	}
	/**
	 * <p>this function sets collection of tagged friends</p>
	 * */
	public void setCollection_of_tagged_friends(List<String> alinan_tagged_user) {
		this.collection_of_tagged_friends = alinan_tagged_user;
	}
	/**
	 * @return location variable
	 * */
	public Location getKonum() {
		return konum;
	}
	/**
	 * <p>this function sets location</p>
	 * */
	public void setKonum(Location konum) {
		this.konum = konum;
	}
	/**
	 * <p>this abstract method defined for subclass for show tagged users</p>
	 * */
	public abstract void show_tagged_users( List<String> alinan);
	/**
	 * <p>this abstract method defined for subclass for show post location	</p>
	 * */
	public abstract void show_post_location(Location gelen);

}
