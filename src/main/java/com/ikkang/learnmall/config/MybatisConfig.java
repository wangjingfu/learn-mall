package com.ikkang.learnmall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ikkang.learnmall.mbg.mapper")
public class MybatisConfig {
}
