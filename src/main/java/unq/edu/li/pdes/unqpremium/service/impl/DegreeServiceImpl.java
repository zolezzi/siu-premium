package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.model.Subject;
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

	public List<DegreeDTO> findAllDegreeByFilter(DegreeFilterDTO filter) {
		return repository.findAllById(filter.getDegreeIds())
				.stream()
				.map(degree -> buildListSubjectsByDegree(degree))
				.collect(Collectors.toList());
	}
	
	private DegreeDTO buildListSubjectsByDegree(Degree degree) {
		var degreeMapper =  mapper.map(degree, DegreeDTO.class);
		degreeMapper.setSubjects(mapperSujectToDTO(degree));
		return degreeMapper;
	}

	private List<SubjectDTO> mapperSujectToDTO(Degree degree) {
		return degree
				.getSubjects()
				.stream()
				.map(subject -> mapperSubjectWithDegree(subject, degree))
				.collect(Collectors.toList());
	}

	private SubjectDTO mapperSubjectWithDegree(Subject subject, Degree degree) {
		var subjectMapper = mapper.map(subject, SubjectDTO.class);
		subjectMapper.setDegreeId(degree.getId());
		return subjectMapper;
	}
}
