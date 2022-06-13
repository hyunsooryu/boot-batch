package com.hyunsoo.bootbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing
@SpringBootApplication
public class BootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBatchApplication.class, args);
    }

}
