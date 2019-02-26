package DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {

	public static ConnectionPool instance = new ConnectionPool();

	private Stack<Connection> connections = new Stack<>();

	private static final String connectionString = "jdbc:mysql://localhost:3306/coupon_management";
	// private static String USERNAME = "root";
	private static String PASSWORD = null;

	private ConnectionPool() {
		for (int i = 1; i < 10; i++) {
			try {
				Connection conn = DriverManager.getConnection(connectionString, "root", PASSWORD);
				connections.push(conn);

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		}
	}

	public Connection getConnection() throws InterruptedException {

		synchronized (connections) {

			if (connections.isEmpty()) {
				connections.wait();
			}

			return connections.pop();
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public void restoreConnection(Connection conn) {
		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	public void closeAllConnections() throws InterruptedException {
		synchronized (connections) {
			while (connections.size() > 10) {
				connections.wait();

			}
			for (Connection conn : connections) {
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}