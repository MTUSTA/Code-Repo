/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Tablet's id
 * @param alinan_parasal_deger the Tablet's price
 * @param alinan_manufacturer the Tablet's manufacturer
 * @param alinan_brand the Tablet's brand
 * @param alinan_maxvolt the Tablet's maxvolt
 * @param alinan_maxwatt the Tablet's maxwatt
 * @param alinan_OS the Tablet's OS
 * @param alinan_cpu_name the Tablet's cpu name
 * @param alinan_ramcapacity the Tablet's ram capacity
 * @param alinan_hddcapacity the Tablet's hdd capacity
 * @param alinan_dimension the Tablet's dimension
 * @see Elec_Computer#Elec_Computer(int, double, String, String, int, int, String, String, int, int)
 */
public class Elec_Computer_Tablet extends Elec_Computer{
	public int dimension;
	public Elec_Computer_Tablet(int alinan_item_id, double alinan_parasal_deger, String alinan_manufacturer, String alinan_brand, int alinan_maxvolt,int alinan_maxwatt,String alinan_OS, String alinan_cpu_name, int alinan_ramcapacity, int alinan_hddcapacity,int alinan_dimension) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer, alinan_brand, alinan_maxvolt, alinan_maxwatt, alinan_OS, alinan_cpu_name, alinan_ramcapacity,alinan_hddcapacity);
		dimension = alinan_dimension;
	}

}
