package com.music.music_player.mappers;

import com.music.music_player.dto.requests.ArtisteRequestDTO;
import com.music.music_player.dto.responses.ArtisteResponseDTO;
import com.music.music_player.entities.Artiste;
import org.springframework.stereotype.Component;

@Component
public class ArtisteMapper {

    // DTO → Entity
    public Artiste toEntity(ArtisteRequestDTO dto) {
        if (dto == null) return null;
        return Artiste.builder()
                .nom(dto.getNom())
                .description(dto.getDescription())
                .image(dto.getImage())
                .build();
    }

    // Entity → DTO
    public  ArtisteResponseDTO toDTO(Artiste artiste) {
        if (artiste == null) return null;
        return ArtisteResponseDTO.builder()
                .id(artiste.getId())
                .nom(artiste.getNom())
                .description(artiste.getDescription())
                .image(artiste.getImage())
                .build();
    }
}