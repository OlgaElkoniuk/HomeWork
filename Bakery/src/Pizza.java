
public class Pizza {
	
private byte diametre;
private byte slices;
private byte toppings;

public boolean isBasicPizza(){
//	if (toppings==0)
//		return  true;
//		else return false;
	return (toppings==0)?true:false;
}
public void printData(){
	System.out.println("Diametre " + diametre + "," + " Slices " + slices + "," + " Toppings " + toppings);
}
public byte getDiametre() {
	return diametre;
}
public void setDiametre(byte diametre) {
	if (diametre>=10&&diametre<=50)
	this.diametre = diametre;
	else {this.diametre = 0;
	System.out.println("Sorry, diametre can be only 10-50 cm");
	}
}
public byte getSlices() {
	return slices;
}
public void setSlices(byte slices) {
	if (slices>=1&&slices<=8)
	this.slices = slices;
	else {this.slices = 0;
	System.out.println("Sorry, number of slices can be only 1-8");
	}
}
public byte getToppings() {
	return toppings;
}
public void setToppings(byte toppings) {
	if (toppings>-1&&toppings<=5)
	this.toppings = toppings;
	else{this.toppings = 0;
	System.out.println("Sorry, amount of toppings can be only 0-5");
	}
}
public Pizza(byte diametre, byte slices, byte toppings) {
	super();
	this.diametre = 10;
	this.slices = 1;
	this.toppings = 0;
}

}
