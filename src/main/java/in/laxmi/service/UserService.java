package in.laxmi.service;

import in.laxmi.binding.DashboardCards;
import in.laxmi.binding.LoginForm;
import in.laxmi.binding.UserAccountForm;

public interface UserService {
	public String loginAccount(LoginForm loginForm);
	public boolean recoverPwd(String email);
	public DashboardCards fetchDashBoardInfo();
		
	public UserAccountForm getUserByEmail(String email);
}
