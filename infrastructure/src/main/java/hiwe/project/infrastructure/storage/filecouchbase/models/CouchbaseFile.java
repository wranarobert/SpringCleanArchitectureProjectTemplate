package ca.project.infrastructure.storage.filecouchbase.models;

import java.util.List;

import ca.project.domain.entities.storage.FileMetadata;

public class CouchbaseFile extends FileMetadata {
	private List<String> fileChunkIds;
	
	public CouchbaseFile() {}
	
	public CouchbaseFile(String originalFilename, List<String> fileChunkIds) {
		super(null, originalFilename);
		this.fileChunkIds = fileChunkIds;
	}

	public CouchbaseFile(String id, String originalFilename, List<String> fileChunkIds) {
		super(id, originalFilename);
		this.fileChunkIds = fileChunkIds;
	}

	public List<String> getFileChunkIds() {
		return fileChunkIds;
	}

	public void setFileChunkIds(List<String> fileChunkIds) {
		this.fileChunkIds = fileChunkIds;
	}
}
