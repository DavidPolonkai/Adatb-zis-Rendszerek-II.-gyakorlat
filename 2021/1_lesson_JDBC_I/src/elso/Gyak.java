package elso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Gyak {

	public static void main(String[] args) {
		Connection conn;
		
		driverReg();
		conn=connect();
		//TableCreation(conn);
		//InsertIntoTables(conn);
		getUser(conn);
		//getAllCar(conn);
		//getRedCar(conn);
		deleteZoldCar(conn);
		getCarsByColor(conn,"zold");
		disconnect(conn);
	}
	
	public static void driverReg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sikeres driver reg\n");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection connect() {
		String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
		String user = "H21_XXXXXX";//neptunkód végig nagy betűvel
		String password = "H21_XXXXXX";
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
	
	public static void disconnect(Connection conn) {
		try {
		conn.close();
		System.out.println("Sikeres lecsatlakozás");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void TableCreation(Connection conn) {
		String sql1 = "create table auto ("
				+ " rsz char(6) primary key,"
				+ " tipus char(10) not null,"
				+ " szin char(10),"
				+ " evjarat number(4),"
				+ " ar number(8)"
				+ ")";
		
		String sql2 = "create table tulaj ("
				+ "id number(3) primary key,"
				+ "nev varchar(40) not null,"
				+ "kor int not null"
				+ ")";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql1);
			System.out.println("Autó létrehozva");
			stmt.executeUpdate(sql2);
			System.out.println("Tulaj létrehozva");
			stmt.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public static void InsertIntoTables(Connection conn) {
		String sql1[]= {
				"insert into auto values('qwe123','astra','zold',1995,30000)",
				"insert into auto values('asd432','zafira','piros',2003,70000)",
				"insert into auto values('asd412','astra','piros',2000,40000)",
		};
		String sql2="insert into tulaj values(1,'Pista',30)";
		try {
			Statement stmt =conn.createStatement();
			stmt.executeUpdate(sql2);
			System.out.println("ugyesek vagyunk");
			/*for (int i=0;i<3;i++) {
				stmt.executeUpdate(sql1[i]);
			}*/
			for (String sql:sql1) {
				stmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void getUser(Connection conn) {
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("select user from dual");
			rs.next();
			System.out.println(rs.getString(1));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void getAllCar(Connection conn) {
		try {
			String sql="select * from auto";
			//ArrayList<Car> autok = new ArrayList<Car>(); 
			
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Car car=new Car(rs.getString("rsz"),rs.getString("tipus"),rs.getString("szin"),rs.getInt("evjarat"),rs.getInt("ar"));
				//autok.add(car);
				System.out.println(car);
			}
			//System.out.println(autok);
			rs.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void getRedCar(Connection conn) {
		String sql="select rsz,tipus,szin from auto where szin='piros'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Car car=new Car(rs.getString(1),rs.getString(2),rs.getString(3));
				System.out.println(car);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void getCarsByColor(Connection conn,String color) {
		String sql="select rsz,tipus,szin from auto where szin='"+color+"'";
		System.out.println(sql);
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Car car=new Car(rs.getString(1),rs.getString(2),rs.getString(3));
				System.out.println(car);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void deleteZoldCar(Connection conn) {
		String sql="delete from auto where szin like 'zold'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
