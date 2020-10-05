package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.TopicDTO;
import project.inz.scheduleChecker.model.Topic;
import project.inz.scheduleChecker.repository.TopicRepository;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public void save(TopicDTO topicDTO){
        Topic topic = createClassFromClassDTO(topicDTO);
        topicRepository.save(topic);
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    public void update(TopicDTO topicDTO){
        Topic topic = createClassFromClassDTO(topicDTO);
        topicRepository.save(topic);
    }

    public void delete(Long id){
        topicRepository.deleteById(id);
    }

    private Topic createClassFromClassDTO(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());

        return topic;
    }
}
