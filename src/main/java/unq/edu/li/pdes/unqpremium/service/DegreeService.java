package unq.edu.li.pdes.unqpremium.service;

import java.util.List;

import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.vo.DegreeVO;

public interface DegreeService {

	DegreeDTO findById(Long degreeId);
	
	void deleteById(Long degreeId) throws Exception;
	
	DegreeDTO save(DegreeVO degree);
	
	DegreeDTO update(DegreeDTO degree, Long degreeId);
	
	List<DegreeDTO> findAll();
	
	List<DegreeDTO> findAllDegreeByFilter(DegreeFilterDTO filter);
	
	List<DegreeDTO> searchByFilter(DegreeFilterDTO filter);
	
}
