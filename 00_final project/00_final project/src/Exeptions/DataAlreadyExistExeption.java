package Exeptions;

public class DataAlreadyExistExeption extends Exception{
static String staticMessage="The input data already exists";
	
	
	public DataAlreadyExistExeption(String message){
		super(staticMessage+message);
	}

}