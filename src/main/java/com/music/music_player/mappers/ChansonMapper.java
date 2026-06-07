package com.music.music_player.mappers;

import com.music.music_player.dto.requests.ChansonRequestDTO;
import com.music.music_player.dto.responses.ChansonResponseDTO;
import com.music.music_player.entities.Album;
import com.music.music_player.entities.Chanson;
import org.springframework.stereotype.Component;

@Component
public class ChansonMapper {

    // DTO → Entity
    public Chanson toEntity(ChansonRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Chanson chanson = Chanson.builder()
                .titre(dto.getTitre())
                .fichierAudio(dto.getFichierAudio())
                .build();

        if (dto.getAlbumId() != null) {
            Album album = new Album();
            album.setId(dto.getAlbumId());
            chanson.setAlbum(album);
        }

        return chanson;
    }

    // Entity → DTO
    public ChansonResponseDTO toDTO(Chanson entity) {

        if (entity == null) {
            return null;
        }

        return ChansonResponseDTO.builder()
                .id(entity.getId())
                .titre(entity.getTitre())
                .fichierAudio(entity.getFichierAudio())
                .albumId(
                        entity.getAlbum() != null
                                ? entity.getAlbum().getId()
                                : null
                )
                .build();
    }
}