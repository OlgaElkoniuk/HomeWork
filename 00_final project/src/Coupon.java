import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon {
	static String sql = "CREATE TABLE coupons (" +
			"id INT PRIMARY KEY AUTO_INCREMENT, " +
			"company_id INT FOREIGN KEY, " +
			"category_id INT FOREIGN KEY, " +
			"title VARCHAR(50) NOT NULL, " +
			"description VARCHAR(250) NOT NULL, " +
			"start_date DATE NOT NULL, " +
			"end_date DATE NOT NULL, " +
			"amount INT, " +
			"price DOUBLE NOT NULL, " +
			"image VARCHAR(400))";
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private int id;
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
	public void setId(int id) {
		this.id = id;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) throws InvalidAmountOfCharactersException{
		if (title.length()<=8)
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) throws InvalidDateException{
		if (startDate.before(new Date())) 
		throw new InvalidDateException("this date has passed, you enterd: "+startDate);
		else this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
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
	//-------------------constructor------------------
	public Coupon(int id, Categories category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image) throws InvalidDateException, InvalidNumberException, InvalidAmountOfCharactersException {
		super();
		setId(id);
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
