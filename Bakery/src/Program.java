
public class Program {
	
	public static void main(String[] args) {
		
		byte sumToppings = 0;
		
		Pizza basicPizza = new Pizza((byte)10, (byte)8,(byte) 1);
		basicPizza.setDiametre((byte)50);
		basicPizza.setSlices((byte)8);
		basicPizza.setToppings((byte)0);
		System.out.println("This pizza is basic pizza " + basicPizza.isBasicPizza());
		//basicPizza.printData();
		
		Pizza chickenMushroomPizza = new Pizza((byte)10, (byte)8,(byte) 1);
		chickenMushroomPizza.setDiametre((byte)40);
		chickenMushroomPizza.setSlices((byte)6);
		chickenMushroomPizza.setToppings((byte)2);
		System.out.println("This pizza is basic pizza " + chickenMushroomPizza.isBasicPizza());
		//chickenMushroomPizza.printData();
		
		Pizza veggiePizza = new Pizza((byte)10, (byte)8,(byte) 1);
		veggiePizza.setDiametre((byte)50);
		veggiePizza.setSlices((byte)8);
		veggiePizza.setToppings((byte)4);
		System.out.println("This pizza is basic pizza " + veggiePizza.isBasicPizza());
		//veggiePizza.printData();
		
		Pizza italianPizza = new Pizza((byte)10, (byte)8,(byte) 1);
		italianPizza.setDiametre((byte)30);
		italianPizza.setSlices((byte)4);
		italianPizza.setToppings((byte)2);
		System.out.println("This pizza is basic pizza " + italianPizza.isBasicPizza());
		//italianPizza.printData();
		
		Pizza smallPizza = new Pizza((byte)10, (byte)8,(byte) 1);
		smallPizza.setDiametre((byte)10);
		smallPizza.setSlices((byte)1);
		smallPizza.setToppings((byte)0);
		System.out.println("This pizza is basic pizza " + smallPizza.isBasicPizza());
		//smallPizza.printData();
		
		
		
		Pizza[] menu = {basicPizza, chickenMushroomPizza, veggiePizza, italianPizza, smallPizza};
		for (int i=0; i<menu.length; i++){
			menu[i].printData();
			sumToppings = (byte) (sumToppings + menu[i].getToppings());
		}
		System.out.println("Amount of all the toppings is: " + sumToppings);
	}

}
