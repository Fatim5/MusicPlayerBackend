package com.music.music_player.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modèle requis pour la création ou modification d'une chanson")
public class ChansonRequestDTO {

    @NotBlank(message = "Le titre est obligatoire")
    @Schema(example = "Reine", description = "Titre de la chanson")
    private String titre;

    private String fichierAudio;

    private Long albumId;
}
