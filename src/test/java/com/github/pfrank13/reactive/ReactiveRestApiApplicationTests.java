package com.github.pfrank13.reactive;

import com.github.pfrank13.reactive.dto.UserDto;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveRestApiApplicationTests {
  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void saveUser(){
    //GIVEN
    final UserDto userDto = UserDto.builder().name("Peter").build();

    //WHEN THEN
    webTestClient
        .post()
        .uri("/user")
        .body(BodyInserters.fromObject(userDto))
        .exchange()
        .expectStatus()
        //.isOk()
        .isCreated()
        .expectBody(UserDto.class)
        .value(retVal -> {
          Assertions.assertThat(retVal.getId()).isNotNegative();
          Assertions.assertThat(retVal.getName()).isEqualTo(userDto.getName());
        });
  }
}
