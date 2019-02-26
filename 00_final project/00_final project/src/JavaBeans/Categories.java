package JavaBeans;

import java.util.ArrayList;

public enum Categories {
	BOOKS,
	CLOTHES,
	ENTERTAIMENT,
	FOOD_PRODUCTS,
	HEALTHCARE,
	HOME_STORE,
	KIDS,
	RESTAURANTS,
	SPORT,
	TECHNICS;
	
	
	/**
	 * this ArrayList is used to add all categories to the DB
	 * @return
	 * ArrayList with all categories.
	 */
	public static final ArrayList <String> categoriesList(){
		ArrayList <String> categories= new ArrayList <String>();
		categories.add(Categories.BOOKS.toString());
		categories.add(Categories.CLOTHES.toString());
		categories.add(Categories.ENTERTAIMENT.toString());
		categories.add(Categories.FOOD_PRODUCTS.toString());
		categories.add(Categories.HEALTHCARE.toString());
		categories.add(Categories.HOME_STORE.toString());
		categories.add(Categories.KIDS.toString());
		categories.add(Categories.RESTAURANTS.toString());
		categories.add(Categories.SPORT.toString());
		categories.add(Categories.TECHNICS.toString());
		return categories;
		
	}
}
