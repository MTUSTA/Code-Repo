
public class measurements  extends Operations{
	private Operations objt = null;
	public measurements(Operations objt) {
		super(5);
		this.objt = objt;
	}
	
	public measurements() {
		super(5);
	}


	/**
	 * @return the objt
	 */
	public Operations getObjt() {
		return objt;
	}

	/**
	 * @param objt the objt to set
	 */
	public void setObjt(Operations objt) {
		this.objt = objt;
	}
}
