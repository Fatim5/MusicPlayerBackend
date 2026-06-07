package com.music.music_player.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file, String folder);
    void deleteFile(String folder, String fileName);
}