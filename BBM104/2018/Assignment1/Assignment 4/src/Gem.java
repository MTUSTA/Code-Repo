
public abstract class Gem {
	private String gemtype;
	private int point;
	private int coordx,coordy;

	public Gem(String gemtype,int puan,int coordx,int coordy) {
		this.setGemtype(gemtype);
		this.setPoint(puan);
		this.setCoordx(coordx);
		this.setCoordy(coordy);
	}

	public String getGemtype() {
		return gemtype;
	}

	public void setGemtype(String gemtype) {
		this.gemtype = gemtype;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getCoordx() {
		return coordx;
	}

	public void setCoordx(int coordx) {
		this.coordx = coordx;
	}

	public int getCoordy() {
		return coordy;
	}

	public void setCoordy(int coordy) {
		this.coordy = coordy;
	}
}
