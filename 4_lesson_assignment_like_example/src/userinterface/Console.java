package userinterface;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import database.Managemant;
import models.Car;
import models.Person;

public class Console {
	private static final Scanner scanner = new Scanner(System.in);
	private static Managemant managemant;

	public static void main(String[] args) {
		writer("Adja meg a felhasznalo nevet: ");
		String username = reader();
		writer("Adja meg a jelszot: ");
		
		String password = reader();
		try {
			managemant = new Managemant(username,password);
			menu();
			managemant.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static Person personParser() {
		writer("Adja meg a person adatait: ");
		writer("name: ");
		String name =reader();
		writer("birth: ");
		Date birth = Date.valueOf(reader());
		return new Person(-1,name,birth);
	}
	
	private static String reader() {		
		return scanner.nextLine(); 
	}
	
	private static void writer(String text) {
		System.out.println(text);
	}
	
	private static ArrayList<Car> personsCars(String name) throws SQLException {	
		ArrayList<models.Car> cars= managemant.getCarsByPerson(managemant.getPersonByName(name));
		return cars;
		
	}
	
	private static void writeCars(ArrayList<Car> cars) {
		for(Car car : cars) {
			System.out.println(car.getPerson().getName()+": "+car.getColor());
		}
	}
	
	private static void writeAllTables(ArrayList<ArrayList<String>> result) {
		for(ArrayList<String> record : result) {
			for(String data: record) {
				System.out.print(data+" ");
			}
			System.out.println();
		}
	}
	
	private static void menu() throws Exception {
		boolean isRunning = true;
		while(isRunning) {
		writer("Válassza a lehetőségek közül: ");
		writer("1: insert person");
		writer("2: persons car");
		writer("3: delete personbyid");
		writer("4: AllTables");
		writer("5: TableNames");
		writer("6: Callable");

		switch (reader()) {
		case "1":
				managemant.insertPerson(personParser());
				break;
		case "2": 
				writeCars(personsCars(reader()));
				break;
		case "3":{
				writer("Adja meg az id-t");
				managemant.deletePersonById(reader());
		}
			break;
		case "4":writeAllTables(managemant.selectPersonAndCars());
			break;
		case "5":writeTableNames(managemant.getTableNames());
			break;
			
		case "6":{
			writer("Adja meg a fib értékét");
			writer(String.valueOf(managemant.callableFibf(Integer.parseInt(reader()))));
		}
			break;
		default: isRunning = false;
		}
		}
	}

	private static void writeTableNames(ArrayList<String> tableNames) {
		for(String name: tableNames) {
			System.out.print(name+", ");
		}
	}
	
	
	
	
	

}
