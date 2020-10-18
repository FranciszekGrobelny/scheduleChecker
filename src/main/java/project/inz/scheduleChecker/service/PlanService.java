package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.model.Plan;
import project.inz.scheduleChecker.repository.PlanRepository;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public void save(Plan plan){
        planRepository.save(plan);
    }

    public List<Plan> findAll(){
        return planRepository.findAll();
    }

    public void update(Plan plan){
        planRepository.save(plan);
    }

    public void delete(Long id){
        planRepository.deleteById(id);
    }


}
