/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 */
import java.util.List;

public class TextPost extends Post{
	/**
	 * @see Post#Post(String, double, double, List)
	 * */
	public TextPost(String Text, double alinan_longitude, double alinan_latitude, List<String> alinan_tagged_user) {
		super(Text, alinan_longitude, alinan_latitude, alinan_tagged_user);
	}
	/**
	 * <p>this function overrides abstract class method and gives show tagged users </p>
	 * */
	@Override
	public void show_tagged_users(List<String> alinan) {
		if(alinan.size()!=0){
			System.out.print("Friends tagged in this post: ");
			for(String taglar:alinan){
				System.out.print(taglar);
				if((alinan.size()-1)!=alinan.indexOf(taglar)){
					System.out.print(" ,");
				}
			}
			System.out.println();
		}	
	}

	/**
	 * <p>this function overrides abstract class method and gives show post location </p>
	 * */
	@Override
	public void show_post_location(Location gelen) {
		System.out.print("Location: ");
		System.out.print(gelen.getLongitude()+" , ");
		System.out.println(gelen.getLatitude());
	}
}
