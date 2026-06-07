package com.music.music_player.services.implementations;

import com.music.music_player.dto.requests.AlbumRequestDTO;
import com.music.music_player.dto.responses.AlbumResponseDTO;
import com.music.music_player.entities.Artiste;
import com.music.music_player.exceptions.ResourceNotFoundException;
import com.music.music_player.mappers.AlbumMapper;
import com.music.music_player.entities.Album;
import com.music.music_player.repository.AlbumRepository;
import com.music.music_player.services.interfaces.AlbumService;
import com.music.music_player.storage.FileStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;
    private final FileStorageService fileStorageService;

    @Override
    public AlbumResponseDTO saveAlbum(AlbumRequestDTO requestDTO) {
        Album album = albumMapper.toEntity(requestDTO);
        Album saved = albumRepository.save(album);
        return albumMapper.toDTO(saved);
    }

    @Override
    public List<AlbumResponseDTO> findAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(albumMapper::toDTO)
                .toList();
    }

    @Override
    public AlbumResponseDTO findAlbumById(Long id) {

        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album introuvable avec l'ID : " + id ));

        return albumMapper.toDTO(album);
    }

    @Override
    public AlbumResponseDTO findAlbumByTitre(String titre) {

        Album album = albumRepository.findByTitre(titre)
                .orElseThrow(() -> new ResourceNotFoundException("Album introuvable avec le titre : " + titre));

        return albumMapper.toDTO(album);
    }

    @Override
    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO requestDTO) {

        Album existing = albumRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Album introuvable avec l'ID : " + id));

        existing.setTitre(requestDTO.getTitre());
        existing.setImage(requestDTO.getImage());

        if (requestDTO.getArtisteId() != null) {
            Artiste artiste = new Artiste();
            artiste.setId(requestDTO.getArtisteId());
            existing.setArtiste(artiste);
        }

        return albumMapper.toDTO(
                albumRepository.save(existing)
        );
    }

    @Override
    public void deleteAlbum(Long id) {

        Album album = albumRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Album introuvable avec l'ID : " + id
                        ));

        if (album.getImage() != null) {
            fileStorageService.deleteFile(
                    "images",
                    album.getImage()
            );
        }

        albumRepository.delete(album);

    }
}