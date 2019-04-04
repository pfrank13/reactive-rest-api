package com.github.pfrank13.reactive.controller;

import com.github.pfrank13.reactive.dto.UserDto;
import com.github.pfrank13.reactive.model.User;
import com.github.pfrank13.reactive.repository.UserRespoitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author pfrank
 */
@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserController {
  private final UserRespoitory userRespoitory;

  @GetMapping("/users")
  public Flux<User> getUsers(){
    return userRespoitory.findAll();
  }

  @GetMapping("/user/{id}")
  public Mono<User> getUser(@PathVariable("id") final int id){
    return userRespoitory.findById(id);
  }

  @PostMapping("/user")
  public Mono<ResponseEntity<UserDto>> postUser(@RequestBody final Mono<UserDto> userDtoMono){
    return userDtoMono.flatMap(userDto -> userRespoitory.save(this.mapUserDtoToUser(userDto)))
        .flatMap(this::createdResponseEntity);
  }

  User mapUserDtoToUser(UserDto userDto){
    return User.builder().id(userDto.getId()).name(userDto.getName()).build();
  }

  UserDto mapUserToUserDto(final User user){
    return UserDto.builder().id(user.getId()).name(user.getName()).build();
  }

  Mono<ResponseEntity<UserDto>> createdResponseEntity(User user){
    return Mono.just(ResponseEntity.created(URI.create(String.format("/user/%d", user.getId())))
        .body(mapUserToUserDto(user)));
  }

  @PutMapping("/user/{id}")
  public Mono<UserDto> putUser(@RequestBody final Mono<UserDto> userDtoMono){
    return userDtoMono.flatMap(userDto -> userRespoitory.save(this.mapUserDtoToUser(userDto)))
        .map(this::mapUserToUserDto);
  }
}
