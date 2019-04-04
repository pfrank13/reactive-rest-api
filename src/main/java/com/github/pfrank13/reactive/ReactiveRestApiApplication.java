package com.github.pfrank13.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class ReactiveRestApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveRestApiApplication.class, args);
  }
}
