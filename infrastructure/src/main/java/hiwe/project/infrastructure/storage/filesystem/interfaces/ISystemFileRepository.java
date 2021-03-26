package ca.project.infrastructure.storage.filesystem.interfaces;

import ca.project.infrastructure.storage.filesystem.models.SystemFile;

// TODO: Not implemented yet
public interface ISystemFileRepository {
	SystemFile getFileDocument(String id);
	SystemFile saveFileDocument(SystemFile file);
	void deleteFileDocument(SystemFile file);
}
