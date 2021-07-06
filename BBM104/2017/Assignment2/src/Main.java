
/**
 * @author Mehmet Taha Usta
 * @version 1.0 12 April 2017
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
	/**
	 * <p>This list keeps admins</p>
	 * */
	public static List<Admin> admins = new ArrayList<Admin>();
	/**
	 * <p>This list keeps customers</p>
	 * */
	public static List<Customer> customers = new ArrayList<Customer>();
	/**
	 * <p>This list keeps Technicians</p>
	 * */
	public static List<Technician> techs = new ArrayList<Technician>();
	/**
	 * <p>This list keeps haircare items</p>
	 * */
	public static List<Cos_Haircare> haircares = new ArrayList<Cos_Haircare>();
	/**
	 * <p>This list keeps perfume items</p>
	 * */
	public static List<Cos_Perfume> perfumes = new ArrayList<Cos_Perfume>();
	/**
	 * <p>This list keeps skincare items</p>
	 * */
	public static List<Cos_Skincare> skincares = new ArrayList<Cos_Skincare>();
	/**
	 * <p>This list keeps desktop items</p>
	 * */
	public static List<Elec_Computer_Desktop> desktops = new ArrayList<Elec_Computer_Desktop>();
	/**
	 * <p>This list keeps laptop items</p>
	 * */
	public static List<Elec_Computer_Laptop> laptops = new ArrayList<Elec_Computer_Laptop>();
	/**
	 * <p>This list keeps tablet items</p>
	 * */
	public static List<Elec_Computer_Tablet> tablets = new ArrayList<Elec_Computer_Tablet>();
	/**
	 * <p>This list keeps smarthphone items</p>
	 * */
	public static List<Elec_Smartphone> smartphones = new ArrayList<Elec_Smartphone>();
	/**
	 * <p>This list keeps tv items</p>
	 * */
	public static List<Elec_Tv> tvs = new ArrayList<Elec_Tv>();
	/**
	 * <p>This list keeps book items</p>
	 * */
	public static List<Off_Book> books = new ArrayList<Off_Book>();
	/**
	 * <p>This list keeps Cddvd items</p>
	 * */
	public static List<Off_Cddvd> cddvds = new ArrayList<Off_Cddvd>();
	/**
	 * <p>This list keeps campaigns</p>
	 * */
	public static List<Campaign> campaigns = new ArrayList<Campaign>();
	/**
	 * <p>Main is reads files ,create users and items,  runs progress command line</p>
	 * @throws ParseException 
	 * */
	public static void main(String[] args) throws ParseException {
		try {
			Scanner kullanicilar = new Scanner(new File(args[0]));
			Scanner itemler = new Scanner(new File(args[1]));
			Scanner komutlar = new Scanner(new File(args[2]));
			while (kullanicilar.hasNextLine()) {
				String kullanicilar_satir = kullanicilar.nextLine();
				String[] splitlenmis_kullanici_satir = kullanicilar_satir.split("\t");
				if (splitlenmis_kullanici_satir[0].equals("ADMIN")) {
					Create_admin(splitlenmis_kullanici_satir);
				}
				if (splitlenmis_kullanici_satir[0].equals("CUSTOMER")) {
					Create_customer(splitlenmis_kullanici_satir);
				}
				if (splitlenmis_kullanici_satir[0].equals("TECH")) {
					Create_tech(splitlenmis_kullanici_satir);
				}
			}
			while (itemler.hasNextLine()) {
				String itemler_satir = itemler.nextLine();
				String[] splitlenmis_itemler_satir = itemler_satir.split("\t");
				if (splitlenmis_itemler_satir[0].equals("BOOK")) {
					Create_book(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("CDDVD")) {
					Create_cddvd(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("DESKTOP")) {
					Create_desktop(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("LAPTOP")) {
					Create_laptop(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("TABLET")) {
					Create_tablet(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("TV")) {
					Create_tv(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("SMARTPHONE")) {
					Create_smarthphone(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("HAIRCARE")) {
					Create_haircare(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("PERFUME")) {
					Create_perfume(splitlenmis_itemler_satir);
				}
				if (splitlenmis_itemler_satir[0].equals("SKINCARE")) {
					Create_skincare(splitlenmis_itemler_satir);
				}
			}
			while (komutlar.hasNextLine()) {
				String komutlar_satir = komutlar.nextLine();
				String[] splitlenmis_komutlar_satir = komutlar_satir.split("\t");
				if (splitlenmis_komutlar_satir[0].equals("ADDCUSTOMER")) {
					ADDCOSTUMER(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWCUSTOMER")) {
					SHOWCUSTOMER(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWCUSTOMERS")) {
					SHOWCUSTOMERS(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("ADDTOCART")) {
					ADDTOCART(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("ORDER")) {
					ORDER(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWADMININFO")) {
					SHOWADMININFO(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("CREATECAMPAIGN")) {
					CREATECAMPAIGN(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWCAMPAIGNS")) {
					SHOWCAMPAIGNS(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("DEPOSITMONEY")) {
					DEPOSITMONEY(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("CHPASS")) {
					CHPASS(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("EMPTYCART")) {
					EMPTYCART(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWORDERS")) {
					SHOWORDERS(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWITEMSLOWONSTOCK")) {
					SHOWITEMSLOWONSTOCK(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("SHOWVIP")) {
					SHOWVIP(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("ADDADMIN")) {
					ADDADMIN(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("ADDTECH")) {
					ADDTECH(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("ADDITEM")) {
					ADDITEM(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("LISTITEM")) {
					LISTITEM(splitlenmis_komutlar_satir);
				}
				if (splitlenmis_komutlar_satir[0].equals("DISPITEMSOF")) {
					DISPITEMSOF(splitlenmis_komutlar_satir);
				}
			}
			kullanicilar.close();
			itemler.close();
			komutlar.close();
		} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}
	}
	/**
	 * <p>Create_admin function creates admin object and adds admins list</p>
	 * */
	public static void Create_admin(String[] alinan_splitlenmis_kullanici_satir) {
		double maas_admin = Double.parseDouble(alinan_splitlenmis_kullanici_satir[4]);
		admins.add(new Admin(alinan_splitlenmis_kullanici_satir[1], alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], maas_admin, alinan_splitlenmis_kullanici_satir[5]));
	}
	/**
	 * <p>customer_id variable gives number created customer </p>
	 * */
	public static int customer_id = 0;
	/**
	 * <p>Create_customer function create customer object and adds customers list </p>
	 * */
	public static void Create_customer(String[] alinan_splitlenmis_kullanici_satir) {
		double bakiye_customer = Double.parseDouble(alinan_splitlenmis_kullanici_satir[4]);
		customer_id++;
		customers.add(new Customer(alinan_splitlenmis_kullanici_satir[1], alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], customer_id, alinan_splitlenmis_kullanici_satir[5],
				bakiye_customer, "CLASSIC", "", ""));

	}
	/**
	 * <p>Create_tech function create Technician object and adds techs list </p>
	 * */
	public static void Create_tech(String[] alinan_splitlenmis_kullanici_satir) {
		double maas_tech = Double.parseDouble(alinan_splitlenmis_kullanici_satir[4]);
		int tech_senior = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		techs.add(new Technician(alinan_splitlenmis_kullanici_satir[1], alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], maas_tech, tech_senior));
	}
	/**
	 * <p>item_id variable determines items id </p>
	 * */
	public static int item_id = 0;
	/**
	 * <p>Create_book function creates book object and adds books list</p>
	 * */
	public static void Create_book(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double book_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int book_releaseDate = Integer.parseInt(alinan_splitlenmis_kullanici_satir[2]);
		int book_numberofpages = Integer.parseInt(alinan_splitlenmis_kullanici_satir[6]);
		books.add(new Off_Book(item_id, book_price, book_releaseDate, alinan_splitlenmis_kullanici_satir[4],
				alinan_splitlenmis_kullanici_satir[3], alinan_splitlenmis_kullanici_satir[5], book_numberofpages));
	}
	/**
	 * <p>Create_cddvd function creates cddvd object and adds cddvds list</p>
	 * */
	public static void Create_cddvd(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double cddvd_price = Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]);
		int cddvd_release_date = Integer.parseInt(alinan_splitlenmis_kullanici_satir[2]);
		cddvds.add(new Off_Cddvd(item_id, cddvd_price, cddvd_release_date, alinan_splitlenmis_kullanici_satir[3],
				alinan_splitlenmis_kullanici_satir[4], alinan_splitlenmis_kullanici_satir[5]));
	}
	/**
	 * <p>Create_desktop function creates desktop object and adds desktops list</p>
	 * */
	public static void Create_desktop(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double desktop_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int desktop_maxvolt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int desktop_maxwatt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		int desktop_ramcapacity = Integer.parseInt(alinan_splitlenmis_kullanici_satir[8]);
		int desktop_hddcapacity = Integer.parseInt(alinan_splitlenmis_kullanici_satir[9]);
		desktops.add(new Elec_Computer_Desktop(item_id, desktop_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], desktop_maxvolt, desktop_maxwatt,
				alinan_splitlenmis_kullanici_satir[6], alinan_splitlenmis_kullanici_satir[7], desktop_ramcapacity,
				desktop_hddcapacity, alinan_splitlenmis_kullanici_satir[10]));
	}
	/**
	 * <p>Create_laptop function creates laptop object and adds laptops list</p>
	 * */
	public static void Create_laptop(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double laptop_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int laptop_maxvolt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int laptop_maxwatt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		int laptop_ramcapacity = Integer.parseInt(alinan_splitlenmis_kullanici_satir[8]);
		int laptop_hddcapacity = Integer.parseInt(alinan_splitlenmis_kullanici_satir[9]);
		int laptop_hdmisupport = Integer.parseInt(alinan_splitlenmis_kullanici_satir[10]);
		laptops.add(new Elec_Computer_Laptop(item_id, laptop_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], laptop_maxvolt, laptop_maxwatt,
				alinan_splitlenmis_kullanici_satir[6], alinan_splitlenmis_kullanici_satir[7], laptop_ramcapacity,
				laptop_hddcapacity, laptop_hdmisupport));
	}
	/**
	 * <p>Create_tablet function creates tablet object and adds tablets list</p>
	 * */
	public static void Create_tablet(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double tablet_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int tablet_maxvolt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int tablet_maxwatt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		int tablet_ramcapacity = Integer.parseInt(alinan_splitlenmis_kullanici_satir[8]);
		int tablet_hddcapacity = Integer.parseInt(alinan_splitlenmis_kullanici_satir[9]);
		int tablet_dimension = Integer.parseInt(alinan_splitlenmis_kullanici_satir[10]);
		tablets.add(new Elec_Computer_Tablet(item_id, tablet_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], tablet_maxvolt, tablet_maxwatt,
				alinan_splitlenmis_kullanici_satir[6], alinan_splitlenmis_kullanici_satir[7], tablet_ramcapacity,
				tablet_hddcapacity, tablet_dimension));
	}
	/**
	 * <p>Create_tv function creates tv object and adds tvs list</p>
	 * */
	public static void Create_tv(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double tv_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int tv_maxvolt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int tv_maxwatt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		int tv_screen_size = Integer.parseInt(alinan_splitlenmis_kullanici_satir[6]);
		tvs.add(new Elec_Tv(item_id, tv_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], tv_maxvolt, tv_maxwatt, tv_screen_size));

	}
	/**
	 * <p>Create_smarthphone function creates smarthphone object and adds smarthphones list</p>
	 * */
	public static void Create_smarthphone(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double smarthphone_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int smarthphone_maxvolt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int smarthphone_maxwatt = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		smartphones.add(new Elec_Smartphone(item_id, smarthphone_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], smarthphone_maxvolt, smarthphone_maxwatt,
				alinan_splitlenmis_kullanici_satir[6]));
	}
	/**
	 * <p>Create_haircare function creates haircare object and adds haircares list</p>
	 * */
	public static void Create_haircare(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double haircare_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int haircare_organic = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int haircare_weight = Integer.parseInt(alinan_splitlenmis_kullanici_satir[6]);
		int haircare_medicate = Integer.parseInt(alinan_splitlenmis_kullanici_satir[7]);
		haircares.add(new Cos_Haircare(item_id, haircare_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], haircare_organic, alinan_splitlenmis_kullanici_satir[5],
				haircare_weight, haircare_medicate));
	}
	/**
	 * <p>Create_perfume function creates perfume object and adds perfumes list</p>
	 * */
	public static void Create_perfume(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double perfume_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int perfume_organic = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int perfume_weight = Integer.parseInt(alinan_splitlenmis_kullanici_satir[6]);
		int perfume_lasting_duration = Integer.parseInt(alinan_splitlenmis_kullanici_satir[7]);
		perfumes.add(new Cos_Perfume(item_id, perfume_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], perfume_organic, alinan_splitlenmis_kullanici_satir[5],
				perfume_weight, perfume_lasting_duration));
	}
	/**
	 * <p>Create_skincare function creates skincare object and adds skincares list</p>
	 * */
	public static void Create_skincare(String[] alinan_splitlenmis_kullanici_satir) {
		item_id++;
		double skincare_price = Double.parseDouble(alinan_splitlenmis_kullanici_satir[1]);
		int skincare_organic = Integer.parseInt(alinan_splitlenmis_kullanici_satir[4]);
		int skincare_weight = Integer.parseInt(alinan_splitlenmis_kullanici_satir[6]);
		int skincare_babysensitive = Integer.parseInt(alinan_splitlenmis_kullanici_satir[7]);
		skincares.add(new Cos_Skincare(item_id, skincare_price, alinan_splitlenmis_kullanici_satir[2],
				alinan_splitlenmis_kullanici_satir[3], skincare_organic, alinan_splitlenmis_kullanici_satir[5],
				skincare_weight, skincare_babysensitive));
	}
	/**
	 * <p>Create_campaign function creates campaign object and adds campaigns list</p>
	 * */
	public static void Create_campaign(String[] alinan_splitlenmis_kullanici_satir) {
		int campaign_rate = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		campaigns.add(new Campaign(alinan_splitlenmis_kullanici_satir[2], alinan_splitlenmis_kullanici_satir[3],
				alinan_splitlenmis_kullanici_satir[4], campaign_rate));
	}
	/**
	 * <p>ADDCOSTUMER function creates customer and adds customers list </p>
	 * */
	public static void ADDCOSTUMER(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int addcustomer_adminsizlik = 0;
		for (Admin admin_compare_addcutomer : admins) {
			if (admin_compare_addcutomer.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				addcustomer_adminsizlik++;
				int bastan_silme_addcustomer;
				for (bastan_silme_addcustomer = 0; bastan_silme_addcustomer < alinan_splitlenmis_kullanici_satir.length
						- 1; bastan_silme_addcustomer++) {
					alinan_splitlenmis_kullanici_satir[bastan_silme_addcustomer] = alinan_splitlenmis_kullanici_satir[bastan_silme_addcustomer
							+ 1];
				}
				Create_customer(alinan_splitlenmis_kullanici_satir);
				break;
			}
		}
		if (addcustomer_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>SHOWCUSTOMER function shows customer info(name,id,email,date of birth,statu) in customers list with id and admin </p>
	 * @throws ParseException 
	 * */
	public static void SHOWCUSTOMER(String[] alinan_splitlenmis_komutlar_satir) throws ParseException {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_komutlar_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int showcustomer_adminsizlik = 0;
		for (Admin admin_compare_showcustomer : admins) {
			if (admin_compare_showcustomer.name.equals(alinan_splitlenmis_komutlar_satir[1])) {
				showcustomer_adminsizlik++;
				int show_customer_id = Integer.parseInt(alinan_splitlenmis_komutlar_satir[2]);
				for (Customer customerid_compare_showcustomer : customers) {
					if (customerid_compare_showcustomer.customer_id == show_customer_id) {
						String convertbirthday=customerid_compare_showcustomer.birthday;
						SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");

						System.out.print("Customer name: " + customerid_compare_showcustomer.name);
						System.out.print("	ID: " + customerid_compare_showcustomer.customer_id);
						System.out.print("	E-mail: " + customerid_compare_showcustomer.e_mail);
						System.out.print("	Date of Birth:" + sdf.parse(convertbirthday));
						System.out.println("	Status: " + customerid_compare_showcustomer.Status);
						System.out.println();

					}
				}
			}
		}
		if (showcustomer_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_komutlar_satir[1] + " exists!");

		}
	}
	/**
	 * <p>SHOWCUSTOMER function shows  all customers info(name,id,email,date of birth,statu) in customers list with admin </p>
	 * @throws ParseException 
	 * */
	public static void SHOWCUSTOMERS(String[] alinan_splitlenmis_kullanici_satir) throws ParseException{
	
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int showcustomers_adminsizlik = 0;
		for (Admin admin_compare_showcustomers : admins) {
			if (admin_compare_showcustomers.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				showcustomers_adminsizlik += 1;

				for (Customer liste_showcustomers : customers) {
					String convertbirthday=liste_showcustomers.birthday;
					SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");

					System.out.print("Customer name: " + liste_showcustomers.name);
					System.out.print("	ID: " + liste_showcustomers.customer_id);
					System.out.print("	E-mail: " + liste_showcustomers.e_mail);
					System.out.print("	Date of Birth: " + sdf.parse(convertbirthday));
					System.out.println("	Status: " + liste_showcustomers.Status);
				}
				System.out.println();
			}
		}
		if (showcustomers_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>item_varlik variable determines the number of items received</p>
	 * 
	 * */
	public static int item_varlik = 0;
	/**
	 * <p>ADDTOCART function uses customer shopping cart variable by customer id</p>
	 * <p>Customers should first add some items to their carts before placing a purchase order</p>
	 * */
	public static void ADDTOCART(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		
		int custo_id_tutmama = 0;
		int custo_id = Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]);
		int item_id = Integer.parseInt(alinan_splitlenmis_kullanici_satir[2]);

		for (Customer custo_compare_id : customers) {
			if (custo_compare_id.customer_id == custo_id) {
				custo_id_tutmama++;
				int durdurma_haircare = 0;
				for (Cos_Haircare custo_addtocart : haircares) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_haircare++;
								break;

							}
						}
						if (durdurma_haircare == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Haircare has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_skincare = 0;
				for (Cos_Skincare custo_addtocart : skincares) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_skincare++;
								break;
							}
						}
						if (durdurma_skincare == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Skincare has been successfully added to your cart.");
						}
						System.out.println();
					}

				}
				int durdurma_perfume = 0;
				for (Cos_Perfume custo_addtocart : perfumes) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_perfume++;
								break;
							}
						}
						if (durdurma_perfume == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart); 
							System.out.println("The item Perfume has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_desktop = 0;
				for (Elec_Computer_Desktop custo_addtocart : desktops) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_desktop++;
								break;
							}
						}
						if (durdurma_desktop == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Desktop has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_laptop = 0;
				for (Elec_Computer_Laptop custo_addtocart : laptops) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_laptop++;
								break;

							}
						}
						if (durdurma_laptop == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Laptop has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_tablet = 0;
				for (Elec_Computer_Tablet custo_addtocart : tablets) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_tablet++;
								break;

							}
						}
						if (durdurma_tablet == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Tablet has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_smartphone = 0;
				for (Elec_Smartphone custo_addtocart : smartphones) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_smartphone++;
								break;

							}
						}
						if (durdurma_smartphone == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Smartphone has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_tvs = 0;
				for (Elec_Tv custo_addtocart : tvs) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_tvs++;
								break;

							}
						}
						if (durdurma_tvs == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item TV has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_books = 0;
				for (Off_Book custo_addtocart : books) {
					if (custo_addtocart.item_id == item_id) {

						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_books++;
								break;

							}
						}
						if (durdurma_books == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);
							System.out.println("The item Book has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
				int durdurma_cddvds = 0;
				for (Off_Cddvd custo_addtocart : cddvds) {
					if (custo_addtocart.item_id == item_id) {
						item_varlik++;
						for (Object re_compare : custo_compare_id.shopping_cart) {
							if (re_compare == custo_addtocart) {
								System.out.println("We are sorry. The item is temporarily unavailable.");
								System.out.println();
								durdurma_cddvds++;
								break;

							}
						}
						if (durdurma_cddvds == 0) {
							custo_compare_id.shopping_cart.add(custo_addtocart);

							System.out.println("The item Cddvd has been successfully added to your cart.");
						}
						System.out.println();
					}
				}
			}
		}
		if (item_varlik == 0 && custo_id_tutmama != 0) {
			System.out.println("Invalid item ID");
		}
		if (custo_id_tutmama == 0) {
			System.out.println("No customer with ID number " + custo_id + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>ORDER function provides complete shopping and creates people order history with customerID and customer password</p>
	 * @throws ParseException
	 * */
	public static void ORDER(String[] alinan_splitlenmis_kullanici_satir) throws ParseException {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int custo_id_yok = 0;
		int invalid_password = 0;
		int toplam_maliyet = 0;
		int item_sayisi = 0;
		int custo_order_id = Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]);
		for (Customer custo_compare_order : customers) {
			if(custo_compare_order.customer_id==custo_order_id){
				custo_id_yok++;
				for (Customer password_compare_order : customers) {
					if(password_compare_order.sifre.equals(alinan_splitlenmis_kullanici_satir[2])){
						invalid_password++;
						if (custo_compare_order.shopping_cart.size() == 0) {
							System.out.println("You should add some items to your cart before order request!");
							System.out.println();
							break;
						}
						if (custo_compare_order.shopping_cart.size() >= 0) {
							for (Object objeler : custo_compare_order.shopping_cart) {
								int karsilastirma = ((Item) objeler).item_id;
								for (Cos_Haircare id_bulma1 : haircares) {
									if (karsilastirma == id_bulma1.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("HAIRCARE")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Cos_Perfume id_bulma2 : perfumes) {

									if (karsilastirma == id_bulma2.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("PERFUME")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Cos_Skincare id_bulma3 : skincares) {

									if (karsilastirma == id_bulma3.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("SKINCARE")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Elec_Computer_Desktop id_bulma4 : desktops) {

									if (karsilastirma == id_bulma4.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("DESKTOP")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Elec_Computer_Laptop id_bulma5 : laptops) {

									if (karsilastirma == id_bulma5.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("LAPTOP")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Elec_Computer_Tablet id_bulma6 : tablets) {

									if (karsilastirma == id_bulma6.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("TABLET")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Elec_Smartphone id_bulma7 : smartphones) {

									if (karsilastirma == id_bulma7.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("SMARTPHONE")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Elec_Tv id_bulma8 : tvs) {

									if (karsilastirma == id_bulma8.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("TV")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Off_Book id_bulma9 : books) {

									if (karsilastirma == id_bulma9.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("BOOK")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								for (Off_Cddvd id_bulma10 : cddvds) {

									if (karsilastirma == id_bulma10.item_id) {
										for (Campaign kampanya1 : campaigns) {
											if (kampanya1.itemtype.equals("CDDVD")) {
												((Item) objeler).parasal_deger = ((((Item) objeler).parasal_deger)% 100) * (100 - kampanya1.rate);
												toplam_maliyet += ((Item) objeler).parasal_deger;
												item_sayisi++;
											}
										}
									}
								}
								
							}
							if (toplam_maliyet > custo_compare_order.bakiye) {
								System.out.println("Order could not be placed. Insufficient funds.");
								System.out.println();
								break;
							}
							if (toplam_maliyet < custo_compare_order.bakiye) {
								if (custo_compare_order.Status.equals("CLASSIC")) {
									custo_compare_order.bakiye = custo_compare_order.bakiye - toplam_maliyet;
									System.out.println("Done! Your order will be delivered as soon as possible. Thank you!");
									custo_compare_order.shopping_cart.clear();
									if (toplam_maliyet < 1000) {
										System.out.println("You need to spend " + (1000 - toplam_maliyet)+ " more TL to become a SILVER MEMBER.");
										System.out.println();
										String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
										SimpleDateFormat sdf =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
										custo_compare_order.order_history.add(new Order(sdf.parse(timeStamp), toplam_maliyet, item_sayisi, custo_compare_order.customer_id));
									}

									if (toplam_maliyet >= 1000 && 5000 > toplam_maliyet) {

										custo_compare_order.Status = "SILVER";
										System.out.println("Congratulations! You have been upgraded to a SILVER MEMBER! You have earned a discount of 10% on all purchases.");
										System.out.println("You need to spend" + (5000 - toplam_maliyet)+ "more TL to become a GOLDEN MEMBER.");
										System.out.println();
										String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
										SimpleDateFormat sdf =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
										System.out.println(custo_compare_order.order_history+ " errr");	
										custo_compare_order.order_history.add(new Order(sdf.parse(timeStamp), toplam_maliyet, item_sayisi, custo_compare_order.customer_id));
										System.out.println(custo_compare_order.order_history);	

									}
									if (toplam_maliyet >= 5000) {
										custo_compare_order.Status = "GOLDEN";
										System.out.println("Congratulations! You have been upgraded to a GOLDEN MEMBER! You have earned a discount of 15% on all purchases.");
										System.out.println();
										String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
										SimpleDateFormat sdf =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
										custo_compare_order.order_history.add(new Order(sdf.parse(timeStamp), toplam_maliyet, item_sayisi, custo_compare_order.customer_id));
									}			

								}
								if (custo_compare_order.Status.equals("SILVER")) {
									custo_compare_order.bakiye = custo_compare_order.bakiye - toplam_maliyet;
									System.out.println(	"Done! Your order will be delivered as soon as possible. Thank you!");
									custo_compare_order.shopping_cart.clear();
									if (toplam_maliyet < 1000) {

										System.out.println("You need to spend " + (1000 - toplam_maliyet)+ " more TL to become a SILVER MEMBER.");



									}
									if (toplam_maliyet >= 1000 && 5000 > toplam_maliyet) {

										custo_compare_order.Status = "SILVER";
										System.out.println("Congratulations! You have been upgraded to a SILVER MEMBER! You have earned a discount of 10% on all purchases.");
										System.out.println("You need to spend" + (5000 - toplam_maliyet)+ "more TL to become a GOLDEN MEMBER.");
										System.out.println("Congratulations! You have been upgraded to a GOLDEN MEMBER! You have earned a discount of 15% on all purchases.");
										String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
										SimpleDateFormat sdf =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
										custo_compare_order.order_history.add(new Order(sdf.parse(timeStamp), toplam_maliyet, item_sayisi, custo_compare_order.customer_id));
									


									}
									if (toplam_maliyet >= 5000) {
										custo_compare_order.Status = "GOLDEN";
										System.out.println("Congratulations! You have been upgraded to a GOLDEN MEMBER! You have earned a discount of 15% on all purchases.");
										String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
										SimpleDateFormat sdf =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
										custo_compare_order.order_history.add(new Order(sdf.parse(timeStamp), toplam_maliyet, item_sayisi, custo_compare_order.customer_id));
									

									}
								}
								if (custo_compare_order.Status.equals("GOLDEN")) {
									toplam_maliyet = (toplam_maliyet * 85) % 100;
									custo_compare_order.bakiye = custo_compare_order.bakiye - toplam_maliyet;
									System.out.println("Done! Your order will be delivered as soon as possible. Thank you!");
									custo_compare_order.shopping_cart.clear();
									String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
									SimpleDateFormat sdf =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
									custo_compare_order.order_history.add(new Order(sdf.parse(timeStamp), toplam_maliyet, item_sayisi, custo_compare_order.customer_id));
								}
								
							}
						}
						
					}

				}
				if (invalid_password == 0) {
					System.out.println("Order could not be placed. Invalid password.");
					System.out.println();
					break;
				}
				
			}
		}
		if(custo_id_yok==0){
			System.out.println("No customer with ID number " + custo_order_id + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>SHOWADMININFO function sees admin informations by admin compare </p>
	 * <p>Admin staff can also display their information</p>
	 * */
	public static void SHOWADMININFO(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int admin_compare_showadmininfo_adminsizlik = 0;
		for (Admin admin_compare_showadmininfo : admins) {
			if (admin_compare_showadmininfo.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				admin_compare_showadmininfo_adminsizlik += 1;
				System.out.println("----------- Admin info -----------");
				System.out.println("Admin name: " + admin_compare_showadmininfo.name);
				System.out.println("Admin e-mail: " + admin_compare_showadmininfo.e_mail);
				System.out.println("Admin date of birth: " + admin_compare_showadmininfo.birthday);
				System.out.println();
			}
		}
		if (admin_compare_showadmininfo_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>CREATECAMPAIGN function  creates campaign and adds campaign list </p>
	 * */
	public static void CREATECAMPAIGN(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int wrong_rate = Integer.parseInt(alinan_splitlenmis_kullanici_satir[5]);
		int createcampaign_adminsizlik = 0;
		for (Admin admin_compare_createcampain : admins) {
			if (admin_compare_createcampain.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				createcampaign_adminsizlik++;
				if (wrong_rate > 50) {
					System.out.println("Campaign was not created. Discount rate exceeds maximum rate of 50%.");
					break;
				}
				Create_campaign(alinan_splitlenmis_kullanici_satir);
			}
		}
		if (createcampaign_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
		}
		System.out.println();
	}
	/**
	 * <p>SHOWCAMPAIGNS function  show  created campaign in campaign list </p>
	 * */
	public static void SHOWCAMPAIGNS(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int customer_idsizlik_showcampaign = 0;
		int id_showcampaign = Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]);
		if (campaigns.size() == 0) {
			System.out.println("No campaign has been created so far!");
		}
		if (campaigns.size() > 0) {
			for (Customer custo_id : customers) {
				if (custo_id.customer_id == id_showcampaign) {
					customer_idsizlik_showcampaign++;
					System.out.println("Active campaigns:");
					for (Campaign kampanyalar : campaigns) {
						System.out.println(kampanyalar.rate + "% sale of " + kampanyalar.itemtype + " until "
								+ kampanyalar.enddate);
					}
				}
			}
			if (customer_idsizlik_showcampaign == 0) {
				System.out.println("No customer with ID number " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
				System.out.println();
			}
		}
		System.out.println();
	}
	/**
	 * <p>DEPOSITMONEY function  increases customer money with customer id  </p>
	 * */
	public static void DEPOSITMONEY(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int customer_idsizlik_depositmoney = 0;
		int compare_id = Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]);
		double loadamount = Double.parseDouble(alinan_splitlenmis_kullanici_satir[2]);
		compare_id--;
		for (Customer custo_id : customers) {
			if (custo_id.customer_id == compare_id) {
				customer_idsizlik_depositmoney++;
				customers.get(compare_id).bakiye += loadamount;
			}
		}
		if (customer_idsizlik_depositmoney == 0) {
			System.out.println("No customer with ID number " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * <p>CHPASS function  change password with customer id and old password  </p>
	 * */
	public static void CHPASS(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int customer_idsizlik_chpass = 0;
		int chpass_id = (Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]));
		for (Customer custo_id : customers) {
			if (custo_id.customer_id == chpass_id) {
				customer_idsizlik_chpass++;
				if (custo_id.sifre.equals(alinan_splitlenmis_kullanici_satir[2])) {
					custo_id.sifre = alinan_splitlenmis_kullanici_satir[3];
					System.out.println("The password has been successfully changed.");
					System.out.println();
				} else {
					System.out.println("The given password does not match the current password. Please try again.");
					System.out.println();
				}

			}
			if (customer_idsizlik_chpass == 0) {
				System.out.println("No customer with ID number " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
				System.out.println();
			}
		}
	}
	/**
	 * <p>EMPTYCART function clears customer shopping cart with customer id  </p>
	 * */
	public static void EMPTYCART(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int custo_id_emptycart = Integer.parseInt(alinan_splitlenmis_kullanici_satir[1]);
		int custosuzluk_emptycart = 0;
		for (Customer custo_compare_emptycart : customers) {
			if (custo_compare_emptycart.customer_id == custo_id_emptycart) {
				custosuzluk_emptycart++;
				custo_compare_emptycart.shopping_cart.clear();
				System.out.println("The cart has been emptied.");
				System.out.println();

			}
		}
		if (custosuzluk_emptycart == 0) {
			System.out.println("No customer with ID number " + custo_id_emptycart + " exists!");
			System.out.println();

		}

	}
	/**
	 * <p>SHOWORDERS functionShows completed shopping with technician</p>
	 * 
	 * */
	public static void SHOWORDERS(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int techsizlik = 0;
		for (Technician tech_showorders : techs) {
			if (tech_showorders.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				techsizlik++;
				if (tech_showorders.senior == 1) {
					for (Customer custo_showorder : customers) {

						if (custo_showorder.order_history.size() != 0) {
							for(Object obje:custo_showorder.order_history){
								System.out.print("Order date: "+((Order)obje).orderDate+"\t");
								System.out.print("Customer ID: "+((Order)obje).customerid+"\t");
								System.out.print("Total Cost:"+((Order)obje).totalCost+"\t");
								System.out.println("Number of purchased items: "+((Order)obje).purchased_items+"\t");
								System.out.println();
							}

						}
					}

				}
				if (tech_showorders.senior == 0) {
					System.out.println(alinan_splitlenmis_kullanici_satir[1] + " is not authorized to display orders!");
				}

			}
			if (techsizlik == 0) {
				System.out.println("No technician person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
				System.out.println();
			}

		}

	}
	/**
	 * <p>SHOWITEMSLOWONSTOCK function shows stock status of every item type with admin or technician </p>
	 * */
	public static void SHOWITEMSLOWONSTOCK(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int showitemslowonstock_adminsizlik = 0;
		int sinirlama = Integer.parseInt(alinan_splitlenmis_kullanici_satir[2]);
		if (alinan_splitlenmis_kullanici_satir.length == 3) {
			for (Admin admin_showitemlowonstock_compare : admins) {
				if (admin_showitemlowonstock_compare.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
					showitemslowonstock_adminsizlik++;
					if (books.size() <= sinirlama) {
						System.out.println("Book : " + books.size());
					}
					if (cddvds.size() <= sinirlama) {
						System.out.println("CDDVD : " + cddvds.size());
					}
					if (desktops.size() <= sinirlama) {
						System.out.println("Desktop : " + desktops.size());
					}
					if (laptops.size() <= sinirlama) {
						System.out.println("Laptop : " + laptops.size());
					}
					if (tablets.size() <= sinirlama) {
						System.out.println("Tablet : " + tablets.size());
					}
					if (tvs.size() <= sinirlama) {
						System.out.println("TV : " + tvs.size());
					}
					if (smartphones.size() <= sinirlama) {
						System.out.println("SmartPhone : " + smartphones.size());
					}
					if (haircares.size() <= sinirlama) {
						System.out.println("HairCare : " + haircares.size());
					}
					if (perfumes.size() <= sinirlama) {
						System.out.println("Perfume : " + perfumes.size());
					}
					if (skincares.size() <= sinirlama) {
						System.out.println("SkinCare : " + skincares.size());
					}
					System.out.println();
				}
			}
			for (Technician tech_showitemlowonstock_compare : techs) {
				if (tech_showitemlowonstock_compare.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
					showitemslowonstock_adminsizlik++;
					if (books.size() <= sinirlama) {
						System.out.println("Book : " + books.size());
					}
					if (cddvds.size() <= sinirlama) {
						System.out.println("CDDVD : " + cddvds.size());
					}
					if (desktops.size() <= sinirlama) {
						System.out.println("Desktop : " + desktops.size());
					}
					if (laptops.size() <= sinirlama) {
						System.out.println("Laptop : " + laptops.size());
					}
					if (tablets.size() <= sinirlama) {
						System.out.println("Tablet : " + tablets.size());
					}
					if (tvs.size() <= sinirlama) {
						System.out.println("TV : " + tvs.size());
					}
					if (smartphones.size() <= sinirlama) {
						System.out.println("SmartPhone : " + smartphones.size());
					}
					if (haircares.size() <= sinirlama) {
						System.out.println("HairCare : " + haircares.size());
					}
					if (perfumes.size() <= sinirlama) {
						System.out.println("Perfume : " + perfumes.size());
					}
					if (skincares.size() <= sinirlama) {
						System.out.println("SkinCare : " + skincares.size());
					}
					System.out.println();
				}
			}
		}
		if (alinan_splitlenmis_kullanici_satir.length == 2) {
			for (Admin admin_showitemlowonstock_compare : admins) {
				if (admin_showitemlowonstock_compare.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
					showitemslowonstock_adminsizlik++;
					System.out.println("Book : " + books.size());
					System.out.println("CDDVD : " + cddvds.size());
					System.out.println("Desktop : " + desktops.size());
					System.out.println("Laptop : " + laptops.size());
					System.out.println("Tablet : " + tablets.size());
					System.out.println("TV : " + tvs.size());
					System.out.println("SmartPhone : " + smartphones.size());
					System.out.println("HairCare : " + haircares.size());
					System.out.println("Perfume : " + perfumes.size());
					System.out.println("SkinCare : " + skincares.size());
					System.out.println();
				}
			}
			for (Technician tech_showitemlowonstock_compare : techs) {
				if (tech_showitemlowonstock_compare.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
					showitemslowonstock_adminsizlik++;
					System.out.println("Book : " + books.size());
					System.out.println("CDDVD : " + cddvds.size());
					System.out.println("Desktop : " + desktops.size());
					System.out.println("Laptop : " + laptops.size());
					System.out.println("Tablet : " + tablets.size());
					System.out.println("TV : " + tvs.size());
					System.out.println("SmartPhone : " + smartphones.size());
					System.out.println("HairCare : " + haircares.size());
					System.out.println("Perfume : " + perfumes.size());
					System.out.println("SkinCare : " + skincares.size());
					System.out.println();
				}
			}
		}
		if (showitemslowonstock_adminsizlik == 0) {
			System.out.println(
					"No admin or technician person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>SHOWVIP function shows customer who has a Golden status </p>
	 * */
	public static void SHOWVIP(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int showvip_adminsizlik = 0;
		for (Admin admin_compare_showvip : admins) {
			if (admin_compare_showvip.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				showvip_adminsizlik++;
				for (Customer customer_compare_vip : customers) {
					if (customer_compare_vip.Status.equals("GOLDEN")) {
						System.out.print("Customer name: " + customer_compare_vip.name);
						System.out.print("	ID: ");
						System.out.print("	E-mail: " + customer_compare_vip.e_mail);
						System.out.print("	Date of Birth: " + customer_compare_vip.birthday);
						System.out.print("	Status: " + customer_compare_vip.Status);
						System.out.println();
					}
				}
			}
		}
		for (Technician tech_compare_showvip : techs) {
			if (tech_compare_showvip.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				showvip_adminsizlik++;
				for (Customer customer_compare_vip : customers) {
					if (customer_compare_vip.Status.equals("GOLDEN")) {
						System.out.print("Customer name: " + customer_compare_vip.name);
						System.out.print("	ID: ");
						System.out.print("	E-mail: " + customer_compare_vip.e_mail);
						System.out.print("	Date of Birth: " + customer_compare_vip.birthday);
						System.out.print("	Status: " + customer_compare_vip.Status);
						System.out.println();
					}
				}
			}
		}
		if (showvip_adminsizlik == 0) {
			System.out.println(
					"No admin or technician person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
		}
	}
	/**
	 * <p>ADDADMIN function adds new admin in admins list with admin </p>
	 * */
	public static void ADDADMIN(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int addadmin_adminsizlik = 0;
		for (Admin admin_compare_addadmin : admins) {
			if (admin_compare_addadmin.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				addadmin_adminsizlik++;
				int bastan_silme_addadmin;
				for (bastan_silme_addadmin = 0; bastan_silme_addadmin < alinan_splitlenmis_kullanici_satir.length
						- 1; bastan_silme_addadmin++) {
					alinan_splitlenmis_kullanici_satir[bastan_silme_addadmin] = alinan_splitlenmis_kullanici_satir[bastan_silme_addadmin
							+ 1];
				}
				Create_admin(alinan_splitlenmis_kullanici_satir);
				break;
			}
		}
		if (addadmin_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>ADDTECH function adds new technician in techs list with admin </p>
	 * */
	public static void ADDTECH(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int addtech_adminsizlik = 0;
		for (Admin admin_compare_addadmin : admins) {
			if (admin_compare_addadmin.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				addtech_adminsizlik++;
				int bastan_silme_addtech;
				for (bastan_silme_addtech = 0; bastan_silme_addtech < alinan_splitlenmis_kullanici_satir.length
						- 1; bastan_silme_addtech++) {
					alinan_splitlenmis_kullanici_satir[bastan_silme_addtech] = alinan_splitlenmis_kullanici_satir[bastan_silme_addtech
							+ 1];
				}
				Create_tech(alinan_splitlenmis_kullanici_satir);
				break;
			}
		}
		if (addtech_adminsizlik == 0) {
			System.out.println("No admin person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
			System.out.println();
		}
	}
	/**
	 * <p>ADDITEM function adds new item and list for items name with tech </p>
	 * */
	public static void ADDITEM(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int additem_techsizlik = 0;
		int item_eklenememe = 0;
		String[] additem_split = alinan_splitlenmis_kullanici_satir[2].split(":");
		for (Technician tech_compare_additem : techs) {
			if (tech_compare_additem.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				additem_techsizlik++;
				if (additem_split[0].equals("BOOK")) {
					item_eklenememe++;
					Create_book(additem_split);
				}
				if (additem_split[0].equals("CDDVD")) {
					item_eklenememe++;
					Create_cddvd(additem_split);
				}
				if (additem_split[0].equals("DESKTOP")) {
					item_eklenememe++;
					Create_desktop(additem_split);
				}
				if (additem_split[0].equals("LAPTOP")) {
					item_eklenememe++;
					Create_laptop(additem_split);
				}
				if (additem_split[0].equals("TABLET")) {
					item_eklenememe++;
					Create_tablet(additem_split);
				}
				if (additem_split[0].equals("TV")) {
					item_eklenememe++;
					Create_tv(additem_split);
				}
				if (additem_split[0].equals("SMARTPHONE")) {
					item_eklenememe++;
					Create_smarthphone(additem_split);
				}
				if (additem_split[0].equals("HAIRCARE")) {
					item_eklenememe++;
					Create_haircare(additem_split);
				}
				if (additem_split[0].equals("PERFUME")) {
					item_eklenememe++;
					Create_perfume(additem_split);
				}
				if (additem_split[0].equals("SKINCARE")) {
					item_eklenememe++;
					Create_skincare(additem_split);
				}
			}
		}
		if (additem_techsizlik == 0) {
			System.out.println("No technician person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
		}
		if (item_eklenememe == 0) {
			System.out.println("No item type " + additem_split[0] + " found");
		}
	}
	/**
	 * <p>LISTITEM_book has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_book() {
		for (Off_Book for_book : books) {
			System.out.println("Type: Book");
			System.out.println("Item ID: " + for_book.item_id);
			System.out.println("Price: " + for_book.parasal_deger + " $");
			System.out.println("Release Date: " + for_book.release_date);
			System.out.println("Title: " + for_book.cover_title);
			System.out.println("Publisher: " + for_book.publisher);
			System.out.println("Author: " + for_book.author);
			System.out.println("Page: " + for_book.number_of_pages + " pages");
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_cddvd has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_cddvd() {
		for (Off_Cddvd for_cddvd : cddvds) {
			System.out.println("Type: CDDVD");
			System.out.println("Item ID: " + for_cddvd.item_id);
			System.out.println("Price: " + for_cddvd.parasal_deger);
			System.out.println("Release Date: " + for_cddvd.release_date);
			System.out.println("Title: " + for_cddvd.cover_title);
			System.out.println("Songs: " + for_cddvd.songs);
			System.out.println("Composer: " + for_cddvd.composer);
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_desktop has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_desktop() {
		for (Elec_Computer_Desktop for_desktop : desktops) {
			System.out.println("Type: Desktop");
			System.out.println("Item ID: " + for_desktop.item_id);
			System.out.println("Price: " + for_desktop.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_desktop.manufacturer);
			System.out.println("Brand: " + for_desktop.brand);
			System.out.println("Max Volt: " + for_desktop.maxvolt + " V.");
			System.out.println("Max Watt: " + for_desktop.maxwatt + " W.");
			System.out.println("Operating System: " + for_desktop.OS);
			System.out.println("CPU Type: " + for_desktop.cpu_name);
			System.out.println("RAM Capacity: " + for_desktop.ramcapacity + " GB.");
			System.out.println("HDD Capacity: " + for_desktop.hddcapacity + " GB.");
			System.out.println("Box Color: " + for_desktop.kutu_rengi);
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_laptop has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_laptop() {
		for (Elec_Computer_Laptop for_laptop : laptops) {
			System.out.println("Type: Laptop");
			System.out.println("Item ID: " + for_laptop.item_id);
			System.out.println("Price: " + for_laptop.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_laptop.manufacturer);
			System.out.println("Brand: " + for_laptop.brand);
			System.out.println("Max Volt: " + for_laptop.maxvolt + " V.");
			System.out.println("Max Watt: " + for_laptop.maxwatt + " W.");
			System.out.println("Operating System: " + for_laptop.OS);
			System.out.println("CPU Type: " + for_laptop.cpu_name);
			System.out.println("RAM Capacity: " + for_laptop.ramcapacity + " GB.");
			System.out.println("HDD Capacity: " + for_laptop.hddcapacity + " GB.");
			if (for_laptop.hdmi_support == 1) {
				System.out.println("HDMI support: Yes");
			}
			if (for_laptop.hdmi_support == 0) {
				System.out.println("HDMI support: No");
			}
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_tablet has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_tablet() {
		for (Elec_Computer_Tablet for_tablet : tablets) {
			System.out.println("Type: Tablet");
			System.out.println("Item ID: " + for_tablet.item_id);
			System.out.println("Price: " + for_tablet.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_tablet.manufacturer);
			System.out.println("Brand: " + for_tablet.brand);
			System.out.println("Max Volt: " + for_tablet.maxvolt + " V.");
			System.out.println("Max Watt: " + for_tablet.maxwatt + " W.");
			System.out.println("Operating System: " + for_tablet.OS);
			System.out.println("CPU Type: " + for_tablet.cpu_name);
			System.out.println("RAM Capacity: " + for_tablet.ramcapacity + " GB.");
			System.out.println("HDD Capacity: " + for_tablet.hddcapacity + " GB.");
			System.out.println("Dimension: " + for_tablet.dimension);
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_tv has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_tv() {
		for (Elec_Tv for_tv : tvs) {
			System.out.println("Type: TV");
			System.out.println("Item ID: " + for_tv.item_id);
			System.out.println("Price: " + for_tv.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_tv.manufacturer);
			System.out.println("Brand: " + for_tv.brand);
			System.out.println("Max Volt: " + for_tv.maxvolt + " V.");
			System.out.println("Max Watt: " + for_tv.maxwatt + " W.");
			System.out.println("Screen size: " + for_tv.screen_size + "\"");
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_smartphone has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_smartphone() {
		for (Elec_Smartphone for_smartphone : smartphones) {
			System.out.println("Type: SmartPhone");
			System.out.println("Item ID: " + for_smartphone.item_id);
			System.out.println("Price: " + for_smartphone.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_smartphone.manufacturer);
			System.out.println("Brand: " + for_smartphone.brand);
			System.out.println("Max Volt: " + for_smartphone.maxvolt + " V.");
			System.out.println("Max Watt: " + for_smartphone.maxwatt + " W.");
			System.out.println("Screen Type: " + for_smartphone.screen_type);
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_haircare has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_haircare() {
		for (Cos_Haircare for_haircare : haircares) {
			System.out.println("Type: HairCare");
			System.out.println("Item ID: " + for_haircare.item_id);
			System.out.println("Price: " + for_haircare.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_haircare.manufacturer);
			System.out.println("Brand: " + for_haircare.brand);
			if (for_haircare.organik == 1) {
				System.out.println("HDMI support: Yes");
			}
			if (for_haircare.organik == 0) {
				System.out.println("Organic:  No");
			}
			System.out.println("Expiration Date: " + for_haircare.son_kullanma_tarihi);
			System.out.println("Weight: " + for_haircare.agirlik + " g.");
			if (for_haircare.medicated == 1) {
				System.out.println("Medicated: Yes");
			}
			if (for_haircare.medicated == 0) {
				System.out.println("Medicated: No");
			}
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_perfume has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_perfume() {
		for (Cos_Perfume for_perfume : perfumes) {
			System.out.println("Type: Perfume");
			System.out.println("Item ID: " + for_perfume.item_id);
			System.out.println("Price: " + for_perfume.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_perfume.manufacturer);
			System.out.println("Brand: " + for_perfume.brand);
			if (for_perfume.organik == 1) {
				System.out.println("HDMI support: Yes");
			}
			if (for_perfume.organik == 0) {
				System.out.println("Organic:  No");
			}
			System.out.println("Expiration Date: " + for_perfume.son_kullanma_tarihi);
			System.out.println("Weight: " + for_perfume.agirlik + " g.");
			System.out.println("Lasting Duration: " + for_perfume.lasting_duration + " min.");
			System.out.println("-----------------------");
		}
	}
	/**
	 * <p>LISTITEM_skincare has function for LISTITEM function and DISPITEMSOF function</p>
	 * */
	public static void LISTITEM_skincare() {
		for (Cos_Skincare for_skincare : skincares) {
			System.out.println("Type: SkinCare");
			System.out.println("Item ID: " + for_skincare.item_id);
			System.out.println("Price: " + for_skincare.parasal_deger + " $");
			System.out.println("Manufacturer: " + for_skincare.manufacturer);
			System.out.println("Brand: " + for_skincare.brand);
			if (for_skincare.organik == 1) {
				System.out.println("HDMI support: Yes");
			}
			if (for_skincare.organik == 0) {
				System.out.println("Organic:  No");
			}
			System.out.println("Expiration Date: " + for_skincare.son_kullanma_tarihi);
			System.out.println("Weight: " + for_skincare.agirlik + " g.");
			if (for_skincare.babySensitive == 1) {
				System.out.println("Baby Sensitive: Yes");
			}
			if (for_skincare.babySensitive == 0) {
				System.out.println("Baby Sensitive: No");
			}
			System.out.println("-----------------------");

		}
	}
	/**
	 * <p>LISTITEM function sorts all item with admin or technician </p>
	 * */
	public static void LISTITEM(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int listitem_adminsizlik = 0;
		for (Admin admin_compare_listitem : admins) {
			if (admin_compare_listitem.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				listitem_adminsizlik++;
				LISTITEM_book();
				LISTITEM_cddvd();
				LISTITEM_desktop();
				LISTITEM_laptop();
				LISTITEM_tablet();
				LISTITEM_tv();
				LISTITEM_smartphone();
				LISTITEM_haircare();
				LISTITEM_perfume();
				LISTITEM_skincare();

			}
		}
		for (Technician tech_compare_listitem : techs) {
			if (tech_compare_listitem.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				listitem_adminsizlik++;
				LISTITEM_book();
				LISTITEM_cddvd();
				LISTITEM_desktop();
				LISTITEM_laptop();
				LISTITEM_tablet();
				LISTITEM_tv();
				LISTITEM_smartphone();
				LISTITEM_haircare();
				LISTITEM_perfume();
				LISTITEM_skincare();

			}
		}
		if (listitem_adminsizlik == 0) {
			System.out.println(
					"No admin or technician person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
		}
	}
	/**
	 * <p>DISPITEMSOF function sorts Desired item with technician </p>
	 * */
	public static void DISPITEMSOF(String[] alinan_splitlenmis_kullanici_satir) {
		System.out.print("COMMAND TEXT: <");
		for(String commandline:alinan_splitlenmis_kullanici_satir){
			System.out.print(commandline+"\t");
		}
		System.out.println(">");
		int dispitemsof_techsizlik = 0;
		String[] splitlenmis_dispitemsof = alinan_splitlenmis_kullanici_satir[2].split(":");
		for (Technician tech_compare_dispitemsof : techs) {
			if (tech_compare_dispitemsof.name.equals(alinan_splitlenmis_kullanici_satir[1])) {
				dispitemsof_techsizlik++;
				for (String splitlenmis_dispitemsof_elements : splitlenmis_dispitemsof) {
					System.out.println(splitlenmis_dispitemsof_elements);
					if (splitlenmis_dispitemsof_elements.equals("BOOK")) {
						LISTITEM_book();
					}
					if (splitlenmis_dispitemsof_elements.equals("CDDVD")) {
						LISTITEM_cddvd();
					}
					if (splitlenmis_dispitemsof_elements.equals("DESKTOP")) {
						LISTITEM_desktop();
					}
					if (splitlenmis_dispitemsof_elements.equals("LAPTOP")) {
						LISTITEM_laptop();
					}
					if (splitlenmis_dispitemsof_elements.equals("TABLET")) {
						LISTITEM_tablet();
					}
					if (splitlenmis_dispitemsof_elements.equals("TV")) {
						LISTITEM_tv();
					}
					if (splitlenmis_dispitemsof_elements.equals("SMARTPHONE")) {
						LISTITEM_smartphone();
					}
					if (splitlenmis_dispitemsof_elements.equals("HAIRCARE")) {
						LISTITEM_haircare();
					}
					if (splitlenmis_dispitemsof_elements.equals("PERFUME")) {
						LISTITEM_perfume();
					}
					if (splitlenmis_dispitemsof_elements.equals("SKINCARE")) {
						LISTITEM_skincare();
					}
				}
			}
		}
		if (dispitemsof_techsizlik == 0) {
			System.out.println("No technician person named " + alinan_splitlenmis_kullanici_satir[1] + " exists!");
		}
	}

}