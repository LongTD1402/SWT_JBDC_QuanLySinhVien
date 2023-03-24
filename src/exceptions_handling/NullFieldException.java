package exceptions_handling;

public class NullFieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NullFieldException(String message) {
		this.message = "[!!!] "+ message;
	}

}
