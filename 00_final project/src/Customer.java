import java.util.ArrayList;


public class Customer extends CouponOwner{
	static String sql = "CREATE TABLE customers (" +
			"id INT PRIMARY KEY AUTO_INCREMENT, " +
			"first_name VARCHAR(50) NOT NULL, " +
			"last_name VARCHAR(50) NOT NULL, " +
			"email VARCHAR(50) NOT NULL, " +
			"password VARCHAR(9) NOT NULL)";
	//---------------properties---------------
	private String firstName;
	private String secondName;
	//---------------setters and getters---------------
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws InvalidAmountOfCharactersException{
		if(firstName.length()<=50)
		this.firstName = firstName;
		else throw new InvalidAmountOfCharactersException(" , name cant be longer "
				+ "than 50 characters, you entered: " + firstName);
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) throws InvalidAmountOfCharactersException{
		if(secondName.length()<=50)
		this.secondName = secondName;
		else throw new InvalidAmountOfCharactersException(" , name cant be longer "
				+ "than 50 characters, you entered: " + secondName);
	}
	//---------------constructor---------------
	public Customer(int id, String email, String password, ArrayList coupons,
			String firstName, String secondName) throws InvalidEmailException,
			InvalidAmountOfCharactersException {
		super(id, email, password, coupons);
		setFirstName(firstName);
		setSecondName(secondName);
	}
	
	//---------------to string---------------
	public String toString(){
		return "customer id: "+super.getId()+"n/customer email: "+super.getEmail()+
				"n/customer password: "+super.getPassword()+"n/first name: "+firstName
				+"n/second name: "+secondName+"n/coupons: "+super.getCoupons(super.getId());
	}
}
