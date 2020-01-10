package com.uca.assignment.configs;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DBTwoConfig {

	@Value("${datasource.dbone.url}")
	private String databaseUrl;

	@Value("${datasource.dbone.username}")
	private String username;

	@Value("${datasource.dbone.password}")
	private String password;

	@Value("${datasource.dbone.driverClassName}")
	private String driverClassName;

	@Value("${datasource.dbone.maxActive}")
	private String maxActive;

	@Value("${datasource.dbone.validationQuery}")
	private String validationQuery;

	@Value("${datasource.dbone.testWhileIdle}")
	private String testWhileIdle;

	@Value("${datasource.dbone.hibernate.dialect}")
	private String dialect;

	@Value("${datasource.dbone.hibernate.show_sql}")
	private String hibernate_show_sql;

	@Bean
	public LocalContainerEntityManagerFactoryBean dbtwoEntityManager() {
		System.out.println(databaseUrl);
		System.out.println(password);
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", hibernate_show_sql);
		properties.put("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		properties.put("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dbtwoDataSource());
		em.setPackagesToScan(new String[] { "com.uca.assignment.dbone.entities" });
		//
		// lk.dialog.reloadloan.dbone.entities.resourceloan
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