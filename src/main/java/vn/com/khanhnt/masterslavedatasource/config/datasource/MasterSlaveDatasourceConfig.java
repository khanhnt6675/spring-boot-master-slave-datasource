package vn.com.khanhnt.masterslavedatasource.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MasterSlaveDatasourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.master")
  public DataSourceProperties masterDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.slave")
  public DataSourceProperties slaveDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public HikariDataSource masterDataSource(
      @Qualifier("masterDataSourceProperties") DataSourceProperties properties
  ) {
    return buildHikariDataSource(properties);
  }

  @Bean
  public HikariDataSource slaveDataSource(
      @Qualifier("slaveDataSourceProperties") DataSourceProperties properties
  ) {
    return buildHikariDataSource(properties);
  }

  private HikariDataSource buildHikariDataSource(DataSourceProperties properties) {
    HikariDataSource hikariDataSource = properties.initializeDataSourceBuilder()
        .type(HikariDataSource.class).build();
    hikariDataSource.setAutoCommit(false);
    return hikariDataSource;
  }

}
