package com.example.prac04.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerDTO {

    private String id;

    private String videoId;

    private String title;

    private String artist;

    private String url;

    private String userId;
}
