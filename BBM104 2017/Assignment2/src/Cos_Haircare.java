/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Haircare's id
 * @param alinan_parasal_deger the Haircare's price
 * @param alinan_manufacturer the Haircare's manufacturer
 * @param alinan_brand the Haircare's brand
 * @param alinan_organik the Haircare's organic or not
 * @param alinan_son_kullanma_tarihi the Haircare's expiration date
 * @param alinan_agirlik the Haircare's weight
 * @param alinan_medicated the Haircare's  medicate or not
 * @see Cosmetic#Cosmetic(int, double, String, String, int, String, int)
 */
public class Cos_Haircare extends Cosmetic{
	public int medicated ;
	public Cos_Haircare(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_organik,String alinan_son_kullanma_tarihi, int alinan_agirlik,int alinan_medicated) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer, alinan_brand, alinan_organik, alinan_son_kullanma_tarihi, alinan_agirlik);
		medicated = alinan_medicated;
	}
}
