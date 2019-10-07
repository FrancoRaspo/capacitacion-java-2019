package com.eiv;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PoolProvider  implements DataSourceProvider {
    
        
       
    @Override
    public DataSource getDataSource() {
        
        HikariConfig config = new HikariConfig();
                     
        config.setMaximumPoolSize(10);
        config.setJdbcUrl(JdbcCfg.URL);
        config.setDriverClassName(JdbcCfg.CLASS_NAME);
        config.setUsername(JdbcCfg.USER);
        config.setPassword(JdbcCfg.PASS);
        
        HikariDataSource ds = new HikariDataSource(config);
        
        return ds;
        
    }
}
