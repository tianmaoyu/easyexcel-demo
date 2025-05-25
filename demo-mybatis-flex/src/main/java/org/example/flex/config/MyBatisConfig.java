package org.example.flex.config;

import com.mybatisflex.core.mybatis.FlexConfiguration;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
//@MapperScan("org.example.flex.mapper") // 替换为你的 Mapper 包路径
//public class MyBatisConfig {
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        FlexConfiguration configuration = new FlexConfiguration();
//        configuration.setLogImpl(StdOutImpl.class); // 可选：设置日志实现
//
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setConfiguration(configuration);
//        return factoryBean.getObject();
//    }
//}