package com.music.music_player.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modèle requis pour la création ou modification d'un album")
public class AlbumRequestDTO {

    @NotBlank(message = "Le titre est obligatoire")
    @Schema(example = "Amour", description = "Titre de l'album")
    private String titre;

    private String image;

    private Long artisteId; // on simplifie la relation

}
