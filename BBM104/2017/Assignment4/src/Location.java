/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 */
public class Location {
	private double latitude,longitude;
/**
 * @param latitude the latitude
 * @param longitude the longitude
 * */
	public Location(double latitude, double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
	/**
	 * @return the location latitude
	 * */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * <p>this method set latitude</p>
	 * */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the location longitude
	 * */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * <p>this method set longitude</p>
	 * */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	

}
