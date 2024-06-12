package in.praveenit.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchRequest {
	
	public String planName;
	public String planStatus;
	public String gender;
	public String startDate;
	public String endDate;

}
