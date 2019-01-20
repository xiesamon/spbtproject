package com.samon.spbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpbtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbtApplication.class, args);
    }

}

