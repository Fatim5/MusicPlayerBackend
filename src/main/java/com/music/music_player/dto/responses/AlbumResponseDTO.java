package com.music.music_player.dto.responses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponseDTO {

    private Long id;
    private String titre;
    private String image;

    private Long artisteId; // on simplifie la relation

}