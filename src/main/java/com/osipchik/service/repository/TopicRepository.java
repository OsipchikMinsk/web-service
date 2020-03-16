package com.osipchik.service.repository;

import com.osipchik.service.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    
   Topic findTopicByName (String name);
}
