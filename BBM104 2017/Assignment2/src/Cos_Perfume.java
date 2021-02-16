/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Perfume's id
 * @param alinan_parasal_deger the Perfume's price
 * @param alinan_manufacturer the Perfume's manufacturer
 * @param alinan_brand the Perfume's brand
 * @param alinan_organik the Perfume's organic or not
 * @param alinan_son_kullanma_tarihi the Perfume's expiration date
 * @param alinan_agirlik the Perfume's weight
 * @param alinan_lasting_duration the Perfume's  lasting duration
 * @see Cosmetic#Cosmetic(int, double, String, String, int, String, int)
 */
public class Cos_Perfume extends Cosmetic{
	public int lasting_duration;
	public Cos_Perfume(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_organik,String alinan_son_kullanma_tarihi, int alinan_agirlik,int alinan_lasting_duration) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer, alinan_brand, alinan_organik, alinan_son_kullanma_tarihi, alinan_agirlik);
		lasting_duration = alinan_lasting_duration;	
	}
}