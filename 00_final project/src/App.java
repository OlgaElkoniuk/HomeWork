import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class App {

	   // all this props are used to create the connection to mySql
	   private static String USERNAME = "root";
	   private static String PASSWORD = null;
	   private static String URL = "jdbc:mysql://localhost:3306/coupon_management";

	public static void dropTable(){
		
		Connection connection =null;
		Statement statement = null;
		try {
			
			// Create a connection to the database: 
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			String sql = "DROP TABLE companies ";
			sql = "DROP TABLE customers ";
			
			statement = connection.createStatement();	
			statement.executeUpdate(sql);
			
			System.out.println("drop table");			
		}
		catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		finally{
			if(connection!=null)
				try {
					statement.close();
					connection.close();
				} catch (SQLException ex) {
					 System.out.println(ex.getMessage());
				}
		}
	}
	
	public static void buildDB(String sqlTable) {
		
		Connection connection =null;
		Statement statement = null;
		try {
			
			// Create a connection to the database: 
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			String sql = sqlTable;
			

			statement = connection.createStatement();	
			statement.executeUpdate(sql);
			
			System.out.println("companies table has been created.");			
		}
		catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		finally{
			if(connection!=null)
				try {
					statement.close();
					connection.close();
				} catch (SQLException ex) {
					 System.out.println(ex.getMessage());
				}
		}
	}
	
	

		

	//-------------------------insert for companies-----------------------------
	public static void insertCompanies(String name, int id, String email, String password){  	
	Connection connection =null;
	PreparedStatement preparedStatement = null;
	try {
		
		// Create a connection: 
		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		
		// Create sql statement: 
		String sql = String.format("INSERT INTO companies(name, id, email, password) "
				+ "VALUES('%s', %d, '%s','%s')", name, id, email, password);
		
		// Create object which can execute the above sql and return the new id:
		 preparedStatement = connection.prepareStatement(sql);
		
		// Execute (the insert command):
		int res=preparedStatement.executeUpdate();
		
		if(res!=0)
			System.out.println("Insert succeeded");
	}
	catch(Exception ex) {
		 System.out.println(ex.getMessage());
	}
	finally{
		if(connection!=null)
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
	}
	}
	//-------------------------insert for customers-----------------------------
		public static void insertCustomers(int id, String email, String password,
				String firstName, String secondName){  	
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		try {
			
			// Create a connection: 
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			// Create sql statement: 
			String sql = String.format("INSERT INTO customers(id, email, password, firstName, secondName) "
					+ "VALUES(%d, '%s', '%s','%s','%s')", id, email, password, firstName, secondName);
			
			// Create object which can execute the above sql and return the new id:
			 preparedStatement = connection.prepareStatement(sql);
			
			// Execute (the insert command):
			int res=preparedStatement.executeUpdate();
			
			if(res!=0)
				System.out.println("Insert succeeded");
		}
		catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		finally{
			if(connection!=null)
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException ex) {
					 System.out.println(ex.getMessage());
				}
		}
		}	
		//-------------------------insert for coupons-----------------------------

	
	//-------------------------קריאת נתונים מהטבלה-----------------------------
	public static void readAll(){
		
		Connection connection =null;
		Statement statement = null;
		
		try {
			
		// Create a connection: 
		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		
		
		// Create a statement object which can read data: 
		statement = connection.createStatement();
		
		// Create sql statement for reading data: 
		String sql = "SELECT * FROM products";
		
		// Execute the query and return an object which contains the data (the table):
		ResultSet resultSet = statement.executeQuery(sql);
		
		
		
		//loop over the rows in the ResultSet
		// next() - Moves the cursor forward one row from its current position - returns true if the new current row is valid; false if there are no more rows
		while(resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			double price = resultSet.getDouble("price");
			
			System.out.println("id: " + id + ", name: " + name + ", price: " + price);	
		}
		}
		catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		finally{
			if(connection!=null)
				try {
					statement.close();
					connection.close();
				} catch (SQLException ex) {
					 System.out.println(ex.getMessage());
				}
		}
		
	}
	
	
	
	//-------------------------עידכון נתונים בטבלה-----------------------------
	public static void update(int id, String name, double price) throws SQLException {
		
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		try {
			
			// Create a connection: 
		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
	
		// Create an update sql statement: 
		String sql = String.format("UPDATE products SET name='%s', price=%.2f WHERE id=%d", name, price, id);
		
		// Create an object for executing the above sql: 
	    preparedStatement = connection.prepareStatement(sql);
		
	int res=preparedStatement.executeUpdate();
		
		if(res!=0)
			System.out.println("Update succeeded");
	}
	catch(Exception ex) {
		 System.out.println(ex.getMessage());
	}
	finally{
		if(connection!=null)
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
	}
}
	
	
	
	//-------------------------מחיקת נתונים מהטבלה-----------------------------
	public static void delete(int id) throws SQLException {
		
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		try {
			
			// Create a connection: 
		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
	
		
		// Create sql command for delete one record: 
		String sql = String.format("DELETE FROM products WHERE id=%d", id);
		
		// Create an object for executing the above command: 
		preparedStatement = connection.prepareStatement(sql);
		
int res=preparedStatement.executeUpdate();
		
		if(res!=0)
			System.out.println("Delete succeeded");
	}
	catch(Exception ex) {
		 System.out.println(ex.getMessage());
	}
	finally{
		if(connection!=null)
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InvalidDateException, InvalidNumberException, InvalidAmountOfCharactersException, InvalidEmailException {
	

//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			dropTable(); 
//			buildDB(Company.sql);
//			
//			
//		}
//		catch(Exception ex) {
//			System.out.println("Error: " + ex.getMessage());
//		}
		Calendar c = Calendar.getInstance();

		c.setTime(new Date()); // Now use today date.

		
		Date startDate = new Date(2019, 5,22);//year-1900
		Date endDate = new Date(2019, 6,22);//month + 1
		Coupon coup = new Coupon(11, Categories.BOOKS, "title", "description", 
				startDate, endDate, 5, 2.4, "image");
		ArrayList <Coupon> coupons = new ArrayList <Coupon>();
		coupons.add(coup);
		Company com = new Company("ibm", 22, "elkonuk@gmail.com", "password", coupons);
System.out.println(com);
	}

}