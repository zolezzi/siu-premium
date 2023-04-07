package unq.edu.li.pdes.unqpremium.service;

import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;

public interface SubjectService {

	SubjectDTO findById(Long subjectId);
	
	void deleteById(Long degreeId, Long id) throws Exception;
	
	SubjectDTO save(SubjectVO subject);
	
	SubjectDTO update(SubjectDTO subject, Long degreeId, Long subjectId);
}
