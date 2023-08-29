package in.laxmi.service;

import java.util.List;

import in.laxmi.binding.PlanForm;
import in.laxmi.entity.PlanEntity;

public interface PlanService {
public boolean createPlan(PlanForm p);
public List<PlanEntity> fetchPlans();
public PlanForm getPlanById(Integer planId);
public String changePlanStatus(Integer planId,String status);
}
