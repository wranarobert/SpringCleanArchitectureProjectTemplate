package ca.project.infrastructure.storage.filecouchbase;

import java.util.List;

import ca.project.domain.entities.storage.FileData;
import ca.project.domain.entities.storage.FileMetadata;
import ca.project.domain.exceptions.FileStorageException;
import ca.project.domain.external.infrastructure.storage.IFileStorage;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileManager;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileRepository;
import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFile;
import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFileChunk;

public class CouchbaseFileStorageAdapter implements IFileStorage {
	private final ICouchbaseFileRepository couchbaseFileRepository;
	private final ICouchbaseFileManager fileChunkManager;
	
	public CouchbaseFileStorageAdapter(
		ICouchbaseFileManager fileChunkManager,
		ICouchbaseFileRepository couchbaseFileRepository
	) {
		this.fileChunkManager = fileChunkManager;
		this.couchbaseFileRepository = couchbaseFileRepository;
	}
	
	public FileMetadata saveFile(FileMetadata fileMetadata, byte[] fileBytes) {
		List<CouchbaseFileChunk> fileChunks = fileChunkManager.splitToChunks(fileBytes);
		
		List<String> fileChunkIds = couchbaseFileRepository.saveFileChunks(fileChunks);
		
		if(fileChunkIds.stream().anyMatch(id -> id == null)) {
			while(fileChunkIds.remove(null));
			
			List<CouchbaseFileChunk> chunksToDelete = couchbaseFileRepository.getFileChunks(fileChunkIds);
			
			couchbaseFileRepository.deleteFileChunks(chunksToDelete);
			
			throw new FileStorageException("There was a problem with saving a file");
		}
		
		CouchbaseFile couchFile = couchbaseFileRepository.saveFileDocument(
			new CouchbaseFile(
				fileMetadata.getOriginalFilename(), 
				fileChunkIds
			)
		);
		
		return couchFile;
	}

	public FileData getFile(String id) {
		CouchbaseFile fileDocument = couchbaseFileRepository.getFileDocument(id);
		
		if(fileDocument == null) {
			throw new FileStorageException("File not found");
		}
		
		List<CouchbaseFileChunk> fileChunks = couchbaseFileRepository.getFileChunks(fileDocument.getFileChunkIds());
		
		return new FileData(
			fileDocument.getOriginalFilename(),
			fileChunkManager.joinChunks(fileChunks)
		);
	}
	
	public void deleteFile(String id) {
		CouchbaseFile fileDocument = couchbaseFileRepository.getFileDocument(id);
		
		if(fileDocument == null) {
			throw new FileStorageException("File not found");
		}
		
		List<CouchbaseFileChunk> fileChunks = couchbaseFileRepository.getFileChunks(fileDocument.getFileChunkIds());
		
		couchbaseFileRepository.deleteFileChunks(fileChunks);
		
		couchbaseFileRepository.deleteFileDocument(fileDocument);
	}

}
