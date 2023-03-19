package unq.edu.li.pdes.unqpremium.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import unq.edu.li.pdes.unqpremium.dto.SemesterDTO;
import unq.edu.li.pdes.unqpremium.model.Semester;
import unq.edu.li.pdes.unqpremium.vo.SemesterVO;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.configure();
    }

    public ModelMapper getMapper() {
        return this.modelMapper;
    }
    
    private void configure() {
        // SemesterDTO config
        this.modelMapper.typeMap(Semester.class, SemesterDTO.class).addMappings(mapper -> {
            mapper.map(Semester::getId, SemesterDTO::setId);
            mapper.map(Semester::getName, SemesterDTO::setName);
            mapper.map(Semester::getFromDate, SemesterDTO::setFromDate);
            mapper.map(Semester::getToDate, SemesterDTO::setToDate);
        });
        this.modelMapper.typeMap(SemesterDTO.class, Semester.class).addMappings(mapper -> {
            mapper.map(SemesterDTO::getName, Semester::setName);
            mapper.map(SemesterDTO::getFromDate, Semester::setFromDate);
            mapper.map(SemesterDTO::getToDate, Semester::setToDate);
        });
        this.modelMapper.typeMap(SemesterVO.class, SemesterDTO.class).addMappings(mapper -> {
            mapper.map(SemesterVO::getName, SemesterDTO::setName);
            mapper.map(SemesterVO::getFromDate, SemesterDTO::setFromDate);
            mapper.map(SemesterVO::getToDate, SemesterDTO::setToDate);
        });
    }
}
