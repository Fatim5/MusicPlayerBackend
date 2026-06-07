package com.music.music_player.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Override
    public String storeFile(MultipartFile file, String folder) {

        try {

            String fileName =
                    UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path uploadPath =
                    Paths.get("uploads/" + folder);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path destination =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'upload du fichier");
        }

    }

    @Override
    public void deleteFile(String folder, String fileName) {

        try {

            Path path = Paths.get(
                    "uploads/" + folder + "/" + fileName
            );

            Files.deleteIfExists(path);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erreur lors de la suppression du fichier"
            );
        }

    }

}