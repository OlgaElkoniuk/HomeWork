
public class Bird implements IFly {
	public void setKindOfBird(String kindOfBird) {
		this.kindOfBird = kindOfBird;
	}

	public void setAge(int age) {
		this.age = (age>0&&age<100)?age:1;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	private String kindOfBird;
	private int age;
	private String colour;
	
	

	public String getKindOfBird() {
		return kindOfBird;
	}

	public int getAge() {
		return age;
	}

	public String getColour() {
		return colour;
	}

	public void fly(int speed) {
		speed = (int )(Math.random() * 50 + 1);
		System.out.println("The speed of bird is: "+ speed);
	}

	public boolean land() {
		System.out.println("The bird has landed sucsessfully");
		return false;
	}

}
