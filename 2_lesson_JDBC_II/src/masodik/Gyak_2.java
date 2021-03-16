package masodik;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;


public class Gyak_2 {

	public static void main(String[] args) {
		try {
		driverReg();
		Connection conn = createConnection();
		//getMeta(conn);
		//createGyar(conn);
		//fillTulaj2(conn);		
		//getOlderTulaj(conn,getIntFromConsole());
		//getSpecifiedTulaj(conn,1,"Alma",20);
		//update(conn,"Alma","Janos");
		//getAllTulaj2(conn);
		//createDateTest(conn);
		fillDateTest(conn);
		getAllDateTest(conn);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
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
	
	public static void getMeta(Connection conn)throws SQLException{
		DatabaseMetaData dmeta = conn.getMetaData();	
		String[] array= {"TABLE"};
		ResultSet rs = dmeta.getTables(null, null, "%", array);	
		System.out.println(dmeta.getDriverName());
		
		while(rs.next()) {
		 System.out.println(rs.getString(3));
		}	
	}
	
	public static void createTulaj(Connection conn) throws SQLException{
		//conn.createStatement().execute("create table gyar ( id number(3) primary key, name varchar(100), size number(3))");
		conn.createStatement().execute("create table tulaj ("
				+ "id number(3) primary key,"
				+ "nev varchar(40) not null,"
				+ "kor int not null"
				+ ")");
	}
	
	public static void fillTulaj(Connection conn) throws SQLException{
		PreparedStatement prstmt = conn.prepareStatement("insert into tulaj values(?,?,?)");
		
		prstmt.setInt(1, 1);
		prstmt.setString(2, "Alma");
		prstmt.setInt(3, 30);
		prstmt.executeUpdate();
		
		prstmt.setInt(1, 2);
		prstmt.setString(2, "Korte");
		prstmt.setInt(3, 40);
		prstmt.executeUpdate();

	}
	
	public static void getAllTulaj(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("Select * from tulaj");
		
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		}
	}
	
	public static void fillTulaj2(Connection conn) throws SQLException{
		PreparedStatement prstmt = conn.prepareStatement("insert into tulaj values(?,?,?)");
		prstmt.setInt(1, 5);
		prstmt.setString(2, "Szilva");
		prstmt.setInt(3, 20);
		prstmt.addBatch();
		
		prstmt.setInt(1, 6);
		prstmt.setString(2, "Szolo");
		prstmt.setInt(3, 10);
		prstmt.addBatch();
		
		
		int[] num =prstmt.executeBatch();
		System.out.println(num[0]+" "+num[1]);	
	}
	
	public static void getAllTulaj2(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("Select * from tulaj");
		
		ResultSet rs = prstmt.executeQuery();
		ResultSetMetaData rsmeta= rs.getMetaData();
		System.out.println(rsmeta.getColumnName(1)+" "+rsmeta.getColumnName(2)+" "+rsmeta.getColumnName(3));
		System.out.println(rsmeta.getColumnTypeName(1)+"-"+rsmeta.getColumnTypeName(2)+"-"+rsmeta.getColumnTypeName(3));
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		}
	}
	
	public static void getOlderTulaj(Connection conn, int age) throws SQLException {
		PreparedStatement oldertulaj = conn.prepareStatement("select * from tulaj where kor>?");
		
		oldertulaj.setInt(1, age);
		ResultSet rs = oldertulaj.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		}
	}
	
	public static int getIntFromConsole() {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		return scanner.nextInt();
	}
	public static void getSpecifiedTulaj(Connection conn, int id, String name, int age) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("select * from tulaj where id=? or nev like ? or kor=?");
		
		prstmt.setInt(1, id);
		prstmt.setString(2, name);
		prstmt.setInt(3, age);
		
		ResultSet rs = prstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1)+rs.getString(2)+rs.getInt(3));
		}
	}
	
	public static void update(Connection conn, String searchname, String newname) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("update tulaj set nev=? where nev=?");
		
		prstmt.setString(1, newname);
		prstmt.setString(2, searchname);
		
		System.out.println("Updatelt rekordok száma: "+prstmt.executeUpdate());
	}
	

	public static void createDateTest(Connection conn) throws SQLException{
		PreparedStatement prstmt = conn.prepareStatement("Create table test ( id int primary key, datum date )");
		prstmt.executeUpdate();
		
	}
	
	public static void fillDateTest(Connection conn) throws SQLException{
		PreparedStatement prstmt = conn.prepareStatement("Insert into test values(?,?)");
		
		prstmt.setInt(1, 1);
		prstmt.setDate(2, Date.valueOf(LocalDate.now()));
		prstmt.addBatch();
		
		prstmt.setInt(1, 2);
		prstmt.setDate(2, Date.valueOf(LocalDate.now().plusDays(1)));
		prstmt.addBatch();
		
		prstmt.setInt(1, 3);
		prstmt.setDate(2, Date.valueOf(LocalDate.now().plusDays(2)));
		prstmt.addBatch();
		
		prstmt.setInt(1, 4);
		prstmt.setDate(2, Date.valueOf(LocalDate.now()));
		prstmt.addBatch();
		
		prstmt.executeBatch();
	}
	
	public static void getAllDateTest(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("Select * from test");
		
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getDate(2));
		}
	}
}
