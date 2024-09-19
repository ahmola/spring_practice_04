package com.example.prac04.player;

import com.example.prac04.config.StringToObjectId;
import com.example.prac04.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    private StringToObjectId stringToObjectId;


    public Flux<Player> findAll() {
        return playerRepository.findAll();
    }

    public Mono<Player> addPlayer(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO);
        if(!playerDTO.getUserId().isEmpty()) {
            try {
                userRepository.findById(stringToObjectId.convert(playerDTO.getUserId())).subscribe(user -> {
                    player.setUserId(user.getId());
                });
            }catch (Exception e){
                throw new RuntimeException("Something goes wrong during Finding User : " + e.getMessage());
            }
        }
        return playerRepository.save(new Player());
    }

    public Mono<Player> findPlayer(String id) {
        return playerRepository.findById(stringToObjectId.convert(id));
    }

    public Mono<Boolean> fixPlayer(PlayerDTO playerDTO) {
        Mono<Player> playerMono = playerRepository.findById(stringToObjectId.convert(playerDTO.getId()))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("There is no such Player")));

        return playerMono.flatMap(player -> {
            try {
                player.setArtist(playerDTO.getArtist());
                player.setUrl(playerDTO.getUrl());
                player.setTitle(playerDTO.getTitle());
                if (playerDTO.getUserId().isEmpty())
                    player.setUserId(stringToObjectId.convert(""));
                else
                    player.setUserId(stringToObjectId.convert(playerDTO.getUserId()));

                playerRepository.save(player);

                return Mono.just(true);
            } catch (Exception e){
                throw new RuntimeException("Something goes wrong during Fixing Player : " + e.getMessage());
            }
        });
    }

    public Mono<Boolean> deletePlayer(String id) {
        Mono<Player> playerMono = playerRepository.findById(stringToObjectId.convert(id))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("There is no such Player")));
        return playerMono.flatMap(player -> {
            try {
                playerRepository.deleteById(player.getId());

                return Mono.just(true);
            }catch (Exception e){
                throw new RuntimeException("Something goes wrong during Deleting Player : " + e.getMessage());
            }
        });
    }
}
