package ca.project.infrastructure.security.factories;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.entities.identity.ApplicationUser;

@Component
public class ApplicationUserFactory {
	@Autowired
	PermissionsFactory permissionsFactory;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ApplicationUserDTO create(ApplicationUserDTO userEntity) {
        var userDTO = modelMapper.map(userEntity, ApplicationUserDTO.class);
        
        userDTO.setPermissions(permissionsFactory.create(userDTO.getRoles()));
        
        return userDTO;
	}
}
