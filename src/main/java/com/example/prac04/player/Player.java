package com.example.prac04.player;

import com.example.prac04.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Player {

    @Id
    private ObjectId id;

    private String title;

    private String artist;

    private String url;

    private ObjectId userId;

    public Player(PlayerDTO playerDTO){
        this.title = playerDTO.getTitle();
        this.artist = playerDTO.getArtist();
        this.url = playerDTO.getUrl();
    }
}
