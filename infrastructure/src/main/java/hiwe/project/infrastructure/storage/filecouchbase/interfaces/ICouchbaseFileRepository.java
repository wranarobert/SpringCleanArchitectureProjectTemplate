package ca.project.infrastructure.storage.filecouchbase.interfaces;

import java.util.List;

import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFile;
import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFileChunk;

public interface ICouchbaseFileRepository {
	CouchbaseFile getFileDocument(String id);
	CouchbaseFile saveFileDocument(CouchbaseFile file);
	void deleteFileDocument(CouchbaseFile file);
	
	List<CouchbaseFileChunk> getFileChunks(List<String> fileChunkIds);
	List<String> saveFileChunks(List<CouchbaseFileChunk> fileChunks);
	void deleteFileChunks(List<CouchbaseFileChunk> fileChunks);
}
