package unq.edu.li.pdes.unqpremium.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.CommitteeDTO;
import unq.edu.li.pdes.unqpremium.exception.CommitteeNotFoundException;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Committee;
import unq.edu.li.pdes.unqpremium.repository.CommitteeRepository;
import unq.edu.li.pdes.unqpremium.repository.SemesterDegreeSubjectRepository;
import unq.edu.li.pdes.unqpremium.service.CommitteeService;
import unq.edu.li.pdes.unqpremium.vo.CommitteeVO;

@Service
@RequiredArgsConstructor
public class CommitteeServiceImpl implements CommitteeService{

	private final CommitteeRepository repository;
	private final SemesterDegreeSubjectRepository semesterDegreeSubjectRepository;
	private final Mapper mapper;
	
	@Override
	public CommitteeDTO findById(Long committeeId) {
		var committeeDB = getCommitteeById(committeeId);
		return mapper.map(committeeDB, CommitteeDTO.class);
	}
	
	@Override
	@Transactional
	public void deleteById(Long semesterDegreeSubjectId, Long committeeId) {
		var semesterDegreeSubject = semesterDegreeSubjectRepository.findById(semesterDegreeSubjectId)
				.orElseThrow(() -> new UnqPremiumException(String.format("No found relationship with semester, degree or subject with:%s ", semesterDegreeSubjectId)));
		var committeeDB = getCommitteeById(committeeId);
		if(!semesterDegreeSubject.getCommittees().contains(committeeDB)) {
			throw new UnqPremiumException(String.format("No found committee with id :%s and not found relationship with semester, degree or subject with:%s ", committeeId, semesterDegreeSubjectId));
		}
		semesterDegreeSubject.getCommittees().remove(committeeDB);
		semesterDegreeSubjectRepository.save(semesterDegreeSubject);
		repository.delete(committeeDB);
	}
	
	@Override
	@Transactional
	public CommitteeDTO save(CommitteeVO committeevo) {
		var semesterDegreeSubject = semesterDegreeSubjectRepository.findById(committeevo.getSemesterDegreeSubjectId())
				.orElseThrow(() -> new UnqPremiumException(String.format("No found relationship with semester, degree or subject with:%s ", committeevo.getSemesterDegreeSubjectId())));
		var aNewCommittee = mapper.map(committeevo, Committee.class);
		aNewCommittee = repository.save(aNewCommittee);
		semesterDegreeSubject.getCommittees().add(aNewCommittee);
		semesterDegreeSubjectRepository.save(semesterDegreeSubject);
		return mapper.map(aNewCommittee, CommitteeDTO.class);
	}
	
	@Override
	public CommitteeDTO update(CommitteeDTO committeeDto, Long semesterDegreeSubjectId, Long committeeId) {
		var semesterDegreeSubject = semesterDegreeSubjectRepository.findById(semesterDegreeSubjectId)
				.orElseThrow(() -> new UnqPremiumException(String.format("No found relationship with semester, degree or subject with:%s ", semesterDegreeSubjectId)));
		var committeeDB = getCommitteeById(committeeId);
		if(!semesterDegreeSubject.getCommittees().contains(committeeDB)) {
			throw new UnqPremiumException(String.format("No found committee with id :%s and not found relationship with semester, degree or subject with:%s ", committeeId, semesterDegreeSubjectId));
		}
		committeeDB = repository.save(mapper.map(committeeDto, Committee.class));
		return  mapper.map(committeeDB, CommitteeDTO.class);
	}
	
	private Committee getCommitteeById(Long committeeId) {
		var committeeIdOpt = repository.findById(committeeId);
		if(committeeIdOpt.isEmpty()) {
			throw new CommitteeNotFoundException(String.format("Committee not found with id:%s ", committeeId));
		}
		return committeeIdOpt.get();
	}
}
