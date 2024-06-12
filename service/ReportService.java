package in.praveenit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.praveenit.entity.CitizenPlan;
import in.praveenit.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public interface ReportService {
	
	public List<String> getPlanName();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;

}
