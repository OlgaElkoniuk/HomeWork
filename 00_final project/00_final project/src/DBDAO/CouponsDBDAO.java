package DBDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import Exeptions.DataAlreadyExistExeption;
import Exeptions.DataDoesNotExist;
import Exeptions.NoIDException;
//import java.sql.Date;

import JavaBeans.Categories;
import JavaBeans.Coupon;

public class CouponsDBDAO extends BasicDBDAO implements CouponsDAO {
	
	//private ConnectionPool connectionPool = ConnectionPool.getInstance();

	// ------------------ADD------------------
	@Override
	public void addCoupon(Coupon coupon) throws SQLException, Exception {
		if (isCouponExists(coupon.getTitle(), coupon.getDescription()) == false) {
			if(isIDExists(coupon.getCompany_id(), tableNameCompanies)&&isIDExists(coupon.getCategoryId(), tableNameCategories)) {
			Connection connection = null;

			try {

				connection = connectionPool.getConnection();

				String sql = String.format(
						"INSERT INTO coupons(COMPANY_ID, TITLE, DESCRIPTION, IMAGE, AMOUNT, PRICE, START_DATE, END_DATE, CATEGORY_ID) "
								+ "VALUES(%d,'%s', '%s', '%s', %d, '%f','%s','%s', %d)",
						coupon.getCompany_id(), coupon.getTitle(), coupon.getDescription(), coupon.getImage(),
						coupon.getAmount(), coupon.getPrice(), coupon.getStartDate(), coupon.getEndDate(),
						coupon.getCategoryId());

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

					preparedStatement.executeUpdate();

					try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
						resultSet.next();
						int id = resultSet.getInt(1);
						coupon.setCouponId(id);
					}
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
			}else throw new DataDoesNotExist("You've entered company ID: "+coupon.getCompany_id()+"You've entered company ID: "+coupon.getCategoryId());
		} else throw new DataAlreadyExistExeption("Sorry, the coupon: " 
			+ coupon.getTitle() + ", " + coupon.getDescription() + " already exists");
	}

