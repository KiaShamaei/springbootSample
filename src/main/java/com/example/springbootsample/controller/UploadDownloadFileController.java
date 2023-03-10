package com.example.springbootsample.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/file")
public class UploadDownloadFileController {

    private final static String  path = "D:\\springClass\\springBootSample\\src\\uploadedFile";

    @PostMapping("/upload")
    public Boolean uplaod(@RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File(path +"\\"+ file.getOriginalFilename() ));
        return true;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable("fileName") String fileName)
            throws IOException {
        var fileData = Files.readAllBytes(new File(path+"\\"+fileName).toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(fileData,headers, HttpStatus.OK);
    }


}
