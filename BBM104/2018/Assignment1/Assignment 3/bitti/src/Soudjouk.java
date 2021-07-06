
public class Soudjouk extends Material{

	private Material malzeme=null;
	public Soudjouk(Material malzeme) {
		super(3);
		this.setMalzeme(malzeme);
	}
	public Soudjouk() {
		super(3);
	}
	public Material getMalzeme() {
		return malzeme;
	}
	public void setMalzeme(Material malzeme) {
		this.malzeme = malzeme;
	}

}
