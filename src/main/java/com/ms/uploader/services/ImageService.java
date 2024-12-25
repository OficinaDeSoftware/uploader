package com.ms.uploader.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.ms.uploader.api.dtos.ImageDTO;
import com.ms.uploader.exceptions.CredentialsNotFound;
import com.ms.uploader.api.mappers.ImageDTOMapper;
import com.ms.uploader.models.ImageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Slf4j
@Service
public class ImageService {

    @Value("${firebase.private.key.path}")
    private String firebasePrivateKeyPath;

    @Value("${firebase.bucket}")
    private String firebaseBucket;

    @Value("${firebase.download.url}")
    private String firebaseDownloadUrl;

    private final ImageDTOMapper imageDTOMapper;

    public ImageService( ImageDTOMapper imageDTOMapper ) {
        this.imageDTOMapper = imageDTOMapper;
    }

    private ImageModel uploadFile( File file, String fileName, String contentType ) throws IOException, RuntimeException {

        log.info("uploadFile [FILE_NAME] {}", fileName);

        BlobId blobId = BlobId.of(firebaseBucket, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType( contentType ).build();
        InputStream inputStream = ImageService.class.getClassLoader().getResourceAsStream(firebasePrivateKeyPath);

        if( inputStream == null ) {
            log.error("InputStream is null, credentials not found");
            throw new CredentialsNotFound();
        }

        Credentials credentials = GoogleCredentials.fromStream(inputStream);

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        final String fileUuid = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

        final String fileUrl = String.format( "%s/v0/b/%s/o/%s?alt=media", firebaseDownloadUrl, firebaseBucket, fileUuid );

        log.error("uploadFile [FILE_URL] {}", fileUrl );

        return new ImageModel( fileUrl );
    }

    private File convertToFile( MultipartFile multipartFile, String fileName ) throws IOException {

        File tempFile = new File( fileName );

        try ( FileOutputStream fos = new FileOutputStream( tempFile ) ) {
            fos.write( multipartFile.getBytes() );

        }

        return tempFile;
    }

    private String getExtension( String fileName ) {
        return fileName.substring( fileName.lastIndexOf(".") );
    }

    public ImageDTO upload( MultipartFile multipartFile ) throws RuntimeException {

        String fileName = multipartFile.getOriginalFilename();

        if ( fileName == null ){
            log.error( "File name is empty");
            throw new IllegalArgumentException("File name is empty");
        }

        fileName = UUID.randomUUID().toString().concat( this.getExtension(fileName) );

        try {

            final String contentType = multipartFile.getContentType();

            File file = this.convertToFile( multipartFile, fileName );

            ImageModel imageModel = this.uploadFile( file, fileName, contentType );

            file.delete();

            return imageDTOMapper.apply( imageModel );

        } catch ( Exception e ) {
            log.error( "Fail on upload file [ERROR] {}", e.getMessage());
            throw new RuntimeException( "Fail on upload file" );
        }
    }

}