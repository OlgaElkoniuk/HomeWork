package Exeptions;

public class NoIDException extends Exception{
static String staticMessage="Update wont be executed. Please add the ID and try again.";
	
	
	public NoIDException(String message){
		super(message+staticMessage);
	}	
	}