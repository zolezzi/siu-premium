package unq.edu.li.pdes.unqpremium.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.exception.SubjectNotFoundException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.SubjectService;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{

	private final SubjectRepository repository;
	private final Mapper mapper;
	
	@Override
	public SubjectDTO findById(Long subjectId) {
		var subjectDB = getSubjectById(subjectId);
		return mapper.map(subjectDB, SubjectDTO.class);
	}

	@Override
	public void deleteById(Long subjectId) {
		var subjectDB = getSubjectById(subjectId);
		repository.delete(subjectDB);
	}

	@Override
	public SubjectDTO save(SubjectVO subject) {
		var subjectDB = repository.save(mapper.map(subject, Subject.class));
		return mapper.map(subjectDB, SubjectDTO.class);
	}

	@Override
	public SubjectDTO update(SubjectDTO subject, Long subjectId) {
		var subjectDB = getSubjectById(subjectId);
		subjectDB = mapper.map(subject, Subject.class);
		return mapper.map(repository.save(subjectDB), SubjectDTO.class);
	}

	private Subject getSubjectById(Long subjectId) {
		var subjectIdOpt = repository.findById(subjectId);
		if(subjectIdOpt.isEmpty()) {
			throw new SubjectNotFoundException("subject not found");
		}
		return subjectIdOpt.get();
	}
}
