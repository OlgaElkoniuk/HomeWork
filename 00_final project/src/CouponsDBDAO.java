import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


public class CouponsDBDAO implements CouponsDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	//------------------ADD------------------
	@Override
	public void addCoupon(Coupon coupon) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO coupons(TITLE, DESCRIPTION, IMAGE, AMOUNT, PRICE, STARTDATE, ENDDATE, CATEGORY) " + 
					"VALUES('%s', '%s', '%s', %d, %f, %t, %t, '%s')",
					coupon.getTitle(), coupon.getDescription(), coupon.getImage(), 
					coupon.getAmount(), coupon.getPrice(), coupon.getStartDate(), 
					coupon.getEndDate(), coupon.getCategory().toString());

			try(PreparedStatement preparedStatement = 
					connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					coupon.setId(id); 
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}

	}
	//------------------UPDATE------------------
	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format(
					"UPDATE coupons SET TITLE='%s', DESCRIPTION='%s', IMAGE='%s', AMOUNT=%d, PRICE=%f, STARTDATE=%t, ENDDATE=%t, CATEGORY='%s' WHERE ID=%d",
					coupon.getTitle(), coupon.getDescription(), coupon.getImage(), 
					coupon.getAmount(), coupon.getPrice(), coupon.getStartDate(), 
					coupon.getEndDate(), coupon.getCategory().toString(), coupon.getId());

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}

	}
	//------------------GET ALL COUPONS------------------
	@Override
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM coupons";

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
					
					while(resultSet.next()) {

						int id = resultSet.getInt("ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						Date startDate = resultSet.getTime("STARTDATE");
						Date endDate = resultSet.getTime("ENDDATE");
						Categories category = (Categories) resultSet.getObject("CATEGORY");
						
	
						Coupon coupon = new Coupon(id, category, title, description, startDate, 
								endDate, amount, price, image);
						
						allCoupons.add(coupon);
					}
					
					return allCoupons;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	//------------------GET ONE COUPON------------------
	@Override
	public Coupon getOneCoupon(int couponID) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM coupons WHERE ID=%d", couponID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.next();

					String title = resultSet.getString("TITLE");
					String description = resultSet.getString("DESCRIPTION");
					String image = resultSet.getString("IMAGE");
					int amount = resultSet.getInt("AMOUNT");
					double price = resultSet.getDouble("PRICE");
					Date startDate = resultSet.getTime("STARTDATE");
					Date endDate = resultSet.getTime("ENDDATE");
					Categories category = (Categories) resultSet.getObject("CATEGORY");
					

					Coupon coupon = new Coupon(couponID, category, title, description, startDate, 
							endDate, amount, price, image);
					return coupon;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	//------------------CHECK IF COUPON EXISTS------------------
	@Override
	public boolean isCouponExists(String title, String description) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM coupons WHERE TITLE = '%s' AND DESCRIPTION = '%s'",
					title,description);

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
	//------------------DELETE------------------

	@Override
	public void deleteCoupon(int couponID) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM coupons WHERE ID=%d", couponID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	//------------------GET COUPONS BY COMPANY ID------------------

	@Override
	public ArrayList<Coupon> getCouponsByCompanyID(int companyID) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM coupons WHERE ID=%d", companyID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
					
					while(resultSet.next()) {

						int id = resultSet.getInt("ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						Date startDate = resultSet.getTime("STARTDATE");
						Date endDate = resultSet.getTime("ENDDATE");
						Categories category = (Categories) resultSet.getObject("CATEGORY");
						
	
						Coupon coupon = new Coupon(id, category, title, description, startDate, 
								endDate, amount, price, image);
						
						allCoupons.add(coupon);
					}
					
					return allCoupons;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}
	@Override
	public ArrayList<Coupon> getCouponsByCustomerID(int customerID) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format(
					"SELECT Count(*) AS Count FROM coupons WHERE ID=%d", customerID);

			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try(ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
					
					while(resultSet.next()) {

						int id = resultSet.getInt("ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						String image = resultSet.getString("IMAGE");
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						Date startDate = resultSet.getTime("STARTDATE");
						Date endDate = resultSet.getTime("ENDDATE");
						Categories category = (Categories) resultSet.getObject("CATEGORY");
						
	
						Coupon coupon = new Coupon(id, category, title, description, startDate, 
								endDate, amount, price, image);
						
						allCoupons.add(coupon);
					}
					
					return allCoupons;
				}
			}
		}
		finally {
			connectionPool.restoreConnection(connection);
		}
	}

}
