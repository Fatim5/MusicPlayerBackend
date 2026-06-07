package com.music.music_player.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChansonResponseDTO {

    private Long id;
    private String titre;
    private String fichierAudio;

    private Long albumId;
}
