/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 */
import java.util.List;

public class VideoPost extends TextPost{
	private String video_file_name;
	private int video_duration;
	/**
	 * @param video_file_name	the videopost's video file name
	 * @param video_duration	the videopost's video duration
	 * @see TextPost#TextPost(String, double, double, List)
	 * */
	public VideoPost(String Text, double alinan_longitude, double alinan_latitude, List<String> alinan_tagged_user,String video_file_name,int video_duration) {
		super(Text, alinan_longitude, alinan_latitude, alinan_tagged_user);
		this.setVideo_file_name(video_file_name);
		this.setVideo_duration(video_duration);
		
	}
	/**
	 * @return the video's file name
	 * */
	public String getVideo_file_name() {
		return video_file_name;
	}
	/**
	 * <p>this method sets video's file name</p>
	 * */
	public void setVideo_file_name(String video_file_name) {
		this.video_file_name = video_file_name;
	}
	/**
	 * @return the video's duration
	 * */
	public int getVideo_duration() {
		return video_duration;
	}
	/**
	 * <p>this method sets video's duration</p>
	 * */
	public void setVideo_duration(int video_duration) {
		this.video_duration = video_duration;
	}

}

