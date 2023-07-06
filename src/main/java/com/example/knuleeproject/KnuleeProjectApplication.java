package com.example.knuleeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA데이터를 자동으로 기록되도록 하는 기능을 전원 스위치 개념
@SpringBootApplication
public class KnuleeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnuleeProjectApplication.class, args);
    }

}
