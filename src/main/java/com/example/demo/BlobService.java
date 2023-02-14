package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

public interface BlobService {

    void uploadFile(MultipartFile multipartFile);
}
