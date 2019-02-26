package JavaBeans;
import java.util.ArrayList;

import Exeptions.InvalidAmountOfCharactersException;
import Exeptions.InvalidEmailException;


public class Company extends CouponOwner{



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
	/**
	 * constructor to add info
	 * @param name
	 * @param email
	 * @param password
	 * @throws InvalidEmailException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Company(String name, String email, String password) throws InvalidEmailException, 
			InvalidAmountOfCharactersException {
		super( email, password);
		setName(name);
	}
	/**
	 * constructor to collect info
	 * @param name
	 * @param id
	 * @param email
	 * @param password
	 * @param coupons
	 * @throws InvalidEmailException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Company(String name,int id, String email, String password, 
			ArrayList<Coupon> coupons) throws InvalidEmailException, 
			InvalidAmountOfCharactersException {
		super( id, email, password, coupons);
		setName(name);
	}
	//---------------to string-----------------
	public String toString(){
		return "company id: "+super.getId()+"\ncompany email: "+super.getEmail()+
				"\ncompany password: "+super.getPassword()+"\nname: "+name+"\n";
	}

}
