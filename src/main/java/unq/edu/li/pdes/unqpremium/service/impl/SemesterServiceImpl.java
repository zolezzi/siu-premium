package unq.edu.li.pdes.unqpremium.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.exception.SemesterNotFoundException;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.repository.SemesterRepository;
import unq.edu.li.pdes.unqpremium.service.SemesterService;
import unq.edu.li.pdes.unqpremium.utils.MapperUtil;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService{

	private final SemesterRepository repository;
	private final MapperUtil mapperUtil;
	
	@Override
	public SemesterDTO findSemesterById(Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		return mapperUtil.getMapper().map(semesterDB, SemesterDTO.class);
	}

	@Override
	public void deleteSemesterById(Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		repository.delete(semesterDB);
	}

	@Override
	public SemesterDTO saveSemester(SemesterVO semester) {
		var semesterDB = repository.save(mapperUtil.getMapper().map(semester, Semester.class));
		return mapperUtil.getMapper().map(semesterDB, SemesterDTO.class);
	}

	@Override
	public SemesterDTO updateSemester(SemesterDTO semester, Long semesterId) {
		var semesterDB = getSemesterById(semesterId);
		semesterDB = mapperUtil.getMapper().map(semester, Semester.class);
		return mapperUtil.getMapper().map(repository.save(semesterDB), SemesterDTO.class);
	}

	private Semester getSemesterById(Long semesterId) {
		var semesterIdOpt = repository.findById(semesterId);
		if(semesterIdOpt.isEmpty()) {
			throw new SemesterNotFoundException("Semester not found");
		}
		return semesterIdOpt.get();
	}
}
