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
import unq.edu.li.pdes.unqpremium.dto.CommitteeDTO;
import unq.edu.li.pdes.unqpremium.service.impl.CommitteeServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.CommitteeVO;

@RestController("committee")
@Api(value = "Committee Service")
@RequestMapping("/committee")
@RequiredArgsConstructor
public class CommitteeController {

	private final CommitteeServiceImpl service;
	
	@ApiOperation(
            value = "Service that return a Committee",
            notes = "This service return a Committee by the ID",
            nickname = "findById",
            response = CommitteeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = CommitteeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = CommitteeDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping(
            value = "/find-committee-by-id/{id}",
            produces = { "application/json" }
    )
	public CommitteeDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
    @ApiOperation(
            value = "This service save a Committee",
            notes = "Service that return CommitteeDTO with saved object Committee",
            nickname = "save",
            response = CommitteeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = CommitteeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = CommitteeDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/save",
            produces = { "application/json" }
    )
    public CommitteeDTO save(@RequestBody CommitteeVO committee){
        return service.save(committee);
    }
    
    @ApiOperation(
            value = "This service update a Committee",
            notes = "Update a Committee, if it doesn't find it throw an exception",
            nickname = "update",
            response = CommitteeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = CommitteeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = CommitteeDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PutMapping(
            value = "/update/{semesterDegreeSubjectId}/{committeeId}",
            produces = { "application/json" }
    )
    public CommitteeDTO update(@RequestBody CommitteeDTO committee, @PathVariable("semesterDegreeSubjectId") Long semesterDegreeSubjectId,  @PathVariable("committeeId") Long committeeId){
        return service.update(committee, semesterDegreeSubjectId, committeeId);
    }
    
    @ApiOperation(
            value = "This service delete a Committee",
            notes = "Delete a Committee, if it doesn't find it throw an exception",
            nickname = "deleteById",
            response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = BasicResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = BasicResponse.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping(
            value = "/delete/{semesterDegreeSubjectId}/{committeeId}",
            produces = { "application/json" }
    )
    public BasicResponse deleteById(@PathVariable("semesterDegreeSubjectId") Long semesterDegreeSubjectId,  @PathVariable("committeeId") Long committeeId){
    	service.deleteById(semesterDegreeSubjectId, committeeId);
        return new BasicResponse("Successfully deleted", Boolean.FALSE);
    }
}
