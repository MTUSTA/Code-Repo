/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Smartphone's id
 * @param alinan_parasal_deger the Smartphone's price
 * @param alinan_manufacturer the Smartphone's manufacturer
 * @param alinan_brand the Smartphone's brand
 * @param alinan_maxvolt the Smartphone's maxvolt
 * @param alinan_maxwatt the Smartphone's maxwatt
 * @param alinan_screen_type the Smartphone's screen type
 * @see Electronic#Electronic(int, double, String, String, int, int) 
 */
public class Elec_Smartphone extends Electronic{
	public String screen_type;
	public Elec_Smartphone(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_maxvolt, int alinan_maxwatt,String alinan_screen_type) {
		super(alinan_item_id, alinan_parasal_deger, alinan_manufacturer, alinan_brand, alinan_maxvolt, alinan_maxwatt);
		screen_type = alinan_screen_type;
	}

}
