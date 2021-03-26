package ca.project.infrastructure.storage.filecouchbase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ca.project.infrastructure.base64.IBase64;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileManager;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileStorageProperties;
import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFileChunk;

public class CouchbaseFileManager implements ICouchbaseFileManager {
	private final int chunkSize;
	
	private final IBase64 base64;
	
	public CouchbaseFileManager(IBase64 base64, ICouchbaseFileStorageProperties properties) {
		this.base64 = base64;
		this.chunkSize = properties.getChunkSize() * 1024 * 1024;
	}
	
	public List<CouchbaseFileChunk> splitToChunks(byte[] fileBytes) {
		return splitBytes(fileBytes, chunkSize)
			.stream()
			.map(byteChunk -> new CouchbaseFileChunk(null, base64.encodeBase64(byteChunk)))
			.collect(Collectors.toList());
	}
	
	public byte[] joinChunks(List<CouchbaseFileChunk> fileChunks) {
		return fileChunks.stream()
			.map(fileChunk -> base64.decodeBase64(fileChunk.getByteChunk()))
			.collect(
		        () -> new ByteArrayOutputStream(),
		        (b, e) -> {
		            try {
		                b.write(e);
		            } catch (IOException e1) {
		                throw new RuntimeException(e1);
		            }
		        },
		        (a, b) -> {}
		    )
			.toByteArray();
	}
	
	private List<byte[]> splitBytes(byte[] source, int chunksize) {
		if(source.length < chunksize) {
			return Arrays.asList(source);
		}
		
		List<byte[]> result = new ArrayList<byte[]>();
	    int start = 0;
	    
	    while (start < source.length) {
	        int end = Math.min(source.length, start + chunksize);
	        result.add(Arrays.copyOfRange(source, start, end));
	        start += chunksize;
	    }

	    return result;
    }
}
