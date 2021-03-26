package ca.project.infrastructure.storage.filesystem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import ca.project.domain.entities.storage.FileData;
import ca.project.domain.entities.storage.FileMetadata;
import ca.project.domain.exceptions.FileStorageException;
import ca.project.domain.external.infrastructure.storage.IFileStorage;
import ca.project.infrastructure.storage.filesystem.interfaces.ISystemFileRepository;
import ca.project.infrastructure.storage.filesystem.interfaces.ISystemFileStorageProperties;
import ca.project.infrastructure.storage.filesystem.models.SystemFile;

// TODO: Not tested yet
public class FileSystemStorageAdapter implements IFileStorage {
	private final Path rootLocation;
	private final ISystemFileRepository systemFileRepository;

    public FileSystemStorageAdapter(
    	ISystemFileRepository systemFileRepository,
    	ISystemFileStorageProperties properties
    ) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.systemFileRepository = systemFileRepository;
        
        init();
    }

    private void init() {
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
        	throw new FileStorageException("Could not initialize storage location");
        }
    }

	@Override
	public FileData getFile(String id) {
		SystemFile fileDocument = systemFileRepository.getFileDocument(id);
		
		if(fileDocument == null) {
			throw new FileStorageException("File not found");
		}
		
		try {
			return new FileData(
				fileDocument.getOriginalFilename(),
				Files.readAllBytes(fileDocument.getFilePath())
			);
		} catch (IOException e) {
			throw new FileStorageException("Unable to load a file");
		}
	}

	@Override
	public FileMetadata saveFile(FileMetadata fileMetadata, byte[] fileBytes) {
		var fileDocument = systemFileRepository.saveFileDocument(new SystemFile(fileMetadata.getOriginalFilename(), null));
		
		if(fileDocument.getId() == null) {
			throw new FileStorageException("File not saved");
		}
		
        try {
        	Path filePath = this.rootLocation.resolve(fileDocument.getId());
        	
            Files.copy(new ByteArrayInputStream(fileBytes), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            fileDocument.setFilePath(filePath);
        }
        catch (IOException e) {
        	systemFileRepository.deleteFileDocument(fileDocument);
        	
            throw new FileStorageException("Failed to store file " + fileDocument.getId());
        }
        
        return systemFileRepository.saveFileDocument(fileDocument);
	}

	@Override
	public void deleteFile(String id) {
		SystemFile fileDocument = systemFileRepository.getFileDocument(id);
		
		if(fileDocument == null) {
			throw new FileStorageException("File not found");
		}
		
		Path filePath = this.rootLocation.resolve(fileDocument.getId());
		
		try {
			Files.delete(filePath);
			
			systemFileRepository.deleteFileDocument(fileDocument);
		} catch (IOException e) {
			throw new FileStorageException("Failed to delete stored file " + fileDocument.getId());
		}
	}
}