	// ------------------UPDATE------------------
	@Override
	public void updateCoupon(Coupon coupon) throws SQLException, Exception {
		if (isIDExists(coupon.getId(), tableNameCoupons)) {

			Connection connection = null;

			try {

				connection = connectionPool.getConnection();

				String sql = String.format(
						"UPDATE coupons SET TITLE='%s', DESCRIPTION='%s', IMAGE='%s', AMOUNT=%d, PRICE=%f, START_DATE='%s', END_DATE='%s', CATEGORY_ID=%d WHERE ID=%d",
						coupon.getTitle(), coupon.getDescription(), coupon.getImage(), coupon.getAmount(),
						coupon.getPrice(), coupon.getStartDate(), coupon.getEndDate(), coupon.getCategoryId(),
						coupon.getId());

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.executeUpdate();
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
			}else throw new NoIDException("The customer's ID ="+coupon.getId()+". ");
	}

	// ------------------GET ALL COUPONS------------------
	@Override
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM coupons";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();

					while (resultSet.next()) {
						int company_id = resultSet.getInt("COMPANY_ID");
						int coupon_id = resultSet.getInt("ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String startDateString = resultSet.getString("START_DATE");
						String endDateString = resultSet.getString("END_DATE");
						int category_id = resultSet.getInt("CATEGORY_ID");
						Categories category=Categories.values()[category_id-1];
						Date startDate = Coupon.sdf.parse(startDateString);
						Date endDate = Coupon.sdf.parse(endDateString);

						Coupon coupon = new Coupon(company_id, coupon_id, category, title, description, startDate,
								endDate, amount, price, image);

						allCoupons.add(coupon);
					}

					return allCoupons;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	// ------------------GET ONE COUPON------------------
	@Override
	public Coupon getOneCoupon(int couponID) throws Exception {
		if (isIDExists(couponID, tableNameCoupons)) {
			Connection connection = null;

			try {
				connection = connectionPool.getConnection();

				String sql = String.format("SELECT * FROM coupons WHERE ID=%d", couponID);

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

					try (ResultSet resultSet = preparedStatement.executeQuery()) {

						resultSet.next();
						couponID = resultSet.getInt("ID");
						int company_id = resultSet.getInt("COMPANY_ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String startDateString = resultSet.getString("START_DATE");
						String endDateString = resultSet.getString("END_DATE");
						int category_id = resultSet.getInt("CATEGORY_ID");
						Categories category=Categories.values()[category_id-1];
						Date startDate = Coupon.sdf.parse(startDateString);
						Date endDate = Coupon.sdf.parse(endDateString);
						Coupon coupon = new Coupon(company_id, couponID, category, title, description, startDate,
								endDate, amount, price, image);
						return coupon;
					}
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
		} else throw new DataDoesNotExist("You've entered ID: " + couponID);
	}

	// ------------------CHECK IF COUPON EXISTS------------------
	@Override
	public boolean isCouponExists(String title, String description) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM coupons WHERE TITLE = '%s' AND DESCRIPTION = '%s'", title,
					description);

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
	// ------------------DELETE COUPON------------------

	@Override
	public void deleteCoupon(int couponID) throws Exception {
		if (isIDExists(couponID, tableNameCoupons)) {
			Connection connection = null;

			try {

				connection = connectionPool.getConnection();

				String sql = String.format("DELETE FROM coupons WHERE ID=%d", couponID);

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.executeUpdate();
				}
			} finally {
				connectionPool.restoreConnection(connection);
			}
		} else
			throw new DataDoesNotExist("You've entered: " + couponID);
	}
	// ------------------GET COUPONS BY COMPANY ID------------------

	/* (non-Javadoc)
	 * @see DBDAO.CouponsDAO#getCouponsByCompanyID(int)
	 */
	@Override
	public ArrayList<Coupon> getCouponsByCompanyID(int companyID) throws Exception {
		if (isIDExists(companyID, tableNameCompanies)) {
		Connection connection = null;
		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM coupons WHERE company_id=%d", companyID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();

					while (resultSet.next()) {
						int coupon_id = resultSet.getInt("ID");
						int company_id = resultSet.getInt("COMPANY_ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String startDateString = resultSet.getString("START_DATE");
						String endDateString = resultSet.getString("END_DATE");
						int category_id = resultSet.getInt("CATEGORY_ID");
						Categories category=Categories.values()[category_id-1];
						Date startDate = Coupon.sdf.parse(startDateString);
						Date endDate = Coupon.sdf.parse(endDateString);

						Coupon coupon = new Coupon(company_id, coupon_id, category, title, description, startDate,
								endDate, amount, price, image);

						allCoupons.add(coupon);
					}

					return allCoupons;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		}throw new DataDoesNotExist("You've entered ID: " + companyID);
	}

	// ------------------GET COUPONS BY CUSTOMER ID------------------
	/* (non-Javadoc)
	 * @see DBDAO.CouponsDAO#getCouponsByCustomerID(int)
	 */
	@Override
	public ArrayList<Coupon> getCouponsByCustomerID(int customerID) throws Exception {
		if (isIDExists(customerID, tableNameCustomers)) {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM coupons INNER JOIN customers_vs_coupons ON coupons.id=customers_vs_coupons.COUPON_ID and customer_id=%d", customerID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();

					while (resultSet.next()) {
						int company_id = resultSet.getInt("COMPANY_ID");
						int coupon_id = resultSet.getInt("ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String startDateString = resultSet.getString("START_DATE");
						String endDateString = resultSet.getString("END_DATE");
						int category_id = resultSet.getInt("CATEGORY_ID");
						Categories category=Categories.values()[category_id-1];
						Date startDate = Coupon.sdf.parse(startDateString);
						Date endDate = Coupon.sdf.parse(endDateString);

						Coupon coupon = new Coupon(company_id, coupon_id, category, title, description, startDate,
								endDate, amount, price, image);

						allCoupons.add(coupon);
					}

					return allCoupons;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		}else throw new DataDoesNotExist("You've entered customer id: "+customerID);
	}

	// ------------------ADD COUPON PURCHASE------------------
	/* (non-Javadoc)
	 * @see DBDAO.CouponsDAO#addCouponPurchase(int, int)
	 */
	@Override
	public void addCouponPurchase(int customerID, int couponID) throws Exception {
		if (isIDExists(couponID, tableNameCoupons)&&isIDExists(customerID, tableNameCustomers)) {
			
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO customers_vs_coupons(COUPON_ID, CUSTOMER_ID) " + "VALUES(%d, %d)",
					couponID, customerID);
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}

		} finally {
			connectionPool.restoreConnection(connection);
		}
		}
		else throw new DataDoesNotExist("You've entered: customer ID - "+customerID+" and coupon ID "+couponID);
	}

	// ------------------DELETE COUPON PURCHASE------------------
	/* (non-Javadoc)
	 * @see DBDAO.CouponsDAO#deleteCouponPurchase(int, int)
	 */
	@Override
	public void deleteCouponPurchase(int customerID, int couponID) throws Exception {
		if (isIDExists(couponID, tableNameCoupons)&&isIDExists(customerID, tableNameCustomers)) {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM customers_vs_coupons WHERE COUPON_ID=%d AND CUSTOMER_ID=%d",
					couponID, customerID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		}
		else throw new DataDoesNotExist("You've entered: customer ID - "+customerID+" and coupon ID "+couponID);
	}

}
