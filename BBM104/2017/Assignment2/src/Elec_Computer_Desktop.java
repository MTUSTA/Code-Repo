/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Desktop's id
 * @param alinan_parasal_deger the Desktop's price
 * @param alinan_manufacturer the Desktop's manufacturer
 * @param alinan_brand the Desktop's brand
 * @param alinan_maxvolt the Desktop's maxvolt
 * @param alinan_maxwatt the Desktop's maxwatt
 * @param alinan_OS the Desktop's OS
 * @param alinan_cpu_name the Desktop's cpu name
 * @param alinan_ramcapacity the Desktop's ram capacity
 * @param alinan_hddcapacity the Desktop's hdd capacity
 * @param alinan_kutu_rengi the Desktop's box_colour
 * @see Elec_Computer#Elec_Computer(int, double, String, String, int, int, String, String, int, int)
 */
public class Elec_Computer_Desktop extends Elec_Computer{
	public String kutu_rengi;
	public Elec_Computer_Desktop(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_maxvolt,int alinan_maxwatt,String alinan_OS, String alinan_cpu_name, int alinan_ramcapacity, int alinan_hddcapacity,String alinan_kutu_rengi) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer, alinan_brand, alinan_maxvolt, alinan_maxwatt,alinan_OS, alinan_cpu_name, alinan_ramcapacity,alinan_hddcapacity);
		kutu_rengi = alinan_kutu_rengi;		
	}

}
