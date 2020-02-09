package ipr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@ComponentScans( value = {
		@ComponentScan(
				basePackages = {"ipr.web.repository"}
		)
		,
		@ComponentScan(
				basePackages = { "ipr.web.service"}
		)
})
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"ipr.web.repository"},
		repositoryImplementationPostfix = "Impl")
@PropertySource("classpath:db.properties")
public class DbConfig {

	@Autowired
	private Environment env;

	@PostConstruct
	private void p() {
		System.out.println("pppppppppppppppppppppppppppppppppppppppp");
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		return new HibernateJpaVendorAdapter();
	}

	@Bean //no DataSource
	public DataSource dataSource() throws NamingException {
		System.out.println("ddddddddddddddddddddddddddddddddddddd");

//		for TOMCAT
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties props = new Properties();

		// Setting Hibernate properties
		props.put(DIALECT, env.getProperty("hibernate.dialect"));
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

		return props;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean em_fb = new LocalContainerEntityManagerFactoryBean();

		em_fb.setDataSource(dataSource());
		em_fb.setJpaProperties(hibernateProperties());

		em_fb.setPackagesToScan("ipr.web.entity");
		em_fb.setJpaVendorAdapter(jpaVendorAdapter());
		em_fb.afterPropertiesSet();//

		return em_fb.getNativeEntityManagerFactory();
	}

	//for working @Transactional
	@Bean
	public PlatformTransactionManager transactionManager() throws IOException, NamingException {
		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
		return new JpaTransactionManager(entityManagerFactory());
	}
}