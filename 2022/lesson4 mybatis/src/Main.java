import java.io.IOException;

public class Main {
	private static final CarDAO carDAO = new CarDAO();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new MyBatisUtil();
			System.out.println("connected");
			
			//insertCar(new Car(30,"Suzuki","zöld",200,1));
			//deleteCar(30);
			updateCar(new Car(12,"Suzuki","sárga",100));
			selectAllCar();
			System.out.println("Success");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void insertCar(Car car) {
		carDAO.insert(car);
	}
	
	private static void selectAllCar() {
		for (Car car:carDAO.selectAll()) {
			System.out.println(car);
		}
	}
	
	private static void deleteCar(int id) {
		carDAO.deleteById(id);
	}
	
	private static void updateCar(Car car) {
		carDAO.updateById(car);
	}
	
	

}
