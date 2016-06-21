package com.cyb.tms.init;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.cyb.tms.*" })
@PropertySource({ 
	"classpath:hibernate.properties",
	"classpath:application.properties"
})
public class HibernateConfig {
	
	@Value("${database.driverClass}")
	private String PROPERTY_NAME_DATABASE_DRIVER;// = "db.driver";
	@Value("${database.password}")
    private String PROPERTY_NAME_DATABASE_PASSWORD;// = "db.password";
	@Value("${database.url}")
    private String PROPERTY_NAME_DATABASE_URL;// = "db.url";
	@Value("${database.username}")
    private String PROPERTY_NAME_DATABASE_USERNAME;// = "db.username";
	@Value("${hibernate.dialect}")
    private String PROPERTY_NAME_HIBERNATE_DIALECT;// = "hibernate.dialect";
	@Value("${hibernate.show_sql}")
    private String PROPERTY_NAME_HIBERNATE_SHOW_SQL;// = "hibernate.show_sql";
	@Value("${hibernate.hbm2ddl.auto}")
	private String PROPERTY_HIBERNATE_HBM2DDL_AUTO;
	@Value("${hibernate.format_sql}")
	private String PROPERTY_HIBERNATE_FORMAT_SQL;
	@Value("${entitymanager.packages.to.scan}")
    private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;// = "entitymanager.packages.to.scan";
	@Value("${hibernate.jdbc.batch_size}")
	private int PROPERTY_JDBC_BATCH_SIZE;
	@Value("${connection.acquireIncrement}")
	private int CONNECTION_ACQUIRE_INCREMENT;
	@Value("${connection.minPoolSize}")
	private int CONNECTION_MIN_POOL_SIZE;
	@Value("${connection.maxPoolSize}")
	private int CONNECTION_MAX_POOL_SIZE;
	@Value("${connection.maxIdleTime}")
	private int CONNECTION_MAX_IDLE_TIME;
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
	  return new PropertySourcesPlaceholderConfigurer();
	}
	
    @Bean
	public DataSource dataSource() throws PropertyVetoException {
		//DriverManagerDataSource dataSource = new DriverManagerDataSource();
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(PROPERTY_NAME_DATABASE_DRIVER);
		dataSource.setJdbcUrl(PROPERTY_NAME_DATABASE_URL);
		dataSource.setUser(PROPERTY_NAME_DATABASE_USERNAME);
		dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
		dataSource.setAcquireIncrement(CONNECTION_ACQUIRE_INCREMENT);
		dataSource.setMinPoolSize(CONNECTION_MIN_POOL_SIZE);
		dataSource.setMaxPoolSize(CONNECTION_MAX_POOL_SIZE);
		dataSource.setMaxIdleTime(CONNECTION_MAX_IDLE_TIME);
		dataSource.setMaxStatements(180);
		/*dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
		dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
		dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
		dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);*/
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}
	
	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
		properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
		properties.put("hibernate.hbm2ddl.auto", PROPERTY_HIBERNATE_HBM2DDL_AUTO);
		properties.put("hibernate.format_sql", PROPERTY_HIBERNATE_FORMAT_SQL);
		properties.put("hibernate.jdbc.batch_size", PROPERTY_JDBC_BATCH_SIZE);
		return properties;	
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws PropertyVetoException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
