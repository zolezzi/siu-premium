package unq.edu.li.pdes.unqpremium.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import unq.edu.li.pdes.unqpremium.dto.SubjectDTO;
import unq.edu.li.pdes.unqpremium.service.impl.SubjectServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.SubjectVO;

@RestController("subject")
@Api(value = "Subject Service")
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

	private final SubjectServiceImpl service;

    @ApiOperation(
            value = "Service that return a Subject",
            notes = "This service return a Subject by the ID",
            nickname = "findById",
            response = SubjectDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = SubjectDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = SubjectDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping(
            value = "/find-subject-by-id/{id}",
            produces = { "application/json" }
    )
	public SubjectDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
    
    @ApiOperation(
            value = "This service save a Subject",
            notes = "Service that return SubjectDTO with saved object Subject",
            nickname = "save",
            response = SubjectDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = SubjectDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = SubjectDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/save",
            produces = { "application/json" }
    )
    public SubjectDTO save(@RequestBody SubjectVO subject){
        return service.save(subject);
    }
    
    @ApiOperation(
            value = "This service update a Subject",
            notes = "Update a Subject, if it doesn't find it throw an exception",
            nickname = "update",
            response = SubjectDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = SubjectDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = SubjectDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PutMapping(
            value = "/update/{degreeId}/{subjectId}",
            produces = { "application/json" }
    )
    public SubjectDTO update(@RequestBody SubjectDTO subject, @PathVariable("degreeId") Long degreeId,  @PathVariable("subjectId") Long subjectId){
        return service.update(subject, degreeId, subjectId);
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
            value = "/delete/{degreeId}/{subjectId}",
            produces = { "application/json" }
    )
    public BasicResponse deleteById(@PathVariable("degreeId") Long degreeId,  @PathVariable("subjectId") Long subjectId){
    	service.deleteById(degreeId, subjectId);
        return new BasicResponse("Successfully deleted", Boolean.FALSE);
    }
    
}
