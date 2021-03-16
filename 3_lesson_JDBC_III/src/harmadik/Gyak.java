package harmadik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Gyak {
	
	
	public static void main(String[] args) {
		driverReg();
		Connection conn = createConnection();
		//updateToDate(conn);
		updateEscapeSequence(conn);
		System.out.println("Vége");
	}
	
	public static void driverReg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sikeres driver reg\n");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection createConnection() {
		String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
		String user = "H20_GPNWZT";
		String password = "H20_GPNWZT";
		Connection conn;
		try {
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("Sikeres csatlakozás");
			return conn;
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		System.exit(-1);
		return null;
	}
	
	public static void updateToDate(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("update test set datum = TO_DATE(?,?) where id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateEscapeSequence(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("update test set datum = {d '1992-03-03'} where id = 2");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
