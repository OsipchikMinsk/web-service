package com.osipchik.service;

import com.osipchik.service.controller.TopicController;
import com.osipchik.service.domain.Topic;
import com.osipchik.service.repository.TopicRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicController topicController;
    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void test() throws Exception{}

    @Test
    public void  contextLoads (){
        assertThat(topicController).isNotNull();
    }


    @Test
    public void saveTopic() throws Exception{
        Topic topic = new Topic();
        topic.setName("test topic");
        topicRepository.save(topic);
        Assert.assertNotNull(topicRepository.findTopicByName(topic.getName()));
    }

    @Test
    public void findTopicByname(){
         Topic topic = new Topic(1L,"consultation");
         Topic topicFromDb = topicRepository.findTopicByName(topic.getName());
         assertThat(topic.getName()).isEqualTo(topicFromDb.getName());

    }
    @Test
    public void  findAllTopics (){
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic(1L,"consultation"));
        topics.add(new Topic(2L,"internet connection issue"));
        topics.add(new Topic(4L,"forgot pincode"));
        topics.add(new Topic(5L,"mobile phone lost"));
        topics.add(new Topic(13L,"test topic"));
        topics.add(new Topic(14L,"mobile connection issue"));
        List<Topic> topicFromDb = topicRepository.findAll();
        Assert.assertEquals(true, topics.equals(topicFromDb));

    }



}
