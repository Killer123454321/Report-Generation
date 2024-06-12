package in.praveenit.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.praveenit.entity.CitizenPlan;
import in.praveenit.repo.CitizenPlanRepository;
import in.praveenit.request.SearchRequest;
import in.praveenit.utils.EmailUtil;
import in.praveenit.utils.ExcelGenerator;
import in.praveenit.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	

	@Override
	public List<String> getPlanName() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		
		if(null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//Convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		
		if(null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String EndDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//Convert String to LocalDate
			LocalDate localDate = LocalDate.parse(EndDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
		
		File f = new File("PlanInfo.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generator(response, plans, f);
		
		String subject = "Test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "pr997386@gmail.com";
		
		emailUtil.sendEmail(subject, body, to, f);
		
		f.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception{
		
		File f = new File("PlanInfo.pdf");
		
		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generator(response, plans, f);
		
		String subject = "Test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "pr997386@gmail.com";
		
		emailUtil.sendEmail(subject, body, to, f);
		
		f.delete();
		
		return true;
	}

}
