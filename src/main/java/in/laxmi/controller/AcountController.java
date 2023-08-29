package in.laxmi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.laxmi.binding.UnlockForm;
import in.laxmi.binding.UserAccountForm;
import in.laxmi.constants.AppConfigConstants;
import in.laxmi.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AcountController {
	private Logger logger = LoggerFactory.getLogger(AcountController.class);

	@Autowired
	private AccountService service;

	@PostMapping("/account")
	public ResponseEntity<String> createAccount(@RequestBody UserAccountForm accForm) {
		logger.debug("Account creation process started..");
		boolean status = service.createUserAccount(accForm);
		logger.debug("Account creation process completed");

		if (status) {
			logger.info("Account created successfully");
			return new ResponseEntity<>("Account created",HttpStatus.CREATED);
		} else {
			logger.info("Account creation failed");
			return new ResponseEntity<>("Account creation failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/accounts")
	public ResponseEntity<List<UserAccountForm>> getUsers(){
		logger.debug("Fetching user accounts process started..");
		List<UserAccountForm> userForms = service.fetchUserAccounts();
		logger.debug("Fetching user accounts process completed..");
		logger.info("user accounts fetched success..");
		return new ResponseEntity<>(userForms,HttpStatus.OK);
	}
	
	@GetMapping("/account/{id}")
	public ResponseEntity<UserAccountForm> getUser(@PathVariable("id") Integer userId){
	UserAccountForm userAccById = service.getUserAccById(userId);
		logger.info("user account fetched successfully..");
	return new ResponseEntity<>(userAccById,HttpStatus.OK);
	}
	@PutMapping("/account/{id}/{status}")
	public ResponseEntity<List<UserAccountForm>> updateStatus(@PathVariable("id") Integer userId,@PathVariable("status") String status){
		logger.debug("User account update process started..");
		service.changeAccStatus(userId, status);
		logger.debug("User account update process completed..");
		logger.info("User account status updated successfully..");
	List<UserAccountForm> userAccForms = service.fetchUserAccounts();
	return new ResponseEntity<>(userAccForms,HttpStatus.OK);
	
	}
	@PostMapping(value = "/unlockAccount")
	public ResponseEntity<String> unlockAccount(@RequestBody UnlockForm form) {
		String status = service.unlockUserAccount(form);
		if (status != null) {
			logger.info("successfully updated");
			return new ResponseEntity<>(AppConfigConstants.SUCCESS,HttpStatus.OK);
		} else {
			return new ResponseEntity<>("unlock failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*@PostMapping(value = "/forgotPwd/{email}", consumes = { "application/xml", "application/json" })
	public ResponseEntity<String> forgotPassword(@PathVariable("email") String email) {
		boolean status = service.forgotPwd(email);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCC_MSG;
		} else {
			msg = AppConfigConstants.ERR_MSG;
		}
		logger.info("sent password");
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}*/
	
	/*@PostMapping(value = "/editAccount", produces= {"application/xml","application/json"})
	public ResponseEntity<String> editAccount(@RequestBody CaseWorker cw) {
		boolean status = service.editAccount(cw);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCC_MSG;
		} else {
			msg = AppConfigConstants.ERR_MSG;
		}
		logger.info("successfully edited");
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	@PostMapping(value = "/deleteAccount", produces= {"application/xml","application/json"})
	public ResponseEntity<String> deleteAccount(@RequestParam Integer userid) {
		boolean status = service.deleteAccount(userid);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCC_MSG;
		} else {
			msg = AppConfigConstants.ERR_MSG;
		}
		logger.info("successfully deleted");
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}*/
	/*@PostMapping(value = "/login", produces= {"application/xml","application/json"})
	public ResponseEntity<String> userLogin(@RequestBody CaseWorker cw) {
		boolean status = service.loginAccount(cw);
		String msg = "";
		if (status) {
			msg = AppConfigConstants.SUCC_MSG;
		} else {
			msg = AppConfigConstants.ERR_MSG;
		}
		logger.info("successfully logged in");
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}*/
}
