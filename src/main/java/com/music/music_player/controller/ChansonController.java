package com.music.music_player.controller;

import com.music.music_player.dto.requests.ChansonRequestDTO;
import com.music.music_player.dto.responses.ChansonResponseDTO;
import com.music.music_player.services.interfaces.ChansonService;
import com.music.music_player.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/chansons")
@RequiredArgsConstructor
public class ChansonController {

    private final ChansonService chansonService;
    private final FileStorageService fileStorageService;

    @PostMapping(
            value = "/create",
            consumes = {"multipart/form-data"}
    )
    public ChansonResponseDTO createChanson(

            @RequestParam("titre") String titre,
            @RequestParam("albumId") Long albumId,
            @RequestParam("music") MultipartFile music

    ) {

        String musicName =
                fileStorageService.storeFile(music, "musics");

        ChansonRequestDTO dto = new ChansonRequestDTO();
        dto.setTitre(titre);
        dto.setFichierAudio(musicName);
        dto.setAlbumId(albumId);

        return chansonService.saveChanson(dto);
    }

    @GetMapping
    public List<ChansonResponseDTO> findAll() {
        return chansonService.findAllChansons();
    }

    @GetMapping("/{id}")
    public ChansonResponseDTO findById(
            @PathVariable Long id) {

        return chansonService.findChansonById(id);
    }

    @GetMapping("/titre/{titre}")
    public ChansonResponseDTO findByTitre(
            @PathVariable String titre) {

        return chansonService.findChansonByTitre(titre);
    }

    @PutMapping("/{id}")
    public ChansonResponseDTO update(
            @PathVariable Long id,
            @RequestBody ChansonRequestDTO dto) {

        return chansonService.updateChanson(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        chansonService.deleteChanson(id);
    }
}