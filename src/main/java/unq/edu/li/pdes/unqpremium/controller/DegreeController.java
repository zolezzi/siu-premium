package unq.edu.li.pdes.unqpremium.controller;

import java.util.List;

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
import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.service.impl.DegreeServiceImpl;
import unq.edu.li.pdes.unqpremium.vo.DegreeVO;

@RestController("degree")
@Api(value = "Degree Service")
@RequestMapping("/degree")
@RequiredArgsConstructor
public class DegreeController {
	
	private final DegreeServiceImpl service;

    @ApiOperation(
            value = "Service that returns all degree",
            notes = "This service returns all degree load",
            nickname = "findAll",
            response = DegreeDTO.class, 
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = DegreeDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = DegreeDTO.class, responseContainer = "List") })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping(
            value = "/find-all",
            produces = { "application/json" }
    )
    public List<DegreeDTO> findAll(){
        return service.findAll();
    }
    
    @ApiOperation(
            value = "Service that returns all degree with subjects by degree id",
            notes = "This service returns all degree with subjects load by degree id",
            nickname = "findAllDegreeByFilter",
            response = DegreeDTO.class, 
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = DegreeDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = DegreeDTO.class, responseContainer = "List") })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/find-all-degree-by-filter",
            produces = { "application/json" }
    )
    public List<DegreeDTO> findAllDegreeByFilter(@RequestBody DegreeFilterDTO filter){
        return service.findAllDegreeByFilter(filter);
    }
    
    @ApiOperation(
            value = "Service that returns search by filter degree with subjects by filter",
            notes = "This service returns search by filter  degree with subjects load by filter",
            nickname = "searchByFilter",
            response = DegreeDTO.class, 
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = DegreeDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = DegreeDTO.class, responseContainer = "List") })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/search-by-filter",
            produces = { "application/json" }
    )
    public List<DegreeDTO> searchByFilter(@RequestBody DegreeFilterDTO filter){
        return service.searchByFilter(filter);
    }

    @ApiOperation(
            value = "Service that return a Degree",
            notes = "This service return a Degree by the ID",
            nickname = "findById",
            response = DegreeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = DegreeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = DegreeDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping(
            value = "/find-degree-by-id/{id}",
            produces = { "application/json" }
    )
	public DegreeDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
    
    @ApiOperation(
            value = "This service save a Degree",
            notes = "Service that return DegreeDTO with saved object Degree",
            nickname = "save",
            response = DegreeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = DegreeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = DegreeDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/save",
            produces = { "application/json" }
    )
    public DegreeDTO save(@RequestBody DegreeVO degree){
        return service.save(degree);
    }
    
    @ApiOperation(
            value = "This service update a Degree",
            notes = "Update a Degree, if it doesn't find it throw an exception",
            nickname = "update",
            response = DegreeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = DegreeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = DegreeDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PutMapping(
            value = "/update/{degreeId}",
            produces = { "application/json" }
    )
    public DegreeDTO update(@RequestBody DegreeDTO degree, @PathVariable("degreeId") Long degreeId){
        return service.update(degree, degreeId);
    }
    
    @ApiOperation(
            value = "This service delete a Degree",
            notes = "Delete a Degree, if it doesn't find it throw an exception",
            nickname = "deleteById",
            response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = BasicResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = BasicResponse.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping(
            value = "/delete/{degreeId}",
            produces = { "application/json" }
    )
    public BasicResponse deleteById(@PathVariable("degreeId") Long degreeId) throws Exception{
    	service.deleteById(degreeId);
        return new BasicResponse("Successfully deleted", Boolean.FALSE);
    }
}
