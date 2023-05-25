package unq.edu.li.pdes.unqpremium.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.controller.response.BasicResponse;
import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectDTO;
import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectFilterDTO;
import unq.edu.li.pdes.unqpremium.service.impl.SemesterDegreeSubjectServiceImpl;

@RestController("semester-degree-subject")
@Api(value = "SemesterDegreeSubject Service")
@RequestMapping("/semester-degree-subject")
@RequiredArgsConstructor
public class SemesterDegreeSubjectController {

	private final SemesterDegreeSubjectServiceImpl service;
	
    @ApiOperation(
            value = "Service that returns all objects relationship with semester, degrees and subjects by semester id",
            notes = "This service returns all objects and load relationship with semester, degrees and subjects by semester id",
            nickname = "searchByFilter",
            response = SemesterDegreeSubjectDTO.class, 
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = SemesterDegreeSubjectDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = SemesterDegreeSubjectDTO.class, responseContainer = "List") })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/search-by-filter",
            produces = { "application/json" }
    )
    public List<SemesterDegreeSubjectDTO> searchByFilter(@RequestBody SemesterDegreeSubjectFilterDTO filter){
        return service.searchByFilter(filter);
    }
    
    @ApiOperation(
            value = "This service delete a Subject",
            notes = "Delete a Subject, if it doesn't find it throw an exception",
            nickname = "deleteById",
            response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = BasicResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = BasicResponse.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping(
            value = "/delete/{semesterDegreeSubjectId}/{semesterId}/{degreeId}/{subjectId}",
            produces = { "application/json" }
    )
    public BasicResponse deleteById(@PathVariable("semesterDegreeSubjectId") Long semesterDegreeSubjectId, @PathVariable("semesterId") Long semesterId, @PathVariable("degreeId") Long degreeId,  @PathVariable("subjectId") Long subjectId){
    	service.deleteById(semesterDegreeSubjectId, semesterId, degreeId, subjectId);
        return new BasicResponse("Successfully deleted", Boolean.FALSE);
    }
}
