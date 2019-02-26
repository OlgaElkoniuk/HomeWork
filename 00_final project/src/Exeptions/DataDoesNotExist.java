package Exeptions;

public class DataDoesNotExist extends Exception{
static String staticMessage="The input data does not exist. ";
	
	
	public DataDoesNotExist(String message){
		super(staticMessage+message);
	}

}