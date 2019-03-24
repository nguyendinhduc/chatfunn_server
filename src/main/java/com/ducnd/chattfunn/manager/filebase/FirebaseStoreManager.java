package com.ducnd.chattfunn.manager.filebase;

import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.common.utils.Pair;
import com.ducnd.chattfunn.model.ObjectError;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@Controller
public class FirebaseStoreManager {
    private static final Logger LOG = LoggerFactory.getLogger(FirebaseStoreManager.class);

    public FirebaseStoreManager() {
        try {
            File file = new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()), "service_account_key.json");
            InputStream inputStream = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://fir-firebase-5b455.firebaseio.com")
                    .setStorageBucket("fir-firebase-5b455.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);


        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String uploadImageFile(MultipartFile multipartFile) throws  ExceptionResponse {
        Bucket bucket = StorageClient.getInstance().bucket();
        LOG.info("uploadFile info bucket: " + bucket.getName());
        LOG.debug("uploadFile debug bucket: " + bucket.getName());
        String name = new Date().getTime() +"_"+multipartFile.getOriginalFilename();
        try {
            bucket.create("image/" + name, multipartFile.getInputStream(), multipartFile.getContentType() == null ? MediaType.IMAGE_JPEG_VALUE: multipartFile.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionResponse(new ObjectError(ObjectError.ERROR_PARAM, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return name;
    }

    public Pair<byte[], String> getImage(String name) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob= bucket.get("image/" + name);
        return new Pair<byte[], String>(blob.getContent(), blob.getContentType());

    }

}
