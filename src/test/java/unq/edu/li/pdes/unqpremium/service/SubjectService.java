package unq.edu.li.pdes.unqpremium.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Subject;
import unq.edu.li.pdes.unqpremium.repository.DegreeRepository;
import unq.edu.li.pdes.unqpremium.repository.SubjectRepository;
import unq.edu.li.pdes.unqpremium.service.impl.SubjectServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SubjectService {

	@Mock
	private Subject subject;
	
	@Mock
	private SubjectDTO subjectDto;
	
	@Mock
	private SubjectRepository repository;
	
	@Mock
	private DegreeRepository degreeRepository;
	
	@Mock
	private Mapper mapper;
	
	@InjectMocks
	private SubjectServiceImpl service;
}
