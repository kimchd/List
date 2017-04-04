package org.zerock.persistence;

import org.apache.commons.dbcp.BasicDataSource;

public abstract class AbstractDAO {

	protected BasicDataSource dataSource;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected AbstractDAO() throws Exception { // 생성자 필요
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@192.168.0.218:1521:XE");
		dataSource.setUsername("user00");
		dataSource.setPassword("user00");
		dataSource.setInitialSize(10); // 10개의 커넥션이 맺어짐
		dataSource.setMinIdle(5);

	}

}
