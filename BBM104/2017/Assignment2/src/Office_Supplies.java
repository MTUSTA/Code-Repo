/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017 
 * @param alinan_item_id the Office Supplies's id
 * @param alinan_parasal_deger the Office Supplies's price
 * @param alinan_release_date the Office Supplies's release_date
 * @param alinan_cover_title the Office Supplies's cover_title
 * @see Item#Item(int, double)
 **/
public class Office_Supplies extends Item{
	public int release_date;
	public String cover_title;
	public Office_Supplies(int alinan_item_id, double alinan_parasal_deger,int alinan_release_date,String alinan_cover_title) {
		super(alinan_item_id, alinan_parasal_deger);
		release_date = alinan_release_date;
		cover_title = alinan_cover_title;

	}

}
