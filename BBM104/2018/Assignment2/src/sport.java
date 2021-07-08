
public class sport {
	private int sportID,calorieBurned;
	private String nameOfSport;
	public sport(int sportID,String nameOfSport,int calorieBurned) {
		this.setSportID(sportID);
		this.setNameOfSport(nameOfSport);
		this.setCalorieBurned(calorieBurned);
	}
	public int getSportID() {
		return sportID;
	}
	public void setSportID(int sportID) {
		this.sportID = sportID;
	}
	public int getCalorieBurned() {
		return calorieBurned;
	}
	public void setCalorieBurned(int calorieBurned) {
		this.calorieBurned = calorieBurned;
	}
	public String getNameOfSport() {
		return nameOfSport;
	}
	public void setNameOfSport(String nameOfSport) {
		this.nameOfSport = nameOfSport;
	}

}
