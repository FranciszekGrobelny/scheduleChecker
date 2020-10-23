package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.repository.DayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DayService  {

    private final DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public void save(Day day ){ dayRepository.save(day); }

    public List<Day> findAll(){
        return dayRepository.findAll();
    }

    public void update(Day day){
        dayRepository.save(day);
    }

    public void delete(Long id){
        dayRepository.deleteById(id);
    }

    public Day findByIdNoOptional(Long dayId) {
        return dayRepository.findByIdNoOptional(dayId);
    }
}
