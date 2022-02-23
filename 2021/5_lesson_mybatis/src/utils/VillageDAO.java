package utils;

import org.apache.ibatis.session.SqlSession;

import models.Village;

public class VillageDAO {
	
	public void save(Village village) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("resources.VillageMapper.insertVillage",village);
		session.commit();
		session.close();
	}
	
	public void update(Village village) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.update("resources.VillageMapper.updateVillage",village);
		session.commit();
		session.close();
	}
	
	public Village getData(int id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Village village =session.selectOne("resources.VillageMapper.selectVillage",id);
		session.close();
		return village;
	}
	
	public void delete(int id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.delete("resources.VillageMapper.deleteVillage",id);
		session.commit();
		session.close();
	}
}
