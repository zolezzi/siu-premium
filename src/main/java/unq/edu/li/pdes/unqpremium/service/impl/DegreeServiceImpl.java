package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.service.DegreeService;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService{

	private final DegreeRepository repository;
	private final Mapper mapper;
	
	@Override
	public List<DegreeDTO> findAll() {
		return mapper.mapList(repository.findAll(), DegreeDTO.class);
	}

}
