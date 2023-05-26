package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectFilterDTO;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.SemesterDegreeSubject;
import unq.edu.li.pdes.unqpremium.repository.SemesterDegreeSubjectReportRepository;
import unq.edu.li.pdes.unqpremium.repository.SemesterDegreeSubjectRepository;
import unq.edu.li.pdes.unqpremium.service.SemesterDegreeSubjectService;

@Service
@RequiredArgsConstructor
public class SemesterDegreeSubjectServiceImpl implements SemesterDegreeSubjectService{

	private final SemesterDegreeSubjectRepository repository;
	private final SemesterDegreeSubjectReportRepository reportRepository;
	private final Mapper mapper;
	
	@Override
	public List<SemesterDegreeSubjectDTO> searchByFilter(SemesterDegreeSubjectFilterDTO filter) {
		if(filter == null) {
			throw new UnqPremiumException("Semester not found");
		}
		return reportRepository.search(filter)
				.stream()
				.map(sds -> mapper.map(sds, SemesterDegreeSubjectDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long semesterDegreeSubjectId, Long semesterId, Long degreeId, Long subjectId) {
		var semesterDegreeSubjectDB = getSemesterDegreeSubjectById(semesterDegreeSubjectId);
		if(!semesterDegreeSubjectDB.getSubject().getId().equals(subjectId) 
				|| !semesterDegreeSubjectDB.getSemester().getId().equals(semesterId)
				|| !semesterDegreeSubjectDB.getDegree().getId().equals(degreeId)) {
			throw new UnqPremiumException(String.format("Semester, Degree and Subject not found with id:%s ", semesterDegreeSubjectId));
		}
		repository.delete(semesterDegreeSubjectDB);
	}
	
	private SemesterDegreeSubject getSemesterDegreeSubjectById(Long semesterDegreeSubjectId) {
		var semesterDegreeSubjectIdOpt = repository.findById(semesterDegreeSubjectId);
		if(semesterDegreeSubjectIdOpt.isEmpty()) {
			throw new UnqPremiumException(String.format("Semester, Degree and Subject not found with id:%s ", semesterDegreeSubjectId));
		}
		return semesterDegreeSubjectIdOpt.get();
	}
}
