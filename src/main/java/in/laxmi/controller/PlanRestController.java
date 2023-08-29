package in.laxmi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.laxmi.binding.PlanForm;
import in.laxmi.constants.AppConfigConstants;
import in.laxmi.entity.PlanEntity;
import in.laxmi.service.PlanService;

@RestController
public class PlanRestController {
	@Autowired
	private PlanService service;

	@PostMapping(value = "/createPlan", consumes = { "application/xml", "application/json" })
	public ResponseEntity<String> addAccount(@RequestBody PlanForm p) {
		boolean status = service.createPlan(p);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCCESS;
		} else {
			msg = AppConfigConstants.ERROR;
		}
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	
	/*@GetMapping(value = "/viewplans", produces= {"application/xml","application/json"})
	public List<PlanEntity> getPlans(PlanForm p) {
		return service.getPlan();
	}
	@PostMapping(value = "/editPlan", produces= {"application/xml","application/json"})
	public ResponseEntity<String> editplan(@RequestBody PlanForm p) {
		boolean status = service.editplans(p);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCC_MSG;
		} else {
			msg = AppConfigConstants.ERR_MSG;
		}
		return new ResponseEntity<>(msg, HttpStatus.CREATED);

	}
	@PostMapping(value = "/deletePlan", produces= {"application/xml","application/json"})
	public ResponseEntity<String> deletePlan(@RequestParam Integer planId) {
		boolean status = service.deletePlans(planId);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCC_MSG;
		} else {
			msg = AppConfigConstants.ERR_MSG;
		}
		return new ResponseEntity<>(msg, HttpStatus.CREATED);

	}*/
	
}
