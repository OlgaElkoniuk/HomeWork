package airportManagement;

public class EarthSteward extends Person {
public EarthSteward(String name, String surname) {
		super(name, surname);
	}
private String duty;
private String originCountry;
private int seniority;
public String getDuty() {
	return duty;
}
public void setDuty(String duty) {
	this.duty = duty;
}
public String getOriginCountry() {
	return originCountry;
}
public void setOriginCountry(String originCountry) {
	this.originCountry = originCountry;
}
public int getSeniority() {
	return seniority;
}
public void setSeniority(int seniority) {
	this.seniority=(seniority>=0&&seniority<=60)?seniority:0;
}
@Override public String toString(){
	return super.toString()+ " , origin country: "+originCountry+
			", seniority: "+seniority+", duty: "+duty;
}
}
