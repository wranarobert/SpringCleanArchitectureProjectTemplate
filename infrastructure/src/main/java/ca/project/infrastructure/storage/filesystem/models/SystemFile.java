package ca.project.infrastructure.storage.filesystem.models;

import java.nio.file.Path;

import ca.project.domain.entities.storage.FileMetadata;

public class SystemFile extends FileMetadata {
	private Path filePath;
	
	public SystemFile() {}
	
	public SystemFile(String orginalFilename, Path filePath) {
		super(null, orginalFilename);
		this.filePath = filePath;
	}

	public SystemFile(String id, String orginalFilename, Path filePath) {
		super(id, orginalFilename);
		this.filePath = filePath;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
}
