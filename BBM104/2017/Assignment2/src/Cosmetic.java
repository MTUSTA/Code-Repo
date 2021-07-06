/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Cosmetic's id
 * @param alinan_parasal_deger the Cosmetic's price
 * @param alinan_manufacturer the Cosmetic's manufacturer
 * @param alinan_brand the Cosmetic's brand
 * @param alinan_organik the Cosmetic's organic or not
 * @param alinan_son_kullanma_tarihi the Cosmetic's expiration date
 * @param alinan_agirlik the Cosmetic's weight
 * @see Item#Item(int, double)
 */
public class Cosmetic extends Item{
	public String manufacturer,brand,son_kullanma_tarihi;
	public int agirlik,organik;
	public Cosmetic(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_organik,String alinan_son_kullanma_tarihi,int alinan_agirlik) {
		super(alinan_item_id, alinan_parasal_deger);
		organik = alinan_organik;
		son_kullanma_tarihi = alinan_son_kullanma_tarihi;
		agirlik = alinan_agirlik;
		manufacturer = alinan_manufacturer;
		brand = alinan_brand;
	}

}
