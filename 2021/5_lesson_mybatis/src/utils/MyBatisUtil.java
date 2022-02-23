package utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sqlSessionFactory;
	private static final String CONFIG_FILE_LOCATION="resources/mybatis-config.xml";
	
	public MyBatisUtil() throws IOException {
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(CONFIG_FILE_LOCATION);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
