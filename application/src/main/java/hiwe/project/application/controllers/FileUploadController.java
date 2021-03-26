package ca.project.application.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ca.project.domain.entities.storage.FileData;
import ca.project.domain.entities.storage.FileMetadata;
import ca.project.domain.external.infrastructure.storage.IFileStorage;

@RestController
@RequestMapping("/api/storage")
public class FileUploadController {
	@Autowired
	private IFileStorage couchbaseFileStorage;

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable String id) throws IOException {
    	FileData fileData = couchbaseFileStorage.getFile(id);
    	
    	ContentDisposition contentDisposition = ContentDisposition.builder("inline")
    	          .filename(fileData.getFileName())
    	          .build();
    	
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(fileData.getFileBytes());
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    	FileMetadata savedFileData = couchbaseFileStorage.saveFile(new FileMetadata(file.getOriginalFilename()), file.getBytes());
        
        return ResponseEntity.ok().body(savedFileData);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable String id) {
    	couchbaseFileStorage.deleteFile(id);
    	
        return ResponseEntity.ok().build();
    }
}
