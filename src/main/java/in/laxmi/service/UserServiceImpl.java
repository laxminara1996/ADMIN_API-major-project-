package in.laxmi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.laxmi.binding.DashboardCards;
import in.laxmi.binding.LoginForm;
import in.laxmi.binding.UserAccountForm;
import in.laxmi.constants.AppConfigConstants;
import in.laxmi.entity.EligEntity;
import in.laxmi.entity.PlanEntity;
import in.laxmi.entity.UserEntity;
import in.laxmi.repo.EligRepo;
import in.laxmi.repo.PlanEntityRepo;
import in.laxmi.repo.UserRepo;
import in.laxmi.util.EmailUtils;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
    private EmailUtils emailUtils;
	@Autowired
	private PlanEntityRepo planRepo;
	@Autowired
	private EligRepo eligRepo;
	
	@Override
	public String loginAccount(LoginForm loginForm) {
         UserEntity entity=  userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());
        if(entity == null) {
        	return "Invalid Credentials";
        }
        if(AppConfigConstants.Y_STR.equals(entity.getActiveSw()) && AppConfigConstants.UNLOCKED.equals(entity.getAccStatus())) {
        	return "Success";
        }else {
        	return "Account Locked";
        }
	}

	@Override
	public boolean recoverPwd(String email) {
		UserEntity entity = userRepo.findByEmail(email); 
		if (entity == null) { 
			return false; 
			
		}else {
		 String subject = AppConfigConstants.RECOVER_SUB; 
		 String body = AppConfigConstants.PWD_BODY_FILE; 
		 emailUtils.sendEmail(email, subject, body);
		  return true;
		}
	}

	@Override
	public DashboardCards fetchDashBoardInfo() {
        long plansCount =  userRepo.count(); 
        DashboardCards card = new DashboardCards();
        List<EligEntity> eligList = eligRepo.findAll();
        Long approvedCnt = eligList.stream().filter(ed->ed.getPlanStatus().equals(AppConfigConstants.AP)).count();
        Long deniedCnt = eligList.stream().filter(ed->ed.getPlanStatus().equals(AppConfigConstants.DN)).count();
        Double benfitAmt = eligList.stream().mapToDouble(ed->ed.getBenefitAmt()).sum();

        card.setPlansCnt(plansCount);
		card.setApprovedCnt(approvedCnt);
		card.setDeniedCnt(deniedCnt);
		card.setBeniftAmtGiven(benfitAmt);
        return card;
	}

	@Override
	public UserAccountForm getUserByEmail(String email) {
		UserEntity userentity = userRepo.findByEmail(email);
		
		UserAccountForm user = new UserAccountForm();
		BeanUtils.copyProperties(userentity, user);
		return user;
	}
}
