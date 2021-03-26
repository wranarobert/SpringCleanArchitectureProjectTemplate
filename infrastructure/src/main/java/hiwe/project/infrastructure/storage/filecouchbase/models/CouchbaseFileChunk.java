package ca.project.infrastructure.storage.filecouchbase.models;

public class CouchbaseFileChunk {
	private String id;
	private String byteChunk;
	
	public CouchbaseFileChunk() {}
	
	public CouchbaseFileChunk(String id, String byteChunk) {
		super();
		this.id = id;
		this.byteChunk = byteChunk;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getByteChunk() {
		return byteChunk;
	}

	public void setByteChunk(String byteChunk) {
		this.byteChunk = byteChunk;
	}
}
