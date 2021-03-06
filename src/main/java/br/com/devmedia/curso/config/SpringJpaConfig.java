package br.com.devmedia.curso.config;

import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SpringJpaConfig {
	
//	@Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/dasboard?zeroDateTimeBehavior=convertToNull");
//        ds.setUsername("root");
//        ds.setPassword("");
//        return ds;
//        	// ?createDatabaseIfNotExist=true
//    }
	
//	@Bean
//  public DataSource dataSource() {
//      DriverManagerDataSource ds = new DriverManagerDataSource();
//      ds.setDriverClassName("oracle.jdbc.OracleDriver");
//      ds.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
//      ds.setUsername("system");
//      ds.setPassword("141620");
//      return ds;
      	//<!-- ?createDatabaseIfNotExist=true-->
  //}
	
	@Bean
  public DataSource dataSource() {
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setDriverClassName("oracle.jdbc.OracleDriver");
      ds.setUrl("jdbc:oracle:thin:@10.0.0.9:1521:TESTE");
      ds.setUsername("cigamteste");
      ds.setPassword("cigamteste");
      return ds;
      	
  }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource((javax.sql.DataSource) dataSource);
        factory.setPackagesToScan("br.com.devmedia.curso.domain");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(factory);
        tx.setJpaDialect(new HibernateJpaDialect());
        return tx;
    }

    private Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
       // props.setProperty("hibernate.hbm2ddl.auto", "validate");
        return props;
    }
}
