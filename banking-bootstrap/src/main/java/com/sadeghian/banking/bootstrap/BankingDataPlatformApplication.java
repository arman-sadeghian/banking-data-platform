package com.sadeghian.banking.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sadeghian.banking")
public class BankingDataPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingDataPlatformApplication.class, args);
    }
}

