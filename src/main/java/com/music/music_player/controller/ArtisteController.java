package com.music.music_player.controller;

import com.music.music_player.dto.requests.ArtisteRequestDTO;
import com.music.music_player.dto.responses.ArtisteResponseDTO;
import com.music.music_player.services.interfaces.ArtisteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistes")
@RequiredArgsConstructor
public class ArtisteController {

    private final ArtisteService artisteService;

    @PostMapping
    public ArtisteResponseDTO save(@RequestBody ArtisteRequestDTO dto) {

        return artisteService.saveArtiste(dto);
    }
    @PutMapping("/{id}")
    public ArtisteResponseDTO update(
            @PathVariable Long id,
            @RequestBody ArtisteRequestDTO dto) {

        return artisteService.updateArtiste(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        artisteService.deleteArtiste(id);
    }

    @GetMapping
    public List<ArtisteResponseDTO> findAll() {
        return artisteService.findAllArtistes();
    }

    @GetMapping("/{id}")
    public ArtisteResponseDTO findById(@PathVariable Long id) {
        return artisteService.findArtisteById(id);
    }

    @GetMapping("/nom/{nom}")
    public ArtisteResponseDTO findBynom(@PathVariable String nom) {
        return artisteService.findArtisteByNom(nom);
    }
}
