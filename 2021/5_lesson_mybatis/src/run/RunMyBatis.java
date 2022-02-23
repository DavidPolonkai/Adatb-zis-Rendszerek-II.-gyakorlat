package run;

import utils.MyBatisUtil;
import utils.VillageDAO;
import java.io.IOException;

import models.Village;

public class RunMyBatis {
	private static final VillageDAO villageDAO = new VillageDAO();

	static {
		try {	
			new MyBatisUtil();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
			//insert();
			//update();
			select(1);
			//delete(1);
	}
	
	private static void insert() {
		Village village = new Village(2,"Ózd","Borsod");
		villageDAO.save(village);
		System.out.println("saved");
	}
	
	private static void update() {
		villageDAO.update(new Village(2,"Budapest","Pest"));
		System.out.println("updated");
	}
	
	private static void select(int id) {
		Village village = villageDAO.getData(id);
		
		System.out.println(village);
	}
	
	private static void delete(int id) {
		villageDAO.delete(id);
		System.out.println(id+" has been deleted");
	}
	

}
