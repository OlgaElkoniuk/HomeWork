package airportManagement;

public class Passanger extends Person {
	public Passanger(String name, String surname) {
		super(name, surname);
	}
	private String passport;

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = (passport.length()==8)?passport:"Number of licsence not valid";
	}
	@Override public String toString(){
		return super.toString()+ " , passport number: "+passport;
	}
}
