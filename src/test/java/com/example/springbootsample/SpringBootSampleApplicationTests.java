package com.example.springbootsample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class SpringBootSampleApplicationTests {
    @Autowired
    private RestTemplate restTemplate;

    private static final String DOWNLOAD_PATH = "D:\\springClass\\springBootSample\\download";
    @Test
    public void upload(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String , Object> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("file" , new ClassPathResource("mypick.jpg"));
        HttpEntity<MultiValueMap<String ,Object>> request = new HttpEntity<>(valueMap,headers);
        ResponseEntity<Boolean> response = restTemplate
                .postForEntity("http://localhost:8080/file/upload",request, Boolean.class);
        System.out.println(response.getBody());
    }

    @Test
    void testDownload() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.IMAGE_JPEG));
        HttpEntity<String> http = new HttpEntity<>(httpHeaders);
        String fileName  = "mypick.jpg";
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/file/download/"
                + fileName , HttpMethod.GET,http,byte[].class);
        Files.write(Paths.get(DOWNLOAD_PATH +"\\"+fileName),response.getBody());
    }
}
