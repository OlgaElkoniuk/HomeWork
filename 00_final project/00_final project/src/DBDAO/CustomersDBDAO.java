package DBDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Exeptions.DataAlreadyExistExeption;
import Exeptions.DataDoesNotExist;
import Exeptions.NoIDException;
import JavaBeans.Coupon;
import JavaBeans.Customer;


public class CustomersDBDAO extends BasicDBDAO implements CustomersDAO {
	
//----------------ADD--------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#addCustomer(JavaBeans.Customer)
	 */
	@Override
	public void addCustomer(Customer customer) throws Exception {
		if (isCustomerExists(customer.getEmail(), customer.getPassword())==false) {
		Connection connection = null;
		try {

			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO customers(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD) " + 
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
		}else throw new DataAlreadyExistExeption("The customer with email: "+customer.getEmail()+" and/or password: "+customer.getPassword()+" already exists");
	}
	//----------------UPDATE--------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#updateCustomer(JavaBeans.Customer, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateCustomer(Customer customer, String oldEmail, String oldPassword) throws Exception {
	if (isCouponOwnerExists(oldEmail, oldPassword, tableNameCustomers)) {	
		if (customer.getId()!=0) {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE customers SET LAST_NAME='%s', FIRST_NAME='%s', EMAIL='%s', PASSWORD='%s' WHERE ID=%d",
					customer.getSecondName(), customer.getFirstName(), customer.getEmail(), customer.getPassword(), customer.getId());

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
		}else throw new NoIDException("The customer's ID ="+customer.getId()+". ");
	}else throw new DataDoesNotExist("The customer with email: "+oldEmail+" and/or password: "+oldPassword+" doesn't exist");
	}
	//----------------GET ALL CUSTOMERS-------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#getAllCustomers()
	 */
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
						String secondName = resultSet.getString("LAST_NAME");
						String firstName = resultSet.getString("FIRST_NAME");
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
	//----------------GET ONE CUSTOMER-------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#getOneCustomer(int)
	 */
	@Override
	public Customer getOneCustomer(int customerID) throws Exception {
		if (isIDExists(customerID, tableNameCustomers)) {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM customers WHERE ID=%d", customerID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String secondName = resultSet.getString("LAST_NAME");
					String firstName = resultSet.getString("FIRST_NAME");
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
		}else throw new DataDoesNotExist("You've entered ID: "+customerID);
	}
	//----------------CHECK IF CUSTOMER EXISTS-------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#isCustomerExists(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isCustomerExists(String email, String password)throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM customers WHERE EMAIL = '%s' AND PASSWORD = '%s'",
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
	//----------------DELETE CUSTOMER-------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#deleteCustomer(int)
	 */
	@Override
	public void deleteCustomer(int customerID) throws Exception {
		if (isIDExists(customerID, tableNameCustomers)) {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM customers WHERE ID=%d", customerID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
		}else throw new DataDoesNotExist("You've entered customer ID: "+customerID);
	}
	//----------------GET ONE CUSTOMER BY EMAIL-------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CustomersDAO#getCustomerByEmail(java.lang.String)
	 */
	@Override
	public Customer getCustomerByEmail(String email) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM customers WHERE EMAIL = '%s'", email);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String secondName = resultSet.getString("LAST_NAME");
					String firstName = resultSet.getString("FIRST_NAME");
					int id = resultSet.getInt("ID");
					String password = resultSet.getString("PASSWORD");
					ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCustomerID(id);

					Customer customer = new Customer(id, email, password, coupons, firstName, secondName);
					return customer;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}

}
