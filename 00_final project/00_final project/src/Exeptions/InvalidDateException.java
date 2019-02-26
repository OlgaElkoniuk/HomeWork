package Exeptions;

public class InvalidDateException extends Exception{
static String staticMessage="invalid date";
	
	
	public InvalidDateException(String message){
		super(staticMessage+message);
	}

}