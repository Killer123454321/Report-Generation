package in.praveenit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.praveenit.entity.CitizenPlan;
import in.praveenit.request.SearchRequest;
import in.praveenit.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename = planInfo.pdf");
		reportService.exportPdf(response);
		
	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename = planInfo.xls");
		reportService.exportExcel(response);
		
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search")SearchRequest search, Model model) {
		
//		System.out.println(search);
		
		List<CitizenPlan> plans = reportService.search(search);
		model.addAttribute("planInfo", plans);
		
		init(model);
		
		return "index";
	}
	
	/**
	 * This method is used to load index page.
	 * @param model
	 * @return String
	 */
	@GetMapping("/")
	public String indexPage(Model model) {
		SearchRequest searchObj = new SearchRequest();
		model.addAttribute("search", searchObj);
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("names", reportService.getPlanName());
		model.addAttribute("status", reportService.getPlanStatus());
	}

}
