
public class Kite implements IFly {
	private String colour;
	private double price;
	

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setPrice(double price) {
		this.price = (price>0)?price:0;
	}

	public String getColour() {
		return colour;
	}

	public double getPrice() {
		return price;
	}


	public void fly(int speed) {
		speed = (int )(Math.random() * 10 + 1);
		System.out.println("The speed of kite is: "+ speed);
	}

	public boolean land() {
		System.out.println("The kite has landed sucsessfully");
		return true;
	}

}
