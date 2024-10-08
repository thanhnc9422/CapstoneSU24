package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private  CloudinaryService cloudinaryService;

    @Autowired
    public TestController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String url = cloudinaryService.uploadImage(file, "testManh123");
            return ResponseEntity.ok(url);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }
    @GetMapping("/healthCheck")
    public ResponseEntity<String> healthCheck() {
            return ResponseEntity.status(200).body("alive");
    }

    @GetMapping("/down")
    public ResponseEntity<byte[]> getImage(@RequestParam String publicId) {
        try {
            byte[] image = cloudinaryService.downloadImage(publicId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/jpeg"); // or appropriate image type
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/testUpload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadResult = cloudinaryService.uploadFileAndGetUrl(file, "hehe");
            return new ResponseEntity<>(uploadResult, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
