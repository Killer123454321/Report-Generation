package in.praveenit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.praveenit.entity.CitizenPlan;
import in.praveenit.repo.CitizenPlanRepository;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	public void generator(HttpServletResponse response, List<CitizenPlan> planData, File file) throws Exception{
		
        Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));
		
		document.open();
		
		//Creating font.
		//Setting font style and size.
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setStyle(20);
		
		Paragraph paragraph = new Paragraph("Citizen Plans" , fontTitle);
		
		//Aligning Paragraph in document.
		paragraph.setAlignment(paragraph.ALIGN_CENTER);
		
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(8);
		table.setSpacingBefore(5);
		
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		
		
		for (CitizenPlan plan : planData) {
			
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			
			if(null != plan.getPlanStartDate()) {
				table.addCell(plan.getPlanStartDate() +"");
			}
			else {
				table.addCell("N/A");
			}
			
			if(null != plan.getPlanEndDate()) {
				table.addCell(plan.getPlanEndDate() +"");
			}
			else {
				table.addCell("N/A");
			}
			
			if(null != plan.getPlanBenefitAmount()) {
				table.addCell(String.valueOf(plan.getPlanBenefitAmount()));
			}
			else {
				table.addCell("N/A");
			}
		}
		document.add(table);
		document.close();
		
	}

}
