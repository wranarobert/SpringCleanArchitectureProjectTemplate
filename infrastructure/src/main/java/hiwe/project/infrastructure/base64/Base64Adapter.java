package ca.project.infrastructure.base64;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Base64Adapter implements IBase64 {

	@Override
	public String encodeBase64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	@Override
	public byte[] decodeBase64(String base64) {
		return Base64.decodeBase64(base64);
	}

}
