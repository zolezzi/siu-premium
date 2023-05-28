package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterFilterDTO;
import unq.edu.li.pdes.unqpremium.exception.DegreeNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.SemesterNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.SubjectNotFoundException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.model.SemesterDegreeSubject;
import unq.edu.li.pdes.unqpremium.model.SemesterType;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.repository.SemesterDegreeSubjectRepository;
import unq.edu.li.pdes.unqpremium.repository.SemesterRepository;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.SemesterService;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService{

	private final SemesterRepository repository;
	private final DegreeRepository degreeRepository;
	private final SubjectRepository subjectRepository;
	private final SemesterDegreeSubjectRepository semesterDegreeSubjectRepository;
	private final Mapper mapper;
	
	@Override
	public SemesterDTO findSemesterById(Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		return mapper.map(semesterDB, SemesterDTO.class);
	}

	@Override
	@Transactional
	public void deleteSemesterById(Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		List<SemesterDegreeSubject> listElements = semesterDegreeSubjectRepository.findBySemesterId(semesterId);
		semesterDegreeSubjectRepository.deleteAll(listElements);
		repository.delete(semesterDB);
	}

	@Override
	@Transactional
	public SemesterDTO saveSemester(SemesterVO semester) {
		var semesterDB = repository.save(mapper.map(semester, Semester.class));
		createSemesterWithDegreesAndSubjects(semester, semesterDB);
		return mapper.map(semesterDB, SemesterDTO.class);
	}

	@Override
	public SemesterDTO updateSemester(SemesterDTO semester, Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		semesterDB = mapper.map(semester, Semester.class);
		semesterDB.setId(semesterId);
		return mapper.map(repository.save(semesterDB), SemesterDTO.class);
	}

	@Override
	public List<SemesterDTO> searchSemestersByFilter(SemesterFilterDTO filter) {
		if(filter == null) {
			throw new SemesterNotFoundException("Semester not found");
		}
		var semesterType = filter.getSemesterType() == null ? null : SemesterType.valueOf(filter.getSemesterType());
		return repository.searchSemestersByFilter(filter.getYear(), semesterType)
				.stream()
				.map((semester -> mapper.map(semester, SemesterDTO.class)))
				.collect(Collectors.toList());
	}
	
	private Semester getSemesterById(Long semesterId) {
		var semesterIdOpt = repository.findById(semesterId);
		if(semesterIdOpt.isEmpty()) {
			throw new SemesterNotFoundException("Semester not found");
		}
		return semesterIdOpt.get();
	}

	private void createSemesterWithDegreesAndSubjects(SemesterVO semesterVO, Semester semesterDB) {
		List<SemesterDegreeSubject> list = createRelationshipWithSemesterDegreeAndSubject(semesterDB, semesterVO);
		semesterDegreeSubjectRepository.saveAll(list);
	}

	private List<SemesterDegreeSubject> createRelationshipWithSemesterDegreeAndSubject(Semester semesterDB,
			SemesterVO semesterVO) {
		return semesterVO.getSubjects()
				.stream()
				.map(subject -> createSemesterDegreeAndSubjectAndLoadSubjectAndDegreeById(subject.getId(), subject.getDegreeId(), semesterDB))
				.collect(Collectors.toList());
	}

	private SemesterDegreeSubject createSemesterDegreeAndSubjectAndLoadSubjectAndDegreeById(Long subjectId, Long degreeId, Semester semesterDB) {
		var subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new SubjectNotFoundException(String.format("No found subject :%s", subjectId)));
		var degree = degreeRepository.findById(degreeId)
				.orElseThrow(() -> new DegreeNotFoundException(String.format("No found degree:%s", degreeId)));
		var semesterDegreeSubject = new SemesterDegreeSubject();
		semesterDegreeSubject.setSubject(subject);
		semesterDegreeSubject.setDegree(degree);
		semesterDegreeSubject.setSemester(semesterDB);
		return semesterDegreeSubject;
	}

}
