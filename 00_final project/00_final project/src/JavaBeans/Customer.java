package JavaBeans;
import java.util.ArrayList;

import Exeptions.InvalidAmountOfCharactersException;
import Exeptions.InvalidEmailException;


public class Customer extends CouponOwner{

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
	/**
	 * constructor to add info
	 * @param email
	 * @param password
	 * @param firstName
	 * @param secondName
	 * @throws InvalidEmailException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Customer(String email, String password,
			String firstName, String secondName) throws InvalidEmailException,
			InvalidAmountOfCharactersException {
		super(email, password);
		setFirstName(firstName);
		setSecondName(secondName);
	}
	/**
	 * constructor to collect info
	 * @param id
	 * @param email
	 * @param password
	 * @param coupons
	 * @param firstName
	 * @param secondName
	 * @throws InvalidEmailException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Customer(int id, String email, String password, ArrayList<Coupon>coupons,
			String firstName, String secondName) throws InvalidEmailException,
			InvalidAmountOfCharactersException {
		super(id,email, password, coupons);
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
