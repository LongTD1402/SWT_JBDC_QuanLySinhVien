package exceptions_handling;

public class NameStringException extends Exception{
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

	public NameStringException(String message) {
		this.message ="[!!!]"+ message;
	}
	
}
