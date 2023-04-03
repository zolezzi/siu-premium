package unq.edu.li.pdes.unqpremium.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.DegreeDTO;
import unq.edu.li.pdes.unqpremium.service.impl.DegreeServiceImpl;

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

}
