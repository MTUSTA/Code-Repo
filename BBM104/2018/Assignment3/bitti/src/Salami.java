
public class Salami extends Material{
	private Material malzeme=null;
	public Salami(Material malzeme) {
		super(3);
		this.setMalzeme(malzeme);
	}
	public Salami() {
		super(3);
	}
	public Material getMalzeme() {
		return malzeme;
	}
	public void setMalzeme(Material malzeme) {
		this.malzeme = malzeme;
	}

}
