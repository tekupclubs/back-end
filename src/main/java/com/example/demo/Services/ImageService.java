package com.example.demo.Services;

import com.example.demo.Repository.ImageRepository;
import com.example.demo.models.ImageDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;

    public ImageDb store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageDb image = new ImageDb(fileName, file.getContentType(), file.getBytes());

        return imageRepository.save(image);
    }

    public ImageDb getFile(String id) {
        return imageRepository.findById(id).get();
    }

    public Stream<ImageDb> getAllFiles() {
        return imageRepository.findAll().stream();
    }

}