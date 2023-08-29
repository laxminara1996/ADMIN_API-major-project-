package in.laxmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import in.laxmi.binding.DashboardCards;
import in.laxmi.binding.LoginForm;
import in.laxmi.binding.UserAccountForm;
import in.laxmi.service.UserService;

public class UserRestController {
	@Autowired
	private UserService service;
    @PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		String status = service.loginAccount(loginForm);
		if (status.equals("success")) {
			return "redirect:/dashboard?email="+loginForm.getEmail();
		} else {
			return status;
		}
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCards> buildDashboard(@RequestParam("email") String email) {
		UserAccountForm user = service.getUserByEmail(email);
		DashboardCards dashBoard = service.fetchDashBoardInfo();
		dashBoard.setUser(user);
		return new ResponseEntity<>(dashBoard, HttpStatus.OK);
	}

	
}
