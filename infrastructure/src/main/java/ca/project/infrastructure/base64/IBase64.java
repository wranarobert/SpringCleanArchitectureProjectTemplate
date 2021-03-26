package ca.project.infrastructure.base64;

public interface IBase64 {
	String encodeBase64(byte[] bytes);
	byte[] decodeBase64(String base64);
}
