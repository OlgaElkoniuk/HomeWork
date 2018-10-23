
public class App {	
	
	
	public static IFly createFly(){
	int random = (int )(Math.random() * 3 + 1);
	switch(random){
	case 1: IFly bird = new Bird();
	System.out.println("The object is bird");
	return bird;
	case 2: IFly plane = new Plane();
	System.out.println("The object is plane");
	return plane;
	case 3: IFly kite = new Kite();
	System.out.println("The object is kite");
	return kite;
	}
	return null;
 
	}

	public static void main(String[] args) {
		
		IFly [] flyObj = new IFly [10];
		for (IFly obj:flyObj){
			obj = createFly();
			obj.fly(0);
			obj.land();
		}
		
	
	}

}
