package ca.project.infrastructure.storage.filecouchbase.interfaces;

import java.util.List;

import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFileChunk;

public interface ICouchbaseFileManager {
	public List<CouchbaseFileChunk> splitToChunks(byte[] fileBytes);
	public byte[] joinChunks(List<CouchbaseFileChunk> fileChunks);
}
