package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterFilterDTO;
import unq.edu.li.pdes.unqpremium.exception.SemesterNotFoundException;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.repository.SemesterRepository;
import unq.edu.li.pdes.unqpremium.service.SemesterService;
import unq.edu.li.pdes.unqpremium.utils.Mapper;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService{

	private final SemesterRepository repository;
	private final Mapper mapper;
	
	@Override
	public SemesterDTO findSemesterById(Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		return mapper.map(semesterDB, SemesterDTO.class);
	}

	@Override
	public void deleteSemesterById(Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		repository.delete(semesterDB);
	}

	@Override
	public SemesterDTO saveSemester(SemesterVO semester) {
		var semesterDB = repository.save(mapper.map(semester, Semester.class));
		return mapper.map(semesterDB, SemesterDTO.class);
	}

	@Override
	public SemesterDTO updateSemester(SemesterDTO semester, Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		semesterDB = mapper.map(semester, Semester.class);
		return mapper.map(repository.save(semesterDB), SemesterDTO.class);
	}

	@Override
	public List<SemesterDTO> searchSemestersByFilter(SemesterFilterDTO filter) {
		if(filter == null) {
			throw new SemesterNotFoundException("Semester not found");
		}
		return repository.searchSemesterByFilter(filter.getYear())
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
	
}
