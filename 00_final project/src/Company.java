import java.util.ArrayList;


public class Company extends CouponOwner{


	static String sql = "CREATE TABLE companies (" +
			"id INT PRIMARY KEY AUTO_INCREMENT, " +
			"name VARCHAR(50) NOT NULL, " +
			"email VARCHAR(50) NOT NULL, " +
			"password VARCHAR(9) NOT NULL)";
	//---------------properties---------------
	private String name;
	//---------------setters and getters---------------

	public String getName() {
		return name;
	}
	public void setName(String name) throws InvalidAmountOfCharactersException{
		if(name.length()<=50)
		this.name = name;
		else throw new InvalidAmountOfCharactersException(" , name cant be longer "
				+ "than 50 characters, you entered: " + name);
	}
	//---------------constructor---------------
	public Company(String name, int id, String email, String password, 
			ArrayList coupons) throws InvalidEmailException, 
			InvalidAmountOfCharactersException {
		super(id, email, password, coupons);
		setName(name);
	}
	
	//---------------to string-----------------
	public String toString(){
		return "company id: "+super.getId()+"\ncompany email: "+super.getEmail()+
				"\ncompany password: "+super.getPassword()+"\nname: "+name
				+"\ncoupons: "+super.getCoupons(super.getId());
	}

}
