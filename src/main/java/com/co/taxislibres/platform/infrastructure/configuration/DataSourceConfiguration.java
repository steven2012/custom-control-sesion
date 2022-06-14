package com.co.taxislibres.platform.infrastructure.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.co.taxislibres.platform.crosscutting.persistence.repository" })
public class DataSourceConfiguration {

	@Value("${spring.datasource.url}")
	private String conecctionUrl;

	@Value("${spring.datasource.password}")
	private String conecctionPassword;
	
	@Value("${spring.datasource.username}")
	private String conecctionUsser;
	
	@Primary
	@Bean(name = "db-user-conductor")
	public DataSource dataSource() {
		HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().
				username(conecctionUsser).
				password(conecctionPassword).
				url(conecctionUrl).build();
		ds.setConnectionTestQuery("SELECT 1");
		return ds;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("db-user-conductor") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.co.taxislibres.platform.crosscutting.persistence.entity")
				.persistenceUnit("master").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
