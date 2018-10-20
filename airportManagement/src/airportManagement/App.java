package airportManagement;

public class App {

	public static void main(String[] args) {
	Pilot pilot1 = new Pilot("Stephen", "King");
	pilot1.setLicsence("12345678");
	pilot1.setSeniority(10);
	EarthSteward earthSteward1 = new EarthSteward("Kate", "Smith");
	earthSteward1.setDuty("check documents");
	earthSteward1.setOriginCountry("UK");
	earthSteward1.setSeniority(3);
	AirSteward airSteward1 = new AirSteward("Alice", "Pull");
	airSteward1.setOriginCountry("France");
	airSteward1.setServiceClass("first class");
	Passanger passanger1 = new Passanger("Roger", "Good");
	passanger1.setPassport("87654321");
	
	
	System.out.println(pilot1.toString());
	System.out.println(earthSteward1.toString());
	System.out.println(airSteward1.toString());
	System.out.println(passanger1.toString());

	}

}
