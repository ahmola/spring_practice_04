package com.example.prac04.user;

import com.example.prac04.config.ObjectIdSerializer;
import com.example.prac04.player.Player;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class User {

    @JsonSerialize(using = ObjectIdSerializer.class)
    @Id
    private ObjectId id;

    private String email;

    public User(UserDTO userDTO){
        this.email = userDTO.getEmail();
    }
}
