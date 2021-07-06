
public class imaging extends Operations {
	private Operations objt = null;

	public imaging(Operations objt) {
		super(10);
		this.objt = objt;
	}

	
	public imaging() {
		super(10);
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
