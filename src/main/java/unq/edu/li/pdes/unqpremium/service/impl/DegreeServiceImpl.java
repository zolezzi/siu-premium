package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.exception.DegreeNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.repository.DegreeReportRepository;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.DegreeService;
import unq.edu.li.pdes.unqpremium.vo.DegreeVO;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService{

	private final DegreeRepository repository;
	private final SubjectRepository subjectRepository;
	private final DegreeReportRepository reportRepository;
	private final Mapper mapper;
	

	@Override
	public DegreeDTO findById(Long degreeId) {
		var degreeDB = getDegreeById(degreeId);
		return mapper.map(degreeDB, DegreeDTO.class);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		var degreeDB = getDegreeById(id);
		subjectRepository.deleteAll(degreeDB.getSubjects());
		repository.delete(degreeDB);
	}

	@Override
	public DegreeDTO save(DegreeVO degree) {
		var degreeDB = repository.save(mapper.map(degree, Degree.class));
		repository.save(degreeDB);
		return mapper.map(degreeDB, DegreeDTO.class);
	}

	@Override
	public DegreeDTO update(DegreeDTO degree, Long degreeId) {
		var degreeDB = getDegreeById(degreeId);
		degreeDB = mapper.map(degree, Degree.class);
		return mapper.map(repository.save(degreeDB), DegreeDTO.class);
	}

	@Override
	public List<DegreeDTO> searchByFilter(DegreeFilterDTO filter) {
		if(filter == null) {
			throw new UnqPremiumException("Degree not found");
		}
		return reportRepository.search(filter)
				.stream()
				.map(degree -> mapper.map(degree, DegreeDTO.class))
				.collect(Collectors.toList());
	}
	
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

	private Degree getDegreeById(Long degreeId) {
		var degreeIdOpt = repository.findById(degreeId);
		if(degreeIdOpt.isEmpty()) {
			throw new DegreeNotFoundException(String.format("Degree not found with id:%s ", degreeId));
		}
		return degreeIdOpt.get();
	}
}
