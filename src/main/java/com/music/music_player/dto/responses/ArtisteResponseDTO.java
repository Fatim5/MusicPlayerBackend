package com.music.music_player.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtisteResponseDTO {

    private Long id;
    private String nom;
    private String description;
    private String image;

}

