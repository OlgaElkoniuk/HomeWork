package airportManagement;

public class AirSteward extends Person {
	public AirSteward(String name, String surname) {
		super(name, surname);
	}

	private String originCountry;
	private int seniority;
	private String serviceClass;
	
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
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = (serviceClass=="economy"||serviceClass=="business"||serviceClass=="first class")?serviceClass:"not valid";
	}
	
	@Override public String toString(){
		return super.toString()+ " , origin country: "+originCountry+
				", seniority: "+seniority+", service class: "+serviceClass;
	}
}
