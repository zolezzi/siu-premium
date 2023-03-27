package unq.edu.li.pdes.unqpremium.service;

import java.util.List;

import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterFilterDTO;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

public interface SemesterService {

	SemesterDTO findSemesterById(Long semesterId);
	
	void deleteSemesterById(Long id);
	
	SemesterDTO saveSemester(SemesterVO semester);
	
	SemesterDTO updateSemester(SemesterDTO semester, Long semesterId);
	
	List<SemesterDTO> searchSemestersByFilter(SemesterFilterDTO filter);
}
