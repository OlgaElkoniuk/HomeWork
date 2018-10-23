
public class Plane implements IFly{
	
private String pilotName;
private String companyName;
private String direction;


public String getPilotName() {
	return pilotName;
}

public String getCompanyName() {
	return companyName;
}

public String getDirection() {
	return direction;
}

public void fly(int speed) {
	speed = (int )(Math.random() * 100 + 1);
	System.out.println("The speed of plane is: "+ speed);
	}

public boolean land() {
		System.out.println("The plane has landed sucsessfully");
		return true;
	}


}
