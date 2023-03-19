package unq.edu.li.pdes.unqpremium.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SemesterVO {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
}
