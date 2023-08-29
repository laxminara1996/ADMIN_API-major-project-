package in.laxmi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.laxmi.binding.PlanForm;
import in.laxmi.entity.PlanEntity;
import in.laxmi.repo.PlanEntityRepo;
@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
    private PlanEntityRepo planRepo;
	@Override
	public boolean createPlan(PlanForm p) {
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(p, entity);
		entity.setPlanName(p.getPlanName());
		planRepo.save(entity);
		return true;
	}
	@Override
	public List<PlanEntity> fetchPlans() {
		List<PlanEntity> planData = planRepo.findAll();
		if(!planData.isEmpty()) {
			return planData;
		}
		return Collections.emptyList();
	}
	
	@Override
	public PlanForm getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String changePlanStatus(Integer planId, String status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Override
	public boolean editplans(PlanForm p) {
		Optional<PlanEntity> planData = planRepo.findById(p.getPlanId());
		if(planData.isPresent()) {
			PlanEntity entity = planData.get();
			entity.setPlanName(p.getPlanName());
			entity.setPlanStatus(p.getPlanStatus());
			entity.setCategoryName(p.getCategoryName());
			planRepo.save(entity);
		}
		return true;
	}
	@Override
	public boolean deletePlans(Integer planId) {
		Optional<PlanEntity> planData = planRepo.findById(planId);
		if(planData.isPresent()) {
			PlanEntity entity = planData.get();
			planRepo.delete(entity);
		}
		return true;
	}*/
	
}
