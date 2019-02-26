package Exeptions;

public class InvalidAmountOfCharactersException extends Exception{
static String staticMessage="invalid amount of characters";
	
	
	public InvalidAmountOfCharactersException(String message){
		super(staticMessage+message);
	}

}
