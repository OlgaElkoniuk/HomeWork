package Exeptions;

public class InvalidEmailException extends Exception {

	static String staticMessage="invalid email address";
	
	
	public InvalidEmailException(String message){
		super(staticMessage+message);
	}
}
