package DBDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Exeptions.DataDoesNotExist;



/**
 *all DBDAO classes extends from this class. It contains general methods
 *and properties which are used by all DBDAO classes. It is not
 *abstract because it is used also for create and drop DB in Test
 *class
 * @author elkon
 * 

 *
 */
public class BasicDBDAO {
	//-------------------CREATE TABLE STATEMENTS-----------------------

	private final String categorySQL = "CREATE TABLE categories (" +
			"ID INT PRIMARY KEY AUTO_INCREMENT, " +
			"name VARCHAR(50) NOT NULL)";
	private final String companySQL = "CREATE TABLE companies (" +
			"ID INT PRIMARY KEY AUTO_INCREMENT, " +
			"name VARCHAR(50) NOT NULL, " +
			"email VARCHAR(50) NOT NULL, " +
			"password VARCHAR(9) NOT NULL)";
	/**
	 * please pay attention: coupons, which refer to companies or
	 * categories which were deleted will be deleted
	 * automatically because of CASCADE clause.
	 */
	private final String couponSQL = "CREATE TABLE coupons (" +
			"company_id INT, " +
			"INDEX company_id (company_id),"+
			"FOREIGN KEY (company_id) REFERENCES companies(ID) ON DELETE CASCADE,"+
			"category_id INT, " +
			"INDEX category_id (category_id),"+
			"FOREIGN KEY (category_id) REFERENCES categories(ID) ON DELETE CASCADE,"+
			"ID INT PRIMARY KEY AUTO_INCREMENT, " +
			"title VARCHAR(50) NOT NULL, " +
			"description VARCHAR(250) NOT NULL, " +
			"start_date DATE NOT NULL, " +
			"end_date DATE NOT NULL, " +
			"amount INT, " +
			"price DOUBLE NOT NULL, " +
			"image VARCHAR(400) NOT NULL)";
	private final String customerSQL = "CREATE TABLE customers (" +
			"ID INT PRIMARY KEY AUTO_INCREMENT, " +
			"first_name VARCHAR(50) NOT NULL, " +
			"last_name VARCHAR(50) NOT NULL, " +
			"email VARCHAR(50) NOT NULL, " +
			"password VARCHAR(9) NOT NULL)";
	/**
	 * please pay attention: row, which refers to coupons or
	 * customers which were deleted will be deleted
	 * automatically because of CASCADE clause.
	 */
	private final String customersVScouponsSQL = "CREATE TABLE customers_vs_coupons (" +
			"customer_id INT NOT NULL, " +
			"coupon_id INT NOT NULL," +
			"INDEX customer_id(customer_id),"+
			"FOREIGN KEY (customer_id) REFERENCES customers(ID) ON DELETE CASCADE,"+
			"INDEX coupon_id(coupon_id),"+
			"FOREIGN KEY (coupon_id) REFERENCES coupons(ID) ON DELETE CASCADE,"+
			"PRIMARY KEY (customer_id,coupon_id))";
	protected ConnectionPool connectionPool = ConnectionPool.getInstance();
	//-------------------FINAL STRINGS TABLE NAMES FOR CHECK METHODS-----------------
	public static final String tableNameCompanies = "companies";
	public static final String tableNameCategories = "categories";
	public static final String tableNameCustomers = "customers";
	public static final String tableNameCoupons = "coupons";
	public static final String tableNameCustomersVSCoupons = "customers_vs_coupons";
	// ------------------CHECK IF ID EXIST------------------
	/**
	 * this check was added to avoid mistakes when object, which
	 * is sent as a parameter to DBDAO method doesn't have
	 * a proper ID
	 * @param id
	 * @param tableName
	 * @return
	 * @throws Exception
	 * DataDoesNotExist
	 */
	public boolean isIDExists(int id, String tableName) throws Exception {
		if (tableName==tableNameCustomers||tableName==tableNameCompanies||tableName==tableNameCoupons||tableName==tableNameCategories) {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM " +tableName+ " WHERE ID = %d", id);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");

					return count == 1;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}else throw new DataDoesNotExist("You've entered ID: "+id+"or wrong table name: "+tableName);
	}

	//-------------------BUILD DB---------------------------
	/**
	 * @param sqlTable
	 */
	public void buildDB(String sqlTable) {
		
		Connection connection =null;
		Statement statement = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();	
			statement.executeUpdate(sqlTable);
			
			System.out.println("table has been created.");			
		}
		catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		finally{
			connectionPool.restoreConnection(connection);
		}
	}
	//-------------------DROP DB---------------------------
	/**
	 * @param tableName
	 */
	public void dropDB(String tableName) {
		
		Connection connection =null;
		Statement statement = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();	
			statement.executeUpdate("DROP TABLE "+tableName);
			
			System.out.println("table has been droped.");			
		}
		catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		finally{
			connectionPool.restoreConnection(connection);
		}
	}
	//-------------------CHECK IF COUPON OWNER EXIST (for update methods to enable password and email updates)
	/**
	 * this method was added to allow email and password update.
	 * @param oldEmail
	 * this param is used to check if coupon owner exists
	 * @param oldPassword
	 * this param is used to check if coupon owner exists
	 * @param tableName
	 * @return
	 * @throws Exception
	 * DataDoesNotExist
	 * 
	 *
	 */
	public boolean isCouponOwnerExists(String oldEmail, String oldPassword, String tableName) throws Exception {
		if (tableName=="customers"||tableName=="companies") {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM "+tableName+" WHERE EMAIL = '%s' AND PASSWORD = '%s'",
					oldEmail, oldPassword);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");

					return count == 1;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		}else throw new DataDoesNotExist("You've entered email: "+oldEmail+"and password: "+oldPassword+". Or the table name is wrong: "+tableName);
	}

	//-------------------ADD ALL CATEGORY TO TABLE--------------------
	
	/**
	 * @param category
	 * @throws SQLException
	 * @throws Exception
	 */
	public void addCategory(String category) throws SQLException, Exception {
			Connection connection = null;

			try {

				connection = connectionPool.getConnection();

				String sql = String.format("INSERT INTO CATEGORIES(NAME) " + "VALUES('%s')",
						category);

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

					preparedStatement.executeUpdate();
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
		 
		
	}
	
	public String getCategorySQL() {
		return categorySQL;
	}

	public String getCompanySQL() {
		return companySQL;
	}

	public String getCouponSQL() {
		return couponSQL;
	}

	public String getCustomerSQL() {
		return customerSQL;
	}

	public String getCustomersVScouponsSQL() {
		return customersVScouponsSQL;
	}
	
}
