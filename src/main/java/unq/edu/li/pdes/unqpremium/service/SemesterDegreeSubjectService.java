package unq.edu.li.pdes.unqpremium.service;

import java.util.List;

import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectFilterDTO;

public interface SemesterDegreeSubjectService {

	List<SemesterDegreeSubjectDTO> searchByFilter(SemesterDegreeSubjectFilterDTO filter);
	
	void deleteById(Long semesterDegreeSubjectId, Long semesterId, Long degreeId, Long subjectId);
}
