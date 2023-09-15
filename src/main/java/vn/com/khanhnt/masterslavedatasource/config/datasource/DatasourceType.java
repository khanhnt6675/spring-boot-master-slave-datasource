package vn.com.khanhnt.masterslavedatasource.config.datasource;

public enum DatasourceType {

  MASTER,
  SLAVE;

  public static DatasourceType getDatasourceType(boolean isReadOny) {
    return isReadOny ? SLAVE : MASTER;
  }

}
