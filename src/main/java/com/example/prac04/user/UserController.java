package com.example.prac04.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public Flux<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<User>> register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUser(@PathVariable String id){
        return userService.findUser(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/fix")
    public Mono<ResponseEntity<Boolean>> fixUser(@RequestBody UserDTO userDTO){
        return userService.fixUser(userDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Boolean>> deleteUser(@PathVariable String id){
        return userService.deleteUser(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
