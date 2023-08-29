package in.laxmi.service;


import java.util.List;

import in.laxmi.binding.UnlockForm;
import in.laxmi.binding.UserAccountForm;

public interface AccountService {
public boolean createUserAccount(UserAccountForm accForm);
public List<UserAccountForm> fetchUserAccounts();
public UserAccountForm getUserAccById(Integer accId);
public String changeAccStatus(Integer userId,String status);
public String unlockUserAccount(UnlockForm unlockForm);


/*public boolean editAccount(CaseWorker cw);
public boolean deleteAccount(Integer userid);
public boolean forgotPwd(String email);*/




}
