import java.io.IOException;

public class Examination {
	private String type;
	private int cost;
	private Operations objt = null;

	public Examination(String type) {
		this.type = type;
		if (type.compareTo("Outpatient") == 0) {
			this.cost = 15;
		} else if (type.compareTo("Inpatient") == 0) {
			this.cost = 10;
		}
	}

	public void addoperation(Object line) {
		String[] splitted = (String[]) line;
		Operations hide = null;
		for (int i = 0; i < splitted.length; i++) {
			if (i < 3) {
				if (splitted[i].toLowerCase().compareTo("imaging") == 0) {
					if (hide != null) {
						imaging bos = new imaging(hide);
						hide = bos;
					} else {
						imaging bos = new imaging();
						hide = bos;
					}
				} else if (splitted[i].toLowerCase().compareTo("tests") == 0) {
					if (hide != null) {
						tests bos = new tests(hide);
						hide = bos;
					} else {
						tests bos = new tests();
						hide = bos;
					}
				} else if (splitted[i].toLowerCase().compareTo("measurements") == 0) {
					if (hide != null) {
						measurements bos = new measurements(hide);
						hide = bos;
					} else {
						measurements bos = new measurements();
						hide = bos;
					}
				} else if (splitted[i].toLowerCase().compareTo("doctorvisit") == 0) {
					if (hide != null) {
						doctorvisit bos = new doctorvisit(hide);
						hide = bos;
					} else {
						doctorvisit bos = new doctorvisit();
						hide = bos;
					}
				}
			}
			// this condition is optinal  --> only 3 --> no add more 3 --> only printing not added
			else {
				System.out.println("more examination " + splitted[i] + " could not be added");
			}
		}
		this.objt = hide;
	}
	// string concat for operations
	public void printoperations() throws IOException {
		String prt = "";
		Operations copy = this.objt;
		while (copy != null) {
			if (copy.getClass() == imaging.class) {
				prt = "imaging " + prt;
				copy = ((imaging) copy).getObjt();
			} else if (copy.getClass() == tests.class) {
				prt = "tests " + prt;
				copy = ((tests) copy).getObjt();
			} else if (copy.getClass() == doctorvisit.class) {
				prt = "doctorvisit " + prt;
				copy = ((doctorvisit) copy).getObjt();
			} else if (copy.getClass() == measurements.class) {
				prt = "measurements " + prt;
				copy = ((measurements) copy).getObjt();
			}
		}
		//delete space ent of the string
		String last = prt.substring(0,prt.length()-1);
		Main.writer.write(last);

	}
	// calculate total cost of examination 
	public int cost() {
		int value = cost;
		Operations copy = this.objt;
		while (copy != null) {
			if (copy.getClass() == imaging.class) {
				value += ((imaging) copy).getCost();
				copy = ((imaging) copy).getObjt();

			} else if (copy.getClass() == tests.class) {
				value += ((tests) copy).getCost();
				copy = ((tests) copy).getObjt();
			} else if (copy.getClass() == doctorvisit.class) {
				value += ((doctorvisit) copy).getCost();
				copy = ((doctorvisit) copy).getObjt();
			} else if (copy.getClass() == measurements.class) {
				value += ((measurements) copy).getCost();
				copy = ((measurements) copy).getObjt();
			}
		}
		return value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Operations getObjt() {
		return objt;
	}

	public void setObjt(Operations objt) {
		this.objt = objt;
	}

}
