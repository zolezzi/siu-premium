package unq.edu.li.pdes.unqpremium.service;

import java.util.List;

import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;

public interface DegreeService {

	List<DegreeDTO> findAll();
	List<DegreeDTO> findAllDegreeByFilter(DegreeFilterDTO filter);
}
