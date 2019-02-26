package Exeptions;

public class PurchaseFailedException extends Exception {
static String staticMessage="Purchase failed. ";
	
	
	public PurchaseFailedException(String message){
		super(staticMessage+message);
	}

}
