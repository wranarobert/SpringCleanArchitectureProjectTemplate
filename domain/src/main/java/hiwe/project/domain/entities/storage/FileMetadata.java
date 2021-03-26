package ca.project.domain.entities.storage;

public class FileMetadata {
	private String id;
	private String originalFilename;
	
	public FileMetadata() {}
	
	public FileMetadata(String originalFilename) {
		super();
		this.id = null;
		this.originalFilename = originalFilename;
	}
	
	public FileMetadata(String id, String originalFilename) {
		super();
		this.id = id;
		this.originalFilename = originalFilename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

}
