package JavaBeans;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exeptions.InvalidAmountOfCharactersException;
import Exeptions.InvalidEmailException;

//---------------properties------------------
/**
 * Company and Customer classes extend
 * from this class. It was added in order to avoid
 * writing setters/getters of email, password and 
 * coupon array twice
 * @author elkon
 *
 */
public abstract class CouponOwner {
private int id;
private String email;
private String password;
private ArrayList<Coupon> coupons;
private Pattern regexPattern;
private Matcher regMatcher;

//---------------setters and getters------------------
public void setId(int id) {
	this.id = id;
}
public void setEmail(String email) throws InvalidEmailException{
	if (validateEmailAddress(email))
	this.email = email;
	else throw new InvalidEmailException(" , you enterd: "+email);
}
public void setPassword(String password) throws InvalidAmountOfCharactersException{
	if (password.length()<=8)
	this.password = password;
	else throw new InvalidAmountOfCharactersException(" , you enterd: "+password);
	
}
public void setCoupons(ArrayList<Coupon> coupons) {
	this.coupons = coupons;
}

public int getId() {
	return id;
}
public String getEmail() {
	return email;
}
public String getPassword() {
	return password;
}
public ArrayList<Coupon> getCoupons(int id) {
	return coupons;
}

//-------------email validation function----------------


private boolean validateEmailAddress(String emailAddress) {

    regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
    regMatcher   = regexPattern.matcher(emailAddress);
    if(regMatcher.matches()) {
        return true;
    } else {
        return false;
    }
}
//-------------constructor----------------

/**
 * this constructor is needed to collect info from DB
 * and store it inside the object. 
 * @param id
 * @param email
 * @param password
 * @param coupons
 * @throws InvalidEmailException
 * @throws InvalidAmountOfCharactersException
 */
public CouponOwner(int id, String email, String password, ArrayList<Coupon> coupons) throws InvalidEmailException, InvalidAmountOfCharactersException {
	super();
	setId(id);
	setEmail(email);
	setPassword(password);
	setCoupons(coupons);
}
/**
 * this constructor is needed to create an instance of
 * objects which should be added to the DB. It
 * doesn't contain ID because DB will give it
 * automatically
 * @param email
 * @param password
 * @throws InvalidEmailException
 * @throws InvalidAmountOfCharactersException
 */
public CouponOwner(String email, String password) throws InvalidEmailException, InvalidAmountOfCharactersException {
	super();
	setEmail(email);
	setPassword(password);
}
}
