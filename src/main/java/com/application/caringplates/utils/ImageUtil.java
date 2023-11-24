package com.application.caringplates.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageUtil {

    public static byte[] convertImageToByteArray(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    public static byte[] convertImageFileToByteArray(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readAllBytes(path);
    }
}

