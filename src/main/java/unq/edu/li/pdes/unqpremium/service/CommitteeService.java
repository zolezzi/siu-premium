package unq.edu.li.pdes.unqpremium.service;

import unq.edu.li.pdes.unqpremium.dto.CommitteeDTO;
import unq.edu.li.pdes.unqpremium.vo.CommitteeVO;

public interface CommitteeService {

	CommitteeDTO findById(Long committeeId);
	
	void deleteById(Long semesterDegreeSubjectId, Long committeeId);
	
	CommitteeDTO save(CommitteeVO committee);
	
	CommitteeDTO update(CommitteeDTO committee, Long semesterDegreeSubjectId, Long committeeId);
}
