package in.praveenit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.praveenit.entity.CitizenPlan;
import in.praveenit.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository citizenPlanRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		citizenPlanRepo.deleteAll();
		
		//Cash Plan Data
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Jack");
		c1.setCitizenAge("27");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanBenefitAmount(75000.0);
		c1.setPlanStartDate(LocalDate.now().minusMonths(2));
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		
		
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("John");
		c2.setCitizenAge("35");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setPlanDenialReason("Document Invalid");
		
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Megg");
		c3.setCitizenAge("23");
		c3.setGender("Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanBenefitAmount(65000.0);
		c3.setPlanStartDate(LocalDate.now().minusMonths(3));
		c3.setPlanEndDate(LocalDate.now().plusMonths(7));
		c3.setPlanTerminationReason("Got Job");
		c3.setPlanTerminationDate(LocalDate.now());

		
		//Food Plan Data
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Jullie");
		c4.setCitizenAge("32");
		c4.setGender("Female");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanBenefitAmount(5000.0);
		c4.setPlanStartDate(LocalDate.now().minusMonths(4));
		c4.setPlanEndDate(LocalDate.now().plusMonths(8));
		
		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Maulta");
		c5.setCitizenAge("17");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setPlanDenialReason("Not Eligible");
		
		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Georgia");
		c6.setCitizenAge("40");
		c6.setGender("Female");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanBenefitAmount(55000.0);
		c6.setPlanStartDate(LocalDate.now().minusMonths(2));
		c6.setPlanEndDate(LocalDate.now().plusMonths(9));
		c6.setPlanTerminationReason("Location Change");
		c6.setPlanTerminationDate(LocalDate.now().minusDays(7));
		
		//Employment Plan Data
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Marie");
		c7.setCitizenAge("22");
		c7.setGender("Female");
		c7.setPlanName("Employment");
		c7.setPlanStatus("Approved");
		c7.setPlanBenefitAmount(3000.0);
		c7.setPlanStartDate(LocalDate.now().minusMonths(1));
		c7.setPlanEndDate(LocalDate.now().plusMonths(5));
		
		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Sam");
		c8.setCitizenAge("16");
		c8.setGender("Male");
		c8.setPlanName("Employment");
		c8.setPlanStatus("Denied");
		c8.setPlanDenialReason("Below 18");
		
		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Joy");
		c9.setCitizenAge("25");
		c9.setGender("Male");
		c9.setPlanName("Employment");
		c9.setPlanStatus("Terminated");
		c9.setPlanBenefitAmount(3000.0);
		c9.setPlanStartDate(LocalDate.now().minusMonths(5));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setPlanTerminationReason("Got Job");
		c9.setPlanTerminationDate(LocalDate.now().minusDays(15));
		
		//Medical Plan Data
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizenName("Jonny");
		c10.setCitizenAge("45");
		c10.setGender("Male");
		c10.setPlanName("Medical");
		c10.setPlanStatus("Approved");
		c10.setPlanBenefitAmount(7000.0);
		c10.setPlanStartDate(LocalDate.now().minusMonths(2));
		c10.setPlanEndDate(LocalDate.now().plusMonths(8));
		
		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizenName("Cathey");
		c11.setCitizenAge("35");
		c11.setGender("Female");
		c11.setPlanName("Medical");
		c11.setPlanStatus("Denied");
		c11.setPlanDenialReason("Not Eligible");
		
		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizenName("Orrey");
		c12.setCitizenAge("40");
		c12.setGender("Male");
		c12.setPlanName("Medical");
		c12.setPlanStatus("Terminated");
		c12.setPlanBenefitAmount(7000.0);
		c12.setPlanStartDate(LocalDate.now().minusMonths(5));
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		c12.setPlanTerminationReason("Recovered");
		c12.setPlanTerminationDate(LocalDate.now().minusDays(7));
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		
		citizenPlanRepo.saveAll(list);
	}

}
