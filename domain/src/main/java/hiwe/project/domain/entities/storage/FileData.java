package ca.project.domain.entities.storage;

public class FileData {
	private String fileName;
	private byte[] fileBytes;
	
	public FileData() {}

	public FileData(String fileName, byte[] fileBytes) {
		super();
		this.fileName = fileName;
		this.fileBytes = fileBytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
}
