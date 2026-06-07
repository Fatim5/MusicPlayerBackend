package com.music.music_player.services.implementations;

import com.music.music_player.dto.requests.ArtisteRequestDTO;
import com.music.music_player.dto.responses.ArtisteResponseDTO;
import com.music.music_player.exceptions.ResourceNotFoundException;
import com.music.music_player.mappers.ArtisteMapper;
import com.music.music_player.entities.Artiste;
import com.music.music_player.repository.ArtisteRepository;
import com.music.music_player.services.interfaces.ArtisteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArtisteServiceImpl implements ArtisteService {

    private final ArtisteRepository artisteRepository;
    private final ArtisteMapper artisteMapper;

    @Override
    public ArtisteResponseDTO saveArtiste(ArtisteRequestDTO requestDTO) {

        Artiste artiste = artisteMapper.toEntity(requestDTO);
        Artiste saved = artisteRepository.save(artiste);
        return artisteMapper.toDTO(saved);
    }

    @Override
    public List<ArtisteResponseDTO> findAllArtistes() {

        return artisteRepository.findAll()
                .stream()
                .map(artisteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtisteResponseDTO findArtisteById(Long id) {

        Artiste artiste = artisteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste introuvable avec l'ID : " + id));

        return artisteMapper.toDTO(artiste);
    }

    @Override
    public ArtisteResponseDTO findArtisteByNom(String nom) {

        Artiste artiste = artisteRepository.findByNom(nom)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste introuvable avec le nom : " + nom));

        return artisteMapper.toDTO(artiste);
    }

    @Override
    public ArtisteResponseDTO updateArtiste(Long id, ArtisteRequestDTO requestDTO) {
        Artiste existingArtiste = artisteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artiste introuvable avec l'ID : " + id));

        existingArtiste.setNom(requestDTO.getNom());
        existingArtiste.setDescription(requestDTO.getDescription());
        existingArtiste.setImage(requestDTO.getImage());

        return artisteMapper.toDTO(artisteRepository.save(existingArtiste));
    }

    @Override
    public void deleteArtiste(Long id) {
        if (!artisteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de supprimer. Artiste introuvable avec l'ID : " + id);
        }
        artisteRepository.deleteById(id);
    }


}