package com.music.music_player.services.interfaces;

import com.music.music_player.dto.requests.ArtisteRequestDTO;
import com.music.music_player.dto.responses.ArtisteResponseDTO;

import java.util.List;

public interface ArtisteService {

    ArtisteResponseDTO saveArtiste(ArtisteRequestDTO requestDTO);

    List<ArtisteResponseDTO> findAllArtistes();

    ArtisteResponseDTO findArtisteById(Long id);

    ArtisteResponseDTO findArtisteByNom(String nom);

    ArtisteResponseDTO updateArtiste(Long id, ArtisteRequestDTO requestDTO);

    void deleteArtiste(Long id);
}