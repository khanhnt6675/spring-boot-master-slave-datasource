package vn.com.khanhnt.masterslavedatasource.config.datasource.routing;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceRoutingConfig {

  @Bean
  @Primary
  public DataSource routingDataSource(
      @Qualifier("masterDataSource") DataSource masterDataSource,
      @Qualifier("slaveDataSource") DataSource slaveDataSource
  ) {
    return new TransactionRoutingDataSource(masterDataSource, slaveDataSource);
  }

}
