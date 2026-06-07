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
@Schema(description = "Modèle requis pour la création ou modification d'un artiste")
public class ArtisteRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    @Schema(example = "Dadju", description = "Nom de l'artiste")
    private String nom;

    private String description;
    private String image;
}




