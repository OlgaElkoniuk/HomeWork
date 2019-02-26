package JavaBeans;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Exeptions.InvalidAmountOfCharactersException;
import Exeptions.InvalidDateException;
import Exeptions.InvalidNumberException;

public class Coupon {
	
	public static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private int id;
	private int company_id;
	private Categories category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	
	//------------------------setters and getters----------------------
	
	public int getId() {
		return id;
	}
	public void setCouponId(int id) {
		this.id = id;
	}
	public Categories getCategory() {
		return category;
	}
	public int getCategoryId() {
		return getCategory().ordinal()+1;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) throws InvalidAmountOfCharactersException{
		if (title.length()<=50)
		this.title = title;
		else throw new InvalidAmountOfCharactersException(" , title should not be longer than 50 characters, you entered: " + title);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) throws InvalidAmountOfCharactersException{
		if(description.length()<=250)
		this.description = description;
		else throw new InvalidAmountOfCharactersException(" , title should not be longer than 50 characters, you entered: " + title);
	}
	public String getStartDate() {
		return sdf.format(startDate);
	}
	public void setStartDate(Date startDate) throws InvalidDateException{
		if (startDate.before(new Date())) 
		throw new InvalidDateException("this date has passed, you enterd: "+startDate);
		else this.startDate = startDate;
	}
	public String getEndDate() {
		return sdf.format(endDate);
	}
	public void setEndDate(Date endDate) throws InvalidDateException{
		if (endDate.before(startDate)) 
		throw new InvalidDateException("the end date cant be before start date, you enterd: "+endDate);
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) throws InvalidNumberException{
		if (amount>0)
		this.amount = amount;
		else throw new InvalidNumberException(" ,amount cant be negative , you enterd: "+amount);
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) throws InvalidNumberException{
		if (price>0)
		this.price = price;
		else throw new InvalidNumberException(" ,price cant be negative , you enterd: "+price);
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	//-------------------constructor------------------
	/**
	 * constructor to collect info
	 * @param company_id
	 * @param coupon_id
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param price
	 * @param image
	 * @throws InvalidDateException
	 * @throws InvalidNumberException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Coupon(int company_id, int coupon_id, Categories category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image) throws InvalidDateException, InvalidNumberException, InvalidAmountOfCharactersException {
		super();
		setCompany_id(company_id);
		setCouponId(coupon_id);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
	/**
	 * constructor to add info
	 * @param company_id
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param price
	 * @param image
	 * @throws InvalidDateException
	 * @throws InvalidNumberException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Coupon(int company_id, Categories category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image) throws InvalidDateException, InvalidNumberException, InvalidAmountOfCharactersException {
		super();
		setCompany_id(company_id);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
//---------------to string--------------------------
	public String toString(){
		return "coupon id: "+id+"\ncoupon category: "+category+"\ntitle: "+title
				+"\ndescription: "+"\nstart date: "+startDate+"\nend date: "+endDate
				+"\namount: "+amount+"\nprice: "+price+"\nimage: "+image;
	}

}
