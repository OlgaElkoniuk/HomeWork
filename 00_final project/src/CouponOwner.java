import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//---------------properties------------------
public abstract class CouponOwner {
private int id;
private String email;
private String password;
private ArrayList coupons;
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
public void setCoupons(ArrayList coupons) {
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
public ArrayList getCoupons(int id) {
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
public CouponOwner(int id, String email, String password, ArrayList coupons) throws InvalidEmailException, InvalidAmountOfCharactersException {
	super();
	setId(id);;
	setEmail(email);
	setPassword(password);
	setCoupons(coupons);
}


}
