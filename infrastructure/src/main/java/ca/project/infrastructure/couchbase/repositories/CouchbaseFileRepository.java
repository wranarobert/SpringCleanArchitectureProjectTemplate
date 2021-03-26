package ca.project.infrastructure.couchbase.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import ca.project.infrastructure.couchbase.entitydefinition.file.CouchbaseFileChunkDefinition;
import ca.project.infrastructure.couchbase.entitydefinition.file.CouchbaseFileDefinition;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileRepository;
import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFile;
import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFileChunk;

@Repository
public class CouchbaseFileRepository implements ICouchbaseFileRepository {
	Logger logger = LoggerFactory.getLogger(CouchbaseFileRepository.class);

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CouchbaseTemplate couchbaseTemplate;
	
	public CouchbaseFile getFileDocument(String id) {
		return couchbaseTemplate.findById(id, CouchbaseFile.class);
	}
	
	public CouchbaseFile saveFileDocument(CouchbaseFile file) {
		CouchbaseFileDefinition fileDefinition = modelMapper.map(file, CouchbaseFileDefinition.class);
		
		couchbaseTemplate.save(fileDefinition);
		
		return fileDefinition;
	}

	public List<CouchbaseFileChunk> getFileChunks(List<String> fileChunkIds) {
		return fileChunkIds.stream()
			.map(id -> 
				couchbaseTemplate.findById(id, CouchbaseFileChunk.class)
			)
			.collect(Collectors.toList());
	}
	
	public List<String> saveFileChunks(List<CouchbaseFileChunk> fileChunks) {
		List<CouchbaseFileChunkDefinition> fileChunkDefinitions = fileChunks.stream()
			.map(fileChunk -> modelMapper.map(fileChunk, CouchbaseFileChunkDefinition.class))
			.collect(Collectors.toList());

		couchbaseTemplate.save(fileChunkDefinitions);
		
		List<String> fileChunkIds = fileChunkDefinitions.stream()
			.map(fileChunk -> fileChunk.getId())
			.collect(Collectors.toList());
		
		return fileChunkIds;
	}

	@Override
	public void deleteFileDocument(CouchbaseFile file) {
		couchbaseTemplate.remove(file);
	}

	@Override
	public void deleteFileChunks(List<CouchbaseFileChunk> fileChunks) {
		couchbaseTemplate.remove(fileChunks);
	}
}
