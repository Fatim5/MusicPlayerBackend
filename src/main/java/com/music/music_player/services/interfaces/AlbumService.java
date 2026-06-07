package com.music.music_player.services.interfaces;

import com.music.music_player.dto.requests.AlbumRequestDTO;
import com.music.music_player.dto.responses.AlbumResponseDTO;

import java.util.List;

public interface AlbumService {

    AlbumResponseDTO saveAlbum(AlbumRequestDTO RequestDTO);

    List<AlbumResponseDTO> findAllAlbums();

    AlbumResponseDTO findAlbumById(Long id);

    AlbumResponseDTO findAlbumByTitre(String titre);

    AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO RequestDTO);

    void deleteAlbum(Long id);
}
