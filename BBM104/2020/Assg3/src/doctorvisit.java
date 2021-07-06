
public class doctorvisit extends Operations {
	private Operations objt = null;

	public doctorvisit(Operations objt) {
		super(15);
		this.objt = objt;
	}
	

	public doctorvisit() {
		super(15);
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
