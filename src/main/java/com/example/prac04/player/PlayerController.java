package com.example.prac04.player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1/player")
@RequiredArgsConstructor
@RestController
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/all")
    public Flux<Player> findAll(){
        return playerService.findAll();
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<Player>> addPlayer(@RequestBody PlayerDTO playerDTO){
        return playerService.addPlayer(playerDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Player>> findPlayer(@PathVariable String id){
        return playerService.findPlayer(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/fix")
    public Mono<ResponseEntity<Boolean>> fixPlayer(@RequestBody PlayerDTO playerDTO){
        return playerService.fixPlayer(playerDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Boolean>> deletePlayer(@PathVariable String id){
        return playerService.deletePlayer(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
