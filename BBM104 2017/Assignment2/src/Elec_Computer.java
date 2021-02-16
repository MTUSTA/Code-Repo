/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the computer's id
 * @param alinan_parasal_deger the computer's price
 * @param alinan_manufacturer the computer's manufacturer
 * @param alinan_brand the computer's brand
 * @param alinan_maxvolt the computer's maxvolt
 * @param alinan_maxwatt the computer's maxwatt
 * @param alinan_OS the computer's OS
 * @param alinan_cpu_name the computer's cpu name
 * @param alinan_ramcapacity the computer's ram capacity
 * @param alinan_hddcapacity the computer's hdd capacity
 * @see Electronic#Electronic(int, double, String, String, int, int) 
 */


public class Elec_Computer extends Electronic {
	public String cpu_name,OS;
	public int ramcapacity,hddcapacity;

	public Elec_Computer(int alinan_item_id, double alinan_parasal_deger, String alinan_manufacturer, String alinan_brand, int alinan_maxvolt, int alinan_maxwatt,String alinan_OS, String alinan_cpu_name, int alinan_ramcapacity,	int alinan_hddcapacity) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer,alinan_brand, alinan_maxvolt, alinan_maxwatt);
		cpu_name = alinan_cpu_name;
		ramcapacity = alinan_ramcapacity;
		hddcapacity = alinan_hddcapacity;
		OS = alinan_OS;
	}

}
