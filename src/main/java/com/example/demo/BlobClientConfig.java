package com.example.demo;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BlobClientConfig {

    @Value("${azure.blobStorage.url}")
    private String url;

    @Value("${azure.blobStorage.token}")
    private String tokenSat;

    @Value("${azure.blobStorage.containerName}")
    private String   containerName;

    @Bean
    public BlobServiceClient blobServiceClient() {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(url)
                .sasToken(tokenSat)
                .buildClient();
        return blobServiceClient;

    }

    @Bean
    public BlobContainerClient blobContainerClient(){

        BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
                .endpoint(url)
                .sasToken(tokenSat)
                .containerName(containerName)
                .buildClient();
        return blobContainerClient;
    }




}
