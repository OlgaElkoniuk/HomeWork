package Exeptions;

public class LoginFailedException extends Exception {
	static String staticMessage="Your login failed. You've entered wrong email or password: ";
	
	
	public LoginFailedException(String message){
		super(staticMessage+message);
	}
}
