package com.music.music_player.services.interfaces;

import com.music.music_player.dto.requests.ChansonRequestDTO;
import com.music.music_player.dto.responses.ChansonResponseDTO;

import java.util.List;

public interface ChansonService {

    ChansonResponseDTO saveChanson(ChansonRequestDTO RequestDTO);

    List<ChansonResponseDTO> findAllChansons();

    ChansonResponseDTO findChansonById(Long id);

    ChansonResponseDTO findChansonByTitre(String titre);

    ChansonResponseDTO updateChanson(Long id, ChansonRequestDTO RequestDTO);

    void deleteChanson(Long id);


}
