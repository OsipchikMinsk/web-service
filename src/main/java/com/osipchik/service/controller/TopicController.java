package com.osipchik.service.controller;

import com.osipchik.service.domain.Topic;
import com.osipchik.service.repository.TopicRepository;
import com.osipchik.service.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/topics")
    public String getAllTopics (Model model){
        List<Topic> allTopics = topicService.findAll();
        model.addAttribute("topics", allTopics);
        return "topics";
    }

    @GetMapping("/main")
    public String main1 (){
        return"main";
    }

    @GetMapping
    public String main (){
        return "main";
    }

    @GetMapping("/addTopic")
    public String link(){
        return "addTopic";
    }

    @PostMapping("/addTopic")
    public String createTopic(@RequestParam String topic, Model model) {
        List<Topic> allTopics;
        topicService.saveTopic(topic);
        allTopics = topicService.findAll();
        model.addAttribute("topics", allTopics);
        return "topics";
    }

    @PostMapping ("/sendTopicToMainPage")
    public String send (@RequestParam String topic, Model model){
        model.addAttribute("name", topic);
        return "topicFromSeleted";
    }

}
