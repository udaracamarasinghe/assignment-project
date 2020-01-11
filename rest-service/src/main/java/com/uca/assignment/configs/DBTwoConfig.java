package com.uca.assignment.configs;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.uca.assignment.dbtwo.repositories", entityManagerFactoryRef = "dbtwoEntityManager", transactionManagerRef = "dbtwoTransactionManager")
public class DBTwoConfig {

	@Value("${datasource.dbtwo.url}")
	private String databaseUrl;

	@Value("${datasource.dbtwo.username}")
	private String username;

	@Value("${datasource.dbtwo.password}")
	private String password;

	@Value("${datasource.dbtwo.driverClassName}")
	private String driverClassName;

	@Value("${datasource.dbtwo.maxActive}")
	private String maxActive;

	@Value("${datasource.dbtwo.validationQuery}")
	private String validationQuery;

	@Value("${datasource.dbtwo.testWhileIdle}")
	private String testWhileIdle;

	@Value("${datasource.dbtwo.hibernate.dialect}")
	private String dialect;

	@Value("${datasource.dbtwo.hibernate.auto}")
	private String auto;

	@Value("${datasource.dbtwo.hibernate.show_sql}")
	private String hibernate_show_sql;

	@Bean
	public LocalContainerEntityManagerFactoryBean dbtwoEntityManager() {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", auto);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", hibernate_show_sql);
		properties.put("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		properties.put("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dbtwoDataSource());
		em.setPackagesToScan(new String[] { "com.uca.assignment.dbtwo.entities" });
		//
		// lk.dialog.reloadloan.dbtwo.entities.resourceloan
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	public DataSource dbtwoDataSource() {
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("testWhileIdle", testWhileIdle);
		connectionProperties.setProperty("validationQuery", validationQuery);
		connectionProperties.setProperty("maxActive", maxActive);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		dataSource.setConnectionProperties(connectionProperties);

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager dbtwoTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(dbtwoEntityManager().getObject());

		return transactionManager;
	}
}