package com.example.demo;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.specialized.BlobOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlobServiceImpl  implements  BlobService{

    private final BlobServiceClient serviceClient;
    private final BlobContainerClient blobContainerClient;


    public BlobServiceImpl(BlobServiceClient serviceClient, BlobContainerClient blobContainerClient){
        this.serviceClient = serviceClient;
        this.blobContainerClient = blobContainerClient;

    }

    public byte[] downloadFilesCSV(String fileName){
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
          return  blobClient.downloadToFile(fileName).getContentMd5();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void uploadFile(MultipartFile multipartFile){

        BlobClient blobClient = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());

        try (BlobOutputStream blobOS = blobClient.getBlockBlobClient().getBlobOutputStream()) {
            blobOS.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> learArchivos(){

        List<String> archivosLista = new ArrayList<>();
        for (BlobItem blobItem : blobContainerClient.listBlobs()) {
            archivosLista.add(blobItem.getName());
        }

        return archivosLista;
    }
}
