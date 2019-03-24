package com.ducnd.chattfunn.controller;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.common.utils.Pair;
import com.ducnd.chattfunn.manager.filebase.FirebaseStoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    private FirebaseStoreManager firebaseStoreManager;

    @GetMapping(value = Constants.URL_IMAGE)
    public void getImage(
            @RequestParam(value = "name") String name,
            HttpServletResponse response
    ) {
        Pair<byte[], String> con = firebaseStoreManager.getImage(name);
        if (con.getSecond() != null) {
            response.setHeader("Content-Type", con.getSecond());
        }
        try {
            response.getOutputStream().write(con.getFirst());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = Constants.URL_IMAGE)
    public String postImage(
            @RequestParam(value = "file") MultipartFile file
    ) throws ExceptionResponse {
        String link = firebaseStoreManager.uploadImageFile(file);
        return Constants.URL_IMAGE + "?name=" + link;

    }
}
