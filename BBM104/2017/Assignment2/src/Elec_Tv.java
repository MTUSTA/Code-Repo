/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Tv's id
 * @param alinan_parasal_deger the Tv's price
 * @param alinan_manufacturer the Tv's manufacturer
 * @param alinan_brand the Tv's brand
 * @param alinan_maxvolt the Tv's maxvolt
 * @param alinan_maxwatt the Tv's maxwatt
 * @param alinan_screen_size the Tv's screen_size
 * @see Electronic#Electronic(int, double, String, String, int, int) 
 */
public class Elec_Tv extends Electronic{
	public int screen_size;
	public Elec_Tv(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_maxvolt, int alinan_maxwatt,int alinan_screen_size) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer,alinan_brand, alinan_maxvolt, alinan_maxwatt);
		screen_size = alinan_screen_size;
	}

}
