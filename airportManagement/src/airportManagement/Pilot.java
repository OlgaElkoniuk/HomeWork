package airportManagement;

public class Pilot extends Person {
	
public Pilot(String name, String surname) {
		super(name, surname);
	}

private String licsence;
private int seniority;

public String getLicsence() {
	return licsence;
}
public void setLicsence(String licsence) {
	this.licsence = (licsence.length()==8)?licsence:"Number of licsence not valid";
}
public int getSeniority() {
	return seniority;
}
public void setSeniority(int seniority) {
	this.seniority=(seniority>=0&&seniority<=60)?seniority:0;
}

@Override public String toString(){
	return super.toString()+ " , licsence number: "+licsence+", seniority: "+seniority;
}
}
