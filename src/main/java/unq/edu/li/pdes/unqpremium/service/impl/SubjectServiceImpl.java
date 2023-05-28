package unq.edu.li.pdes.unqpremium.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.exception.DegreeNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.SubjectNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.repository.DegreeReportRepository;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.SubjectService;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{

	private final SubjectRepository repository;
	private final DegreeRepository degreeRepository;
	private final DegreeReportRepository reportRepository;
	private final Mapper mapper;
	
	@Override
	public SubjectDTO findById(Long subjectId) {
		var subjectDB = getSubjectById(subjectId);
		return mapper.map(subjectDB, SubjectDTO.class);
	}

	@Override
	public void deleteById(Long degreeId, Long subjectId) throws RuntimeException {
		var subjectDB = getSubjectById(subjectId);
		var degreeBD = degreeRepository.findById(degreeId)
				.orElseThrow(() -> new DegreeNotFoundException(String.format("No found degree:%s", degreeId)));
		if(!degreeBD.getContainsBySubjectId(subjectId)) {
			throw new UnqPremiumException(String.format("No found subject with id :%s, for degree with id:%s ", subjectId, degreeId));
		}
		degreeBD.getSubjects().remove(subjectDB);
		degreeRepository.save(degreeBD);
		repository.delete(subjectDB);
	}

	@Override
	public SubjectDTO save(SubjectVO subject) {
		var subjectDB = repository.save(mapper.map(subject, Subject.class));
		var degreeBD = degreeRepository.findById(subject.getDegreeId())
				.orElseThrow(() -> new DegreeNotFoundException(String.format("No found degree:%s", subject.getDegreeId())));
		degreeBD.getSubjects().add(subjectDB);
		degreeRepository.save(degreeBD);
		subjectDB.setDegree(degreeBD);
		repository.save(subjectDB);
		return mapper.map(subjectDB, SubjectDTO.class);
	}

	@Override
	public SubjectDTO update(SubjectDTO subject, Long degreeId, Long subjectId) {
		var subjectDB = getSubjectById(subjectId);
		var degreeBD = degreeRepository.findById(degreeId)
				.orElseThrow(() -> new DegreeNotFoundException(String.format("No found degree:%s", degreeId)));
		if(!degreeBD.getContainsBySubjectId(subjectId)) {
			throw new UnqPremiumException(String.format("No found subject with id :%s, for degree with id:%s ", subjectId, degreeId));
		}
		subjectDB = mapper.map(subject, Subject.class);
		return mapper.map(repository.save(subjectDB), SubjectDTO.class);
	}

	@Override
	public List<SubjectDTO> searchByFilter(DegreeFilterDTO filter) {
		if(filter == null) {
			throw new UnqPremiumException("Semester not found");
		}
		return reportRepository.searchSubjectByFilter(filter)
				.stream()
				.map(subject -> mapper.map(subject, SubjectDTO.class))
				.collect(Collectors.toList());
	}
	
	private Subject getSubjectById(Long subjectId) {
		var subjectIdOpt = repository.findById(subjectId);
		if(subjectIdOpt.isEmpty()) {
			throw new SubjectNotFoundException(String.format("Subject not found with id:%s ", subjectId));
		}
		return subjectIdOpt.get();
	}

}
