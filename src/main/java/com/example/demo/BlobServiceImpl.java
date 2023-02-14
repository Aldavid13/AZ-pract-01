package com.example.demo;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.specialized.BlobOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class BlobServiceImpl  implements  BlobService{

    private final BlobServiceClient serviceClient;
    private final BlobContainerClient blobContainerClient;


    public BlobServiceImpl(BlobServiceClient serviceClient, BlobContainerClient blobContainerClient){
        this.serviceClient = serviceClient;
        this.blobContainerClient = blobContainerClient;


    }

    public void downloadFile(){

        BlobClient blobClient = blobContainerClient.getBlobClient("myblob");


        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            blobClient.downloadStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void uploadFile(MultipartFile multipartFile){

        BlobClient blobClient = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());

        try (BlobOutputStream blobOS = blobClient.getBlockBlobClient().getBlobOutputStream()) {
            blobOS.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
