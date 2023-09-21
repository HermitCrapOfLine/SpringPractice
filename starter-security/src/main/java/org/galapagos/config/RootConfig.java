package org.galapagos.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // 설정 파일이니 분석해줘 스프링 !
@ComponentScan(basePackages = { "org.galapagos.service" })
@MapperScan(basePackages = { "org.galapagos.mapper" })
public class RootConfig {

	@Autowired
	ApplicationContext applicationContext;
	
	
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://localhost:3307/glory_db");
		
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3307/glory_db");
		
		config.setUsername("GLORY");
		config.setPassword("1234");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	// MyBatis 핵심 설정
	 @Bean
	    public SqlSessionFactory sqlSessionFactory() throws Exception {
	        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	        
	        sqlSessionFactory.setConfigLocation(
	                applicationContext.getResource(
	                     "classpath:/mybatis-config.xml"));
	        
	        sqlSessionFactory.setDataSource(dataSource()); //메소드 호출이 아니라, dataSource()가 리턴한 Bean을 달라는 의미
	        return (SqlSessionFactory) sqlSessionFactory.getObject();
	    }
}
