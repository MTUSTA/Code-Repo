/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_item_id the Laptop's id
 * @param alinan_parasal_deger the Laptop's price
 * @param alinan_manufacturer the Laptop's manufacturer
 * @param alinan_brand the Laptop's brand
 * @param alinan_maxvolt the Laptop's maxvolt
 * @param alinan_maxwatt the Laptop's maxwatt
 * @param alinan_OS the Laptop's OS
 * @param alinan_cpu_name the Laptop's cpu name
 * @param alinan_ramcapacity the Laptop's ram capacity
 * @param alinan_hddcapacity the Laptop's hdd capacity
 * @param alinan_hdmi_support the Laptop's hdmi support
 * @see Elec_Computer#Elec_Computer(int, double, String, String, int, int, String, String, int, int)
 */
public class Elec_Computer_Laptop extends Elec_Computer{
	public int hdmi_support;
	public Elec_Computer_Laptop(int alinan_item_id, double alinan_parasal_deger,String alinan_manufacturer, String alinan_brand, int alinan_maxvolt,int alinan_maxwatt,String alinan_OS, String alinan_cpu_name, int alinan_ramcapacity, int alinan_hddcapacity,int alinan_hdmi_support) {
		super(alinan_item_id, alinan_parasal_deger,alinan_manufacturer, alinan_brand, alinan_maxvolt, alinan_maxwatt,alinan_OS, alinan_cpu_name, alinan_ramcapacity,alinan_hddcapacity);
		hdmi_support = alinan_hdmi_support;
	}
}