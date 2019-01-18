import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	//----------------CHECK IF COMPANY EXIST-------------------------
	public boolean isCompanyExists(String email, String password) throws Exception {

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
	//----------------ADD--------------------------

	public void addCompany(Company company) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO COMPANIES(NAME, EMAIL, PASSWORD) " + 
					"VALUES('%s', '%s', '%s')",
					company.getName(), company.getEmail(), company.getPassword());

			try(PreparedStatement preparedStatement = 
					connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					company.setId(id); // Add the new created id into the company object.
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	//----------------UPDATE--------------------------

	public void updateCompany(Company company) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE COMPANIES SET NAME='%s', EMAIL='%s', PASSWORD='%s' WHERE ID=%d",
					company.getName(), company.getEmail(), company.getPassword(), company.getId());

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}


	public void deleteCompany(int companyID) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM COMPANIES WHERE ID=%d", companyID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}

	public ArrayList<Company> getAllCompanies() throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM COMPANIES";

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Company> allCompanies = new ArrayList<Company>();
					
					while(resultSet.next()) {

						int id = resultSet.getInt("ID");
						String name = resultSet.getString("NAME");
						String email = resultSet.getString("EMAIL");
						String password = resultSet.getString("PASSWORD");
						ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCompanyID(id);
	
						Company company = new Company(name, id, email, password, coupons);
						
						allCompanies.add(company);
					}
					
					return allCompanies;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}



	public Company getOneCompany(int companyID) throws Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM COMPANIES WHERE ID=%d", companyID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String name = resultSet.getString("NAME");
					String email = resultSet.getString("EMAIL");
					String password = resultSet.getString("PASSWORD");
					ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCompanyID(companyID);

					Company company = new Company(name, companyID, email, password, coupons);

					return company;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
}
