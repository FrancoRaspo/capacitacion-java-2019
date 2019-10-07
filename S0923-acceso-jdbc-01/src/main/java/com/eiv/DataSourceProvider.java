package com.eiv;

import javax.sql.DataSource;

public interface DataSourceProvider {
    public DataSource getDataSource();
}
