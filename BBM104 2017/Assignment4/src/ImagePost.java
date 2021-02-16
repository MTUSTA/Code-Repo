/**
 * @author Mehmet Taha USTA
 * @version 1.0,12 April 2017
 */
import java.util.List;

public class ImagePost extends TextPost {
	private String image_filename;
	private int[] image_resolution = new int[2];
	
	/**
	 * @param image_filename	the image's file name
	 * @param width	the image's width
	 * @param height	the image's height
	 * @see TextPost#TextPost(String, double, double, List)
	 * */
	public ImagePost(String Text, double alinan_longitude, double alinan_latitude, List<String> alinan_tagged_user,String image_filename,int width ,int height) {
		super(Text, alinan_longitude, alinan_latitude, alinan_tagged_user);
		this.setImage_filename(image_filename);
		image_resolution[0]=width;
		image_resolution[1]=height;
	}
	/**
	 * @return image's resolution
	 * */
	public int[] getImage_resolution() {
		return image_resolution;
	}
	/**
	 * <p>this method set image's resolution</p>
	 * */
	public void setImage_resolution(int[] image_resolution) {
		this.image_resolution = image_resolution;
	}
	/**
	 * @return image's file name
	 * */
	public String getImage_filename() {
		return image_filename;
	}
	/**
	 * <p>this method set image's file name</p>
	 * */
	public void setImage_filename(String image_filename) {
		this.image_filename = image_filename;
	}


}
