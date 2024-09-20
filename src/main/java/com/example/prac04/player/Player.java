package com.example.prac04.player;

import com.example.prac04.config.ObjectIdSerializer;
import com.example.prac04.user.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @JsonSerialize(using = ObjectIdSerializer.class)
    @Id
    private ObjectId id;

    private String videoId;

    private String title;

    private String artist;

    private String url;

    private ObjectId userId;

    public Player(PlayerDTO playerDTO){
        this.videoId = playerDTO.getVideoId();
        this.title = playerDTO.getTitle();
        this.artist = playerDTO.getArtist();
        this.url = playerDTO.getUrl();
    }
}
