package com.osipchik.service.service.impl;

import com.osipchik.service.domain.Topic;
import com.osipchik.service.repository.TopicRepository;
import com.osipchik.service.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return (List<Topic>) topicRepository.findAll();
    }

    @Override
    public void saveTopic(String name) {
                 if(name!=null && !name.isEmpty()){
            Topic topic = new Topic(name);
            topicRepository.save(topic);
        }  //if input area is empty to do nothing
    }

    @Override
    public Topic findByName(String name) {
        return this.topicRepository.findTopicByName(name);
    }

    @Override
    public Topic get(Long id) {
        return topicRepository.getOne(id);
    }


}
