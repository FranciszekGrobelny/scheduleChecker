package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.PlanDTO;
import project.inz.scheduleChecker.model.Plan;
import project.inz.scheduleChecker.repository.PlanRepository;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public void save(PlanDTO planDTO){
        Plan plan = createClassFromClassDTO(planDTO);
        planRepository.save(plan);
    }

    public List<Plan> findAll(){
        return planRepository.findAll();
    }

    public void update(PlanDTO planDTO){
        Plan plan = createClassFromClassDTO(planDTO);
        planRepository.save(plan);
    }

    public void delete(Long id){
        planRepository.deleteById(id);
    }

    private Plan createClassFromClassDTO(PlanDTO planDTO) {
        Plan plan = new Plan();
        plan.setClas(planDTO.getClas());
        plan.setDay(planDTO.getDay());
        plan.setLesson(planDTO.getLesson());
        plan.setLessonNumber(planDTO.getLessonNumber());

        return plan;
    }
}
