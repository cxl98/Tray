package com.easyArch.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.easyArch.server.config.info.JdbcInfo;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan("com.easyArch.server.mapper")
public class DataConfig {

    @Resource
    private JdbcInfo jdbcInfo;

    @Bean
    public DataSource getDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(jdbcInfo.getDriver());
        dataSource.setUrl(jdbcInfo.getUrl());
        dataSource.setUsername(jdbcInfo.getUsername());
        dataSource.setPassword(jdbcInfo.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(jdbcInfo.getTypeAliasesPackage());
        return sqlSessionFactoryBean;
    }
}
