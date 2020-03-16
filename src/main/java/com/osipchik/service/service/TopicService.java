package com.osipchik.service.service;

import com.osipchik.service.domain.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> findAll ();
    void  saveTopic (String name);
    Topic findByName (String name);
    Topic get(Long id);
}
