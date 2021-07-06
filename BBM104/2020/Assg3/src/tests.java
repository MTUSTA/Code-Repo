
public class tests extends Operations{
	private Operations objt = null;

	public tests(Operations objt) {
		super(7);
		this.objt = objt;
	}

	public tests() {
		super(7);
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
