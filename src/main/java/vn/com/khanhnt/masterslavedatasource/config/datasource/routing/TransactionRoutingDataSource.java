package vn.com.khanhnt.masterslavedatasource.config.datasource.routing;

import java.util.HashMap;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import vn.com.khanhnt.masterslavedatasource.config.datasource.DatasourceType;

@Slf4j
public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

  public TransactionRoutingDataSource(
      DataSource masterDatasource,
      DataSource slaveDatasource
  ) {

    HashMap<Object, Object> targetDataSources = new HashMap<>() {{
      put(DatasourceType.MASTER, masterDatasource);
      put(DatasourceType.SLAVE, slaveDatasource);
    }};

    setTargetDataSources(targetDataSources);
    setDefaultTargetDataSource(masterDatasource);

  }

  @Override
  protected Object determineCurrentLookupKey() {

    DatasourceType datasourceType = DatasourceType.getDatasourceType(
        TransactionSynchronizationManager.isCurrentTransactionReadOnly());
    log.info("Routed to: {}", datasourceType);
    return datasourceType;

  }

}
