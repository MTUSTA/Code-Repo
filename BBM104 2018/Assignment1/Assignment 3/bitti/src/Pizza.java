
public class Pizza {
	private int value;
	private String type;
	private Material malzeme;

	public Pizza(int value, String tur) {
		setValue(value);
		setType(tur);
		
	}
	/*adds material and hides in pizza malzeme variable*/
	public void addTopping(String[] line2) {
		Material hide = null;
		for (int i = 1; i < line2.length; i++) {
			if (i < 4) {
				if (line2[i].toLowerCase().compareTo("salami") == 0) {
					if (hide != null) {
						Salami bos = new Salami(hide);
						hide = bos;
					} else {
						Salami bos = new Salami();
						hide = bos;
					}
				}
				else if (line2[i].toLowerCase().compareTo("soudjouk") == 0) {
					if (hide != null) {
						Soudjouk bos = new Soudjouk(hide);
						hide = bos;
					} else {
						Soudjouk bos = new Soudjouk();
						hide = bos;
					}
				}
				else if (line2[i].toLowerCase().compareTo("hotpepper") == 0) {
					if (hide != null) {
						Pepper bos = new Pepper(hide);
						hide = bos;
					} else {
						Pepper bos = new Pepper();
						hide = bos;
					}
				}
				else if (line2[i].toLowerCase().compareTo("onion") == 0) {
					if (hide != null) {
						Onion bos = new Onion(hide);
						hide = bos;
					} else {
						Onion bos = new Onion();
						hide = bos;
					}
				}
			} else {
				System.out.println("more material " + line2[i] + " tomato pizza could not be added");
			}
		}
		this.malzeme = hide;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	/*print toppings in pizza*/
	public String printToppings() {
		String donen = null;
		Material yedek_malzeme = malzeme;
		while (malzeme != null) {
			if (malzeme.getClass() == Salami.class) {
				donen = donen + "Salami ";
				malzeme = ((Salami) malzeme).getMalzeme();
			} else if (malzeme.getClass() == Soudjouk.class) {
				donen = donen + "Soudjouk ";
				malzeme = ((Soudjouk) malzeme).getMalzeme();
			} else if (malzeme.getClass() == Pepper.class) {
				donen = donen + "HotPepper ";
				malzeme = ((Pepper) malzeme).malzeme;
			} else if (malzeme.getClass() == Onion.class) {
				donen = donen + "Onion ";
				malzeme = ((Onion) malzeme).getMalzeme();
			}
		}
		if (donen != null) {
			donen = donen.substring(4, donen.length());
			malzeme = yedek_malzeme;
			return donen;
		}

		return "";
	}
	/*calculate pizza value */
	public int cost() {
		int deger = getValue();
		Material yedek_malzeme2 = malzeme;
		while (malzeme != null) {
			if (malzeme.getClass() == Salami.class) {
				deger += ((Salami) malzeme).getValue();
				malzeme = ((Salami) malzeme).getMalzeme();
			} else if (malzeme.getClass() == Soudjouk.class) {
				deger += ((Soudjouk) malzeme).getValue();
				malzeme = ((Soudjouk) malzeme).getMalzeme();
			} else if (malzeme.getClass() == Pepper.class) {
				deger += ((Pepper) malzeme).getValue();
				malzeme = ((Pepper) malzeme).malzeme;
			} else if (malzeme.getClass() == Onion.class) {
				deger += ((Onion) malzeme).getValue();
				malzeme = ((Onion) malzeme).getMalzeme();
			}
		}
		malzeme = yedek_malzeme2;
		return deger;
	}
}
