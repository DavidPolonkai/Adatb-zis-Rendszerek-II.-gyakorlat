package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Person;
import models.Car;

public class Managemant {
	private final Connection conn;
	private static final String url="jdbc:oracle:thin:@193.6.5.58:1521:XE";
	private static final String classname="oracle.jdbc.driver.OracleDriver";
	
	private static final String insertPersonSQL="insert into person values(?,?,?)";
	private static final String selectMaxPersonIdSQL="select max(id) from person";
	private static final String getCarsByPersonSQL="select * from car where person = ?";
	private static final String getPersonByNameSQL="select * from person where name = ?";
	
	public Managemant(String username, String password) throws Exception {
		driverReg();
		this.conn = createConnection(username,password); 
	}
	
	private void driverReg() throws ClassNotFoundException {
			Class.forName(classname);
	}
	
	private Connection createConnection(String username, String password) throws SQLException {	
		Connection conn;
			conn = DriverManager.getConnection(url,username,password);
			return conn;
	}
	
	public void insertPerson(Person person) throws Exception {
		PreparedStatement prstmt = this.conn.prepareStatement(insertPersonSQL);
		person.setId(getMaxPersonId()+1);
		prstmt.setInt(1, person.getId());
		prstmt.setString(2, person.getName());
		prstmt.setDate(3, person.getBirth());
		
		if(prstmt.execute()) new Exception("Not successful");
	}
	
	private int getMaxPersonId() {
		int returnValue=1;
		try {
		ResultSet rs =this.conn.createStatement().executeQuery(selectMaxPersonIdSQL);
		rs.next();
		returnValue = rs.getInt(1);
		}catch(SQLException e) {}
		return returnValue;
	}
	
	public ArrayList<Car> getCarsByPerson(Person person) throws SQLException{
		ArrayList<Car> result = new ArrayList<Car>();
		try {
		PreparedStatement prstmt = this.conn.prepareStatement(getCarsByPersonSQL);
		
		prstmt.setInt(1, person.getId());
		
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next()) {
			result.add(new Car(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getInt(4),person));
		}
		}catch (SQLException e) {
			return null;
		}
		return result;
	}
	
	public Person getPersonByName(String name){
		try {
		PreparedStatement prstmt = this.conn.prepareStatement(getPersonByNameSQL);
		prstmt.setString(1, name);
		ResultSet rs = prstmt.executeQuery();
		rs.next();
		return new Person(rs.getInt(1),rs.getString(2),rs.getDate(3));
		
		}catch(SQLException e) {	}
		
		return null;
		
	}
	
}
