package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlobService {

    void uploadFile(MultipartFile multipartFile);

    byte[] downloadFilesCSV(String fileName);

    List<String> learArchivos();
}
