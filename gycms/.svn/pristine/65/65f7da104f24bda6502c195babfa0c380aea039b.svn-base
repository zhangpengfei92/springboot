package com.jcl.gycms.config.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by :  Sorata   2017/6/27 0027 下午 3:56.
 */
@Configuration
//@MapperScan(basePackages = MasterDataSourceConfig.MASTER_PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(MasterDataSourceConfig.class);

    @Autowired
    private WallFilter wallFilter;

    /** 配置一个主库
     * @return  DruidDataSource
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "druid.datasource")
    public DataSource masterDruidDataSource(){
        DruidDataSource datasource = new DruidDataSource();
        // filter
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(wallFilter);
        datasource.setProxyFilters(filters);
        return datasource;
    }
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(masterDruidDataSource());
    }

    @Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    public WallFilter wallFilter(WallConfig wallConfig){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }

    @Bean(name = "wallConfig")
    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);//允许一次执行多条语句
        wallConfig.setNoneBaseStatementAllow(true);//允许一次执行多条语句
        return wallConfig;
    }
}