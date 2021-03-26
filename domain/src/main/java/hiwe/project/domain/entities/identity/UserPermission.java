package ca.project.domain.entities.identity;

public enum UserPermission {
	IDENTITY_MODULE("Identity Module", "Module that handles users, roles and permissions");
	
	private String name;
	private String description;
	
	private UserPermission(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
