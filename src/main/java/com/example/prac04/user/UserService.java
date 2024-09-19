package com.example.prac04.user;

import com.example.prac04.config.StringToObjectId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final StringToObjectId stringToObjectId;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> register(UserDTO userDTO) {
        return userRepository.save(new User(userDTO));
    }

    public Mono<User> findUser(String id) {
        return userRepository.findById(stringToObjectId.convert(id));
    }

    public Flux<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public Mono<Boolean> fixUser(UserDTO userDTO) {
        Mono<User> userMono = userRepository.findById(stringToObjectId.convert(userDTO.getId()))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("There is no such user")));

        return userMono.flatMap(user -> {
            user.setEmail(userDTO.getEmail());
            try {
                return userRepository.save(user).thenReturn(true);
            } catch (Exception e){
                throw new RuntimeException("Something goes wrong during Saving : " + e.getMessage());
            }
        });
    }

    public Mono<Boolean> deleteUser(String id) {
        Mono<User> userMono = userRepository.findById(stringToObjectId.convert(id))
                        .switchIfEmpty(Mono.error(new UsernameNotFoundException("There is no such user")));

        return userMono.flatMap(user -> {
            try{
                log.info("delete " + user.getId());
                return userRepository.deleteById(user.getId()).thenReturn(true);
            }catch (Exception e){
                throw new RuntimeException("Something goes wrong during deleting : "+ e.getMessage());
            }
        });
    }
}
