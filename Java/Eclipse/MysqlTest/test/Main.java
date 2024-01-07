import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			String username = "root";
			String password = "Parham13730321";
			String url = "jdbc:mysql://192.168.1.100/test";
			System.out.println("Waiting ...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection success...");
			Statement statement;
			ResultSet result;
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM books");
			while (result.next()) {
				System.out.println("Column 1 : " + result.getString(1));
				System.out.println("Column 2 : " + result.getString(2));
				System.out.println("Column 3 : " + result.getString(3));
			}
			result.close();
			statement.executeUpdate("INSERT INTO books(isbn) VALUES(\"0\")");
			System.out.println("write complete ...");
			result = statement.executeQuery("SELECT * FROM books");
			while (result.next()) {
				System.out.println("Column 1 : " + result.getString(1));
				System.out.println("Column 2 : " + result.getString(2));
				System.out.println("Column 3 : " + result.getString(3));
			}
			result.close();
		} catch (Exception e) {
			System.err.println("Something Wrong ..");
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
					System.out.println("Close is success...");
				} catch (SQLException e) {
					System.err.println("Close is .... Yes ...");
					e.printStackTrace();
				}
			}
		}
	}
}
