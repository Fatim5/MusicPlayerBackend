package com.music.music_player.controller;

import com.music.music_player.dto.requests.AlbumRequestDTO;
import com.music.music_player.dto.responses.AlbumResponseDTO;
import com.music.music_player.services.interfaces.AlbumService;
import com.music.music_player.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final FileStorageService fileStorageService;

    @PostMapping(
            value = "/create",
            consumes = {"multipart/form-data"}
    )
    public AlbumResponseDTO createAlbum(
            @RequestParam("titre") String titre,
            @RequestParam("artisteId") Long artisteId,
            @RequestParam("image") MultipartFile image
    ) {

        String imageName =
                fileStorageService.storeFile(image, "images");

        AlbumRequestDTO dto = new AlbumRequestDTO();
        dto.setTitre(titre);
        dto.setImage(imageName);
        dto.setArtisteId(artisteId);

        return albumService.saveAlbum(dto);
    }

    @GetMapping
    public List<AlbumResponseDTO> findAll() {
        return albumService.findAllAlbums();
    }

    @GetMapping("/{id}")
    public AlbumResponseDTO findById(
            @PathVariable Long id) {

        return albumService.findAlbumById(id);
    }

    @GetMapping("/titre/{titre}")
    public AlbumResponseDTO findByTitre(
            @PathVariable String titre) {

        return albumService.findAlbumByTitre(titre);
    }

    @PutMapping("/{id}")
    public AlbumResponseDTO update(
            @PathVariable Long id,
            @RequestBody AlbumRequestDTO dto) {

        return albumService.updateAlbum(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        albumService.deleteAlbum(id);
    }
}