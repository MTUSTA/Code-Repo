/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017 
 * @param alinan_item_id the Cddvd's id
 * @param alinan_parasal_deger the Cddvd's price
 * @param alinan_release_date the Cddvd's release_date
 * @param alinan_cover_title the Cddvd's cover_title
 * @param alinan_composer the Cddvd's composer
 * @param alinan_songs the Cddvd's songs
 * @see Office_Supplies#Office_Supplies(int, double, int, String)
 **/
public class Off_Cddvd extends Office_Supplies{
	public String composer,songs;
	
	public Off_Cddvd(int alinan_item_id, double alinan_parasal_deger, int alinan_release_date,String alinan_cover_title,String alinan_composer,String alinan_songs) {
		super(alinan_item_id, alinan_parasal_deger, alinan_release_date, alinan_cover_title);
		composer = alinan_composer;
		songs = alinan_songs;		
	}

}
