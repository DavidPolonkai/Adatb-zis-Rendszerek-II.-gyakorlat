package runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Run {
	private static final String URL="jdbc:oracle:thin:@193.6.5.58:1521:XE";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			
			Connection conn = connect("H22_GPNWZT","GPNWZT");
			//createTable(conn);
			//insertCar(conn);
			setPriceOfCarByColor(conn,"zöld",800);
			setPriceOfCarByColorPrep(conn,"piros",1000);
			String[] sqlString =  {"insert into car values(10,'Opel','fehér',300)",
			                      "insert into car values(11,'Seat','zöld',700)",
			                      "insert into car values(12,'Opel','fehér',200)"};
			insertMultipleCar(conn,sqlString);
			System.out.println("End of program");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection connect(
			String username, String password) 
					throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL, 
				username, password);
		
		return conn;
		
	}
	
	public static void createTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE CAR ("
				+ "id number(4) primary key, "
				+ "manufacturer varchar2(200) not null, "
				+ "color varchar2(20) not null, "
				+ "price number(5) not null "
				+ ")");
		
		
	}
	
	public static void insertCar(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		
		System.out.println("Insert returned: " + stmt.executeUpdate(""
				+ "insert into car values(3,'Skoda','piros',600) "));
	}
	
	public static void setPriceOfCarByColor(Connection conn, String color, int price) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update car set price="+price+" where color='"+color+"'");
	}
	
	public static void setPriceOfCarByColorPrep(Connection conn, String color, int price) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(
				"update car set price=? where color=?");
		prstmt.setInt(1, price);
		prstmt.setString(2, color);
		
	}
	
	public static void insertMultipleCar(Connection conn, String[] insertSql) throws SQLException {
		Statement stmt = conn.createStatement();
		for (String sql:insertSql) {
			stmt.addBatch(sql);
		}
		System.out.println(stmt.executeBatch());
		
	}
	

}
