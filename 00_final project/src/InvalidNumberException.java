
public class InvalidNumberException extends Exception{
static String staticMessage="invalid number";
	
	
	public InvalidNumberException(String message){
		super(staticMessage+message);
	}

}