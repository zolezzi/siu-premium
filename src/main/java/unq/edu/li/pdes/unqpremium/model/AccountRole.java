package unq.edu.li.pdes.unqpremium.model;

public enum AccountRole {

	ADMIN("ADMIN"),
	PROFESSOR("PROFESOR"),
	STUDENT("ESTUDIANTE");
	
	private String description;
	
	// private enum constructor
	private AccountRole(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
