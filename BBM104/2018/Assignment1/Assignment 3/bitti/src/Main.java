import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		IDataAccessObject DAO = new CustomerDAO();
		IDataAccessObject2 DAO2 = new OrderDAO();
		try {
			try {
				/* read costumer.txt */
				Scanner scanner = new Scanner(new File("customer.txt"));
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();

					/* do not process empty lines */
					if (line.length() != 0) {
						/* create customer object and add arraylist */
						DAO.add(line);
					}

				}
				scanner.close();
			} catch (FileNotFoundException e) {
				System.out.println("No Customer File Found!");
			}

			try {
				/* read order.txt */
				Scanner scanner2 = new Scanner(new File("order.txt"));
				/* deger1 saves order number, deger2 saves customer number */
				int deger1 = 0, deger2 = 0;
				while (scanner2.hasNextLine()) {
					String line = scanner2.nextLine();
					/* do not process empty lines */
					if (line.length() != 0) {
						String[] line2 = line.split(" ");
						if (line2[0].toLowerCase().compareTo("order:") == 0) {
							deger1 = Integer.parseInt(line2[1]);
							deger2 = Integer.parseInt(line2[2]);
							/* create order object and add arraylist */
							DAO2.add(deger1, deger2);
						}
						/* create material object or drink object. Add object in customer order arraylist*/ 
						else {
							if (line2[0].toLowerCase().compareTo("americanpan") == 0) {
								Pizza pizzacik = new Americanpan();
								pizzacik.addTopping(line2);
								((Order) DAO2.getByID(deger1)).getOrders().add(pizzacik);
							} else if (line2[0].toLowerCase().compareTo("neapolitan") == 0) {
								Pizza pizzacik = new Neapolitan();
								pizzacik.addTopping(line2);
								((Order) DAO2.getByID(deger1)).getOrders().add(pizzacik);
							} else if (line2[0].toLowerCase().compareTo("softdrink") == 0) {
								((Order) DAO2.getByID(deger1)).getOrders().add(new softdrink());
							}
						}
					}
				}
				scanner2.close();
			} catch (FileNotFoundException e) {
				System.out.println("No Order File Found!");
			}

			PrintWriter writer3 = new PrintWriter("output.txt");
			Scanner scanner1 = new Scanner(new File(args[0]));
			while (scanner1.hasNextLine()) {
				String line = scanner1.nextLine();
				String[] line2 = line.split(" ");
				String[] line3 = Arrays.copyOf(line2, line2.length);
				/* delete the first element of the arraylist*/
				for (int i = 0; i < line3.length - 1; i++) {
					line3[i] = line3[i + 1];
				}

				/* add customer for input file */
				if (line2[0].compareTo("AddCustomer") == 0) {
					String yapi = null;
					for (int i = 1; i < line2.length; i++) {
						if (i == 5) {
							yapi += ": ";

						}
						yapi += line2[i] + " ";
					}
					yapi = yapi.substring(4, yapi.length());
					DAO.add(yapi);
					writer3.println("Customer " + line2[1] + " " + line2[2] + " added");
				}
				/* remove customer for input file */
				else if (line2[0].compareTo("RemoveCustomer") == 0) {
					int cusid = Integer.parseInt(line2[1]);
					Customer isim = ((Customer) DAO.getByID(cusid));
					if (DAO.deleteByID(cusid) == true) {
						DAO2.update(cusid);
						writer3.println("Customer " + line2[1] + " " + isim.getCustomer_Name() + " removed");
					} else {
						System.out.println("Customer not found");
					}
				}
				/* create order for input file */
				else if (line2[0].compareTo("CreateOrder") == 0) {
					int num1 = Integer.parseInt(line2[1]);
					int num2 = Integer.parseInt(line2[2]);
					DAO2.add(num1, num2);
					writer3.println("Order " + num1 + " created");
				}
				/* add pizza for input file */
				else if (line2[0].compareTo("AddPizza") == 0) {
					int num = Integer.parseInt(line2[1]);
					if (line2[2].toLowerCase().compareTo("americanpan") == 0) {
						Pizza pizzacik = new Americanpan();
						String yapi = null;
						for (int i = 2; i < line2.length; i++) {
							yapi += line2[i] + " ";
						}

						String[] split_yapi = {};
						if (yapi != null) {
							yapi = yapi.substring(4, yapi.length());
							split_yapi = yapi.split(" ");
						}
						pizzacik.addTopping(split_yapi);
						((Order) DAO2.getByID(num)).getOrders().add(pizzacik);
						writer3.println("AmericianPan pizza added to order " + line2[1]);
					} else if (line2[2].toLowerCase().compareTo("neapolitan") == 0) {
						Pizza pizzacik = new Neapolitan();

						String yapi = null;
						for (int i = 2; i < line2.length; i++) {
							yapi += line2[i] + " ";
						}
						String[] split_yapi = {};
						if (yapi != null) {
							yapi = yapi.substring(4, yapi.length());
							split_yapi = yapi.split(" ");
						}

						pizzacik.addTopping(split_yapi);
						((Order) DAO2.getByID(num)).getOrders().add(pizzacik);
						writer3.println("Neapolitan pizza added to order " + line2[1]);
					}
				}
				/* add drink for input file */
				else if (line2[0].toLowerCase().compareTo("adddrink") == 0) {
					int num = Integer.parseInt(line2[1]);
					((Order) DAO2.getByID(num)).getOrders().add(new softdrink());
					writer3.println("Drink added to order " + line2[1]);
				}
				/* paycheck for input file */
				else if (line2[0].toLowerCase().compareTo("paycheck") == 0) {
					int num = Integer.parseInt(line2[1]);
					writer3.println("PayCheck for order " + num);
					int toplam = 0;
					for (Object eleman : ((Order) DAO2.getByID(num)).getOrders()) {

						if (eleman.getClass() == Americanpan.class) {
							int piz_deger = ((Pizza) eleman).cost();
							writer3.println("\t" + ((Pizza) eleman).getType() + " " + ((Pizza) eleman).printToppings()+ piz_deger + "$");
							toplam += piz_deger;
						} else if (eleman.getClass() == Neapolitan.class) {
							int piz_deger = ((Pizza) eleman).cost();
							writer3.println("\t" + ((Pizza) eleman).getType() + " " + ((Pizza) eleman).printToppings()
									+ piz_deger + "$");
							toplam += piz_deger;
						} else if (eleman.getClass() == softdrink.class) {
							writer3.println("\tSoftDrink " + ((softdrink) eleman).getValue() + "$");
							toplam += ((softdrink) eleman).getValue();
						}
					}
					writer3.println("\tTotal: " + toplam + "$");
				}
				/* List Customers */
				else if (line2[0].toLowerCase().compareTo("list") == 0) {
					writer3.println("Customer List:");
					for (Customer customer : DAO.getALL()) {
						writer3.println(customer.getCustomer_id() + " " + customer.getCustomer_Name() + " "
								+ customer.getCustomer_Surname() + " " + customer.getCustomer_Phone_Number()
								+ " Address:" + customer.getCustomer_Address());
					}
				}
			}
			/* save customer information in customer.txt */
			PrintWriter writer = new PrintWriter("customer.txt");
			for (Customer customer : DAO.getALL2()) {
				writer.println(customer.getCustomer_id() + " " + customer.getCustomer_Name() + " "
						+ customer.getCustomer_Surname() + " " + customer.getCustomer_Phone_Number() + " Address:"
						+ customer.getCustomer_Address());
			}
			writer.close();
			/* save order information in order.txt */
			PrintWriter writer2 = new PrintWriter("order.txt");
			for (Order order : DAO2.getALL()) {
				writer2.println("Order: " + order.getSira() + " " + order.getCusid());
				for (Object eleman : order.getOrders()) {
					if (eleman.getClass() == Americanpan.class) {
						writer2.println(((Pizza) eleman).getType() + " " + ((Pizza) eleman).printToppings());
					} else if (eleman.getClass() == Neapolitan.class) {
						writer2.println(((Pizza) eleman).getType() + " " + ((Pizza) eleman).printToppings());
					} else if (eleman.getClass() == softdrink.class) {
						writer2.println("Softdrink");
					}
				}
			}
			writer3.close();
			writer2.close();
			
			scanner1.close();
			
		} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
		}

	}
}