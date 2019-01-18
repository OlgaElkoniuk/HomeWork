import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CustomersDBDAO implements CustomersDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
//----------------ADD--------------------------
	@Override
	public void addCustomer(Customer customer) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO customers(SECONDNAME, FIRSTNAME, EMAIL, PASSWORD) " + 
					"VALUES('%s', '%s','%s', '%s')",
					customer.getSecondName(), customer.getFirstName(), customer.getEmail(), customer.getPassword());

			try(PreparedStatement preparedStatement = 
					connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					customer.setId(id); // Add the new created id into the customer object.
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	//----------------UPDATE--------------------------

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE customers SET SECONDNAME='%s', FIRSTNAME='%s', EMAIL='%s', PASSWORD='%s' WHERE ID=%d",
					customer.getSecondName(), customer.getFirstName(), customer.getEmail(), customer.getPassword(), customer.getId());

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM customers";

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Customer> allCustomers = new ArrayList<Customer>();
					
					while(resultSet.next()) {

						int id = resultSet.getInt("ID");
						String secondName = resultSet.getString("SECONDNAME");
						String firstName = resultSet.getString("FIRSTNAME");
						String email = resultSet.getString("EMAIL");
						String password = resultSet.getString("PASSWORD");
						ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCustomerID(id);
	
						Customer customer = new Customer(id, email, password, coupons, firstName, secondName);
						
						allCustomers.add(customer);
					}
					
					return allCustomers;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public Customer getOneCustomer(int customerID) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM customers WHERE ID=%d", customerID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String secondName = resultSet.getString("SECONDNAME");
					String firstName = resultSet.getString("FIRSTNAME");
					String email = resultSet.getString("EMAIL");
					String password = resultSet.getString("PASSWORD");
					ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCustomerID(customerID);

					Customer customer = new Customer(customerID, email, password, coupons, firstName, secondName);
					return customer;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	

	@Override
	public boolean isCustomerExists(String email, String password)throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM COMPANIES WHERE EMAIL = '%s' AND PASSWORD = '%s'",
					email,password);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					int count = resultSet.getInt("Count");

					return count == 1;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public void deleteCustomer(int customerID) throws Exception {
		// TODO Auto-generated method stub

	}

}
