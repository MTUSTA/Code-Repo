/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Skincare's id
 * @param alinan_parasal_deger the Skincare's price
 * @param alinan_manufacturer the Skincare's manufacturer
 * @param alinan_brand the Skincare's brand
 * @param alinan_organik the Skincare's organic or not
 * @param alinan_son_kullanma_tarihi the Skincare's expiration date
 * @param alinan_agirlik the Skincare's weight
 * @param alinan_babySensitive the Skincare's  babysenisitive or not
 * @see Cosmetic#Cosmetic(int, double, String, String, int, String, int)
 */
public class Cos_Skincare extends Cosmetic{
	public int babySensitive;
	public Cos_Skincare(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_organik,String alinan_son_kullanma_tarihi, int alinan_agirlik,int alinan_babySensitive) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer, alinan_brand, alinan_organik, alinan_son_kullanma_tarihi, alinan_agirlik);
		babySensitive = alinan_babySensitive;
	}
}