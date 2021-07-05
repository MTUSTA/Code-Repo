
public class Onion extends Material{
	private Material malzeme=null;
	public Onion(Material malzeme) {
		super(2);
		this.setMalzeme(malzeme);
	}
	public Onion() {
		super(2);
	}
	public Material getMalzeme() {
		return malzeme;
	}
	public void setMalzeme(Material malzeme) {
		this.malzeme = malzeme;
	}

}
