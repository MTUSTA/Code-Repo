/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Electronic's id
 * @param alinan_parasal_deger the Electronic's price
 * @param alinan_manufacturer the Electronic's manufacturer
 * @param alinan_brand the Electronic's brand
 * @param alinan_maxvolt the Electronic's maxvolt
 * @param alinan_maxwatt the Electronic's maxwatt
 * @see Item#Item(int, double)
 */
public class Electronic extends Item{
	public String manufacturer,brand;
	public int maxvolt,maxwatt;
	public Electronic(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer,String alinan_brand,int alinan_maxvolt,int alinan_maxwatt) {
		super(alinan_item_id, alinan_parasal_deger);
		maxvolt = alinan_maxvolt;
		maxwatt = alinan_maxwatt;
		manufacturer = alinan_manufacturer;
		brand = alinan_brand;
	}

}
