package com.music.music_player.services.implementations;

import com.music.music_player.dto.requests.ChansonRequestDTO;
import com.music.music_player.dto.responses.ChansonResponseDTO;
import com.music.music_player.entities.Album;
import com.music.music_player.exceptions.ResourceNotFoundException;
import com.music.music_player.mappers.ChansonMapper;
import com.music.music_player.entities.Chanson;
import com.music.music_player.repository.ChansonRepository;
import com.music.music_player.services.interfaces.ChansonService;
import com.music.music_player.storage.FileStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChansonServiceImpl implements ChansonService {

    private final ChansonRepository chansonRepository;
    private final ChansonMapper chansonMapper;
    private final FileStorageService fileStorageService;

    @Override
    public ChansonResponseDTO saveChanson(ChansonRequestDTO requestDTO) {
        Chanson chanson = chansonMapper.toEntity(requestDTO);
        Chanson saved = chansonRepository.save(chanson);
        return chansonMapper.toDTO(saved);
    }

    @Override
    public List<ChansonResponseDTO> findAllChansons() {
        return chansonRepository.findAll()
                .stream()
                .map(chansonMapper::toDTO)
                .toList();
    }

    @Override
    public ChansonResponseDTO findChansonById(Long id) {

        Chanson chanson = chansonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chanson introuvable avec l'ID : " + id));

        return chansonMapper.toDTO(chanson);
    }

    @Override
    public ChansonResponseDTO findChansonByTitre(String titre) {

        Chanson chanson = chansonRepository.findByTitre(titre)
                .orElseThrow(() -> new ResourceNotFoundException("Chanson introuvable avec le titre: " + titre));

        return chansonMapper.toDTO(chanson);
    }

    @Override
    public ChansonResponseDTO updateChanson(Long id, ChansonRequestDTO requestDTO) {

        Chanson existing = chansonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chanson introuvable avec l'ID : " + id));

        existing.setTitre(requestDTO.getTitre());
        existing.setFichierAudio(
                requestDTO.getFichierAudio()
        );

        if (requestDTO.getAlbumId() != null) {
            Album album = new Album();
            album.setId(requestDTO.getAlbumId());
            existing.setAlbum(album);
        }

        return chansonMapper.toDTO(
                chansonRepository.save(existing)
        );
    }

    @Override
    public void deleteChanson(Long id) {

        Chanson chanson = chansonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Chanson introuvable avec l'ID : " + id
                        ));

        if (chanson.getFichierAudio() != null) {

            fileStorageService.deleteFile(
                    "musics",
                    chanson.getFichierAudio()
            );

        }

        chansonRepository.delete(chanson);

    }

}
