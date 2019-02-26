package DBDAO;
import java.util.ArrayList;
import Exeptions.DataAlreadyExistExeption;
import Exeptions.DataDoesNotExist;
import Exeptions.NoIDException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompaniesDBDAO extends BasicDBDAO implements CompaniesDAO {

	// ----------------CHECK IF COMPANY EXIST-------------------------
	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#isCompanyExists(java.lang.String, java.lang.String)
	 */
	public boolean isCompanyExists(String email, String password) throws SQLException, Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM COMPANIES WHERE EMAIL = '%s' AND PASSWORD = '%s'",
					email, password);

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
	}
	// ----------------ADD--------------------------

	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#addCompany(JavaBeans.Company)
	 */
	public void addCompany(Company company) throws SQLException, Exception {
		if (isCompanyExists(company.getEmail(), company.getPassword()) == false) {
			Connection connection = null;

			try {

				connection = connectionPool.getConnection();

				String sql = String.format("INSERT INTO COMPANIES(NAME, EMAIL, PASSWORD) " + "VALUES('%s', '%s', '%s')",
						company.getName(), company.getEmail(), company.getPassword());

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

					preparedStatement.executeUpdate();

					try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
						resultSet.next();
						int id = resultSet.getInt(1);
						company.setId(id); // Add the new created id into the company object.
					}
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
		} else throw new DataAlreadyExistExeption("Sorry, company with email: " + company.getEmail() + " already exists");
		
	}
	// ----------------UPDATE--------------------------

	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#updateCompany(JavaBeans.Company, java.lang.String, java.lang.String)
	 */
	public void updateCompany(Company company, String oldEmail, String oldPassword) throws Exception {
		if (isCouponOwnerExists(oldEmail, oldPassword, tableNameCompanies)) {
			if (company.getId()!=0) {
			Connection connection = null;

			try {

				connection = connectionPool.getConnection();

				String sql = String.format("UPDATE COMPANIES SET EMAIL='%s', PASSWORD='%s' WHERE ID=%d",
						company.getEmail(), company.getPassword(), company.getId());

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.executeUpdate();
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
			}else throw new NoIDException("The companies's ID ="+company.getId()+". ");
		} else throw new DataDoesNotExist("The company with email: "+oldEmail+" and/or password: "+oldPassword+" doesn't exist");
		
	}

//---------------------DELETE---------------------
	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#deleteCompany(int)
	 */
	public void deleteCompany(int companyID) throws SQLException, Exception {
		if (isIDExists(companyID, tableNameCompanies)) {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();
			String sql = String.format("DELETE FROM COMPANIES WHERE ID=%d", companyID);
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		}else throw new DataDoesNotExist("You've entered id: "+companyID);
	}

	// ----------------GET ALL COMPANIES---------------------
	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#getAllCompanies()
	 */
	public ArrayList<Company> getAllCompanies() throws SQLException, Exception {

		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM COMPANIES";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Company> allCompanies = new ArrayList<Company>();

					while (resultSet.next()) {

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
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	// ----------------GET ONE COMPANY---------------------

	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#getOneCompany(int)
	 */
	public Company getOneCompany(int companyID) throws SQLException, Exception {
		if (isIDExists(companyID, tableNameCompanies)) {
			Connection connection = null;

			try {
				connection = connectionPool.getConnection();

				String sql = String.format("SELECT * FROM COMPANIES WHERE ID=%d", companyID);

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

					try (ResultSet resultSet = preparedStatement.executeQuery()) {

						resultSet.next();

						String name = resultSet.getString("NAME");
						String email = resultSet.getString("EMAIL");
						String password = resultSet.getString("PASSWORD");
						ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCompanyID(companyID);

						Company company = new Company(name, companyID, email, password, coupons);

						return company;
					}
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
		} else
			throw new DataDoesNotExist("You've entered ID: " + companyID);
	}
	//-------------------GET COMPANY BY EMAIL----------------
	/* (non-Javadoc)
	 * @see DBDAO.CompaniesDAO#getCompanyByEmail(java.lang.String)
	 */
	public Company getCompanyByEmail(String email) throws SQLException, Exception {

			Connection connection = null;

			try {
				connection = connectionPool.getConnection();

				String sql = String.format("SELECT * FROM COMPANIES WHERE EMAIL='%s'", email);

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

					try (ResultSet resultSet = preparedStatement.executeQuery()) {

						resultSet.next();

						String name = resultSet.getString("NAME");
						int id = resultSet.getInt("ID");
						String password = resultSet.getString("PASSWORD");
						ArrayList<Coupon> coupons = new CouponsDBDAO().getCouponsByCompanyID(id);

						Company company = new Company(name, id, email, password, coupons);

						return company;
					}
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}

	}

}
