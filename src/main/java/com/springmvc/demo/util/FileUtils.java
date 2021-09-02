package com.springmvc.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {

    private static final String IMAGE_FOLDER_PATH = "/data/images";
    private static final String PRODUCT_IMAGE_FOLDER_PATH = IMAGE_FOLDER_PATH + "/products";

    public FileUtils() {
    }

    public static Path saveProductImage(MultipartFile image) {
        if(image == null) {
            throw new IllegalArgumentException("Image file cannot be null");
        }
        createDirectories(Paths.get(System.getProperty("user.dir"), PRODUCT_IMAGE_FOLDER_PATH));

        Path savePath = Paths.get(PRODUCT_IMAGE_FOLDER_PATH, image.getOriginalFilename());
        saveFile(image, Paths.get(System.getProperty("user.dir"), savePath.toString()));

        return savePath;
    }

    private static void saveFile(MultipartFile image, Path path) {
        try {
            image.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectories(Path path) {
        try{
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
