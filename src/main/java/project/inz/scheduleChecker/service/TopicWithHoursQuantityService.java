package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;
import project.inz.scheduleChecker.repository.TopicWithHoursQuantityRepository;

import java.util.List;

@Service
public class TopicWithHoursQuantityService {

    private final TopicWithHoursQuantityRepository topicWithHoursQuantityRepository;

    public TopicWithHoursQuantityService(TopicWithHoursQuantityRepository topicWithHoursQuantityRepository) {
        this.topicWithHoursQuantityRepository = topicWithHoursQuantityRepository;
    }

    public void save(TopicWithHoursQuantity topicWithHoursQuantity){
        topicWithHoursQuantityRepository.save(topicWithHoursQuantity);
    }

    public List<TopicWithHoursQuantity> findAll(){
        return topicWithHoursQuantityRepository.findAll();
    }

    public void update(TopicWithHoursQuantity topicWithHoursQuantity){
        topicWithHoursQuantityRepository.save(topicWithHoursQuantity);
    }

    public void delete(Long id){
        topicWithHoursQuantityRepository.deleteById(id);
    }


}
