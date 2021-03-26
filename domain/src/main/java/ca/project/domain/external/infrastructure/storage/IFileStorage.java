package ca.project.domain.external.infrastructure.storage;

import ca.project.domain.entities.storage.FileData;
import ca.project.domain.entities.storage.FileMetadata;

public interface IFileStorage {
	FileData getFile(String id);
	FileMetadata saveFile(FileMetadata fileMetadata, byte[] fileBytes);
	void deleteFile(String id);
}
