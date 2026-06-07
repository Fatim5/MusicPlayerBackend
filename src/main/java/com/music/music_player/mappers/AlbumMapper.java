package com.music.music_player.mappers;

import com.music.music_player.dto.requests.AlbumRequestDTO;
import com.music.music_player.dto.responses.AlbumResponseDTO;
import com.music.music_player.entities.Album;
import com.music.music_player.entities.Artiste;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {

    // DTO → Entity
    public Album toEntity(AlbumRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Album album = Album.builder()
                .titre(dto.getTitre())
                .image(dto.getImage())
                .build();

        if (dto.getArtisteId() != null) {
            Artiste artiste = new Artiste();
            artiste.setId(dto.getArtisteId());
            album.setArtiste(artiste);
        }

        return album;
    }

    // Entity → DTO
    public AlbumResponseDTO toDTO(Album album) {

        if (album == null) {
            return null;
        }

        return AlbumResponseDTO.builder()
                .id(album.getId())
                .titre(album.getTitre())
                .image(album.getImage())
                .artisteId(
                        album.getArtiste() != null
                                ? album.getArtiste().getId()
                                : null
                )
                .build();
    }
}