package org.alexkings.productdeliveryapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Generate a random UUID and create a new filename
                String originalFilename = file.getOriginalFilename();
                String fileExtension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf('.'));

                String newFilename = UUID.randomUUID() + fileExtension;

                // Get the file and save it to the specified directory with the new filename
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:\\Users\\user\\IdeaProjects\\ProductDeliveryApi\\src\\main\\resources\\static\\images" + File.separator + newFilename);
                Files.write(path, bytes);
                return ResponseEntity.ok(newFilename);

            } catch (IOException e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + file.getOriginalFilename());
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        try {
            Path filePath = Paths.get(id);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().build();
        } catch (IOException ex) {
            return ResponseEntity.status(500).build();
        }
    }
}
