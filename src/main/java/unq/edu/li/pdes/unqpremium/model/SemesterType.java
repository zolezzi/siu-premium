package unq.edu.li.pdes.unqpremium.model;

public enum SemesterType {

	FIRST ("Primer cuatrimeste"),
	SECOND("Segundo cuatrimeste");
	
	private String description;
	
	// private enum constructor
	private SemesterType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
