/**
 * FileName: DbConfig
 * Author:   jack.xue
 * Date:     2019/1/4 10:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/4           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/1/4
 * @since 1.0.0
 */
@Configuration
@Conditional(DbCondition.class)
@EnableJpaRepositories(basePackages="cn.bw.fitzcloud.fitzcloudserver"
        , entityManagerFactoryRef="jpaEntityManagerFactory"
        , transactionManagerRef="jpaTransactionManager")
public class DbConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    @Qualifier(value="primaryDataSource")
    @ConfigurationProperties(prefix="monitoring.datasource")
    public DataSource getDateSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="jdbcTransactionManager")
    public PlatformTransactionManager getJdbcTransactionManager(@Qualifier(value="primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "jpaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(getDateSource());
        factory.setPackagesToScan("cn.bw.fitzcloud.fitzcloudserver");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "validate"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("hibernate.show-sql","true"));
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager getJpaTransactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}