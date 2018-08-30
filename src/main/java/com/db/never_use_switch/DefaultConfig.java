package com.db.never_use_switch;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
public class DefaultConfig {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(DefaultConfig.class);
    }

    @Bean
    public DataFactory frame(){
        return new DataFactory();
    }
}
