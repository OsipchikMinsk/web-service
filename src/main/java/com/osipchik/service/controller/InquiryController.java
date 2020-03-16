package com.osipchik.service.controller;

import com.osipchik.service.domain.Inquiry;
import com.osipchik.service.domain.Topic;
import com.osipchik.service.exeption.ResourceNotFoundException;
import com.osipchik.service.repository.InquiryRepository;
import com.osipchik.service.service.InquiryService;
import com.osipchik.service.service.TopicService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private TopicService topicService;

    @GetMapping("/addInquiry")
     public String link(Model model){
        List<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);
        model.addAttribute("count", 1);
        return "addInquiry";
    }

    @PostMapping("/addInquiryDb")
    public String addInquiryToDb(@RequestParam MultiValueMap<String, String> params) {
        Map<String, Object> inquiry = new HashMap<>();
        params.forEach((k, v) -> {
              if (k.equals("topic") || k.equals("customerName") || k.equals("description")) {
                inquiry.put(k, v.get(0));
            } else {
                inquiry.put(k, v);
            }
        });
    inquiryService.save(inquiry);
        return "main";
    }

    @GetMapping ("/linkCustomerInquiries")
    public String linkCustomerInquiries (){
         return "customerInquiries";
    }

    @GetMapping("/linkDeleteInquiry")
    public String linkDeleteInquiry (Model model){
        Iterable<Inquiry> listOfInquaries = inquiryService.findAllInquiries();
        model.addAttribute("listOfInquaries", listOfInquaries);
        return "deleteInquiry";
    }
    @PostMapping ("/customerInquaries")
    public String findInquiriesByCustomerName (@RequestParam String customerName, Model model){
        Iterable<Inquiry> customerInquiries = inquiryService.findInquiriesByCustomerName(customerName);
        model.addAttribute("customerInquiries", customerInquiries);
        return "customerInquiries";
    }

    @GetMapping("/findInquiryByNameAndId")
    public String findInquiryByNameAndId (@RequestParam String inquiryId,
                                          @RequestParam String customerName, Model model){
        Long id = Long.valueOf(inquiryId);
        Inquiry inquiry = inquiryService.findInquiryByCustomerNameAndId(id, customerName);
        Iterable<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);
        model.addAttribute("id", inquiry.getId());
        model.addAttribute("customerName", inquiry.getCustomerName());
        model.addAttribute("description", inquiry.getDescription());
          return "updateInquiry";

    }
    @PostMapping ("/updateInquiry")
    public String updateInquiry (@RequestParam String id,
                                 @RequestParam String nameOfTopic,
                                 @RequestParam String customerName,
                                 @RequestParam String description,
                                 @RequestParam String keyOfAttribute,
                                 @RequestParam String valueOfAttribute,
                                 Model model) throws ResourceNotFoundException {

     Inquiry inquiry = inquiryService.findById(Long.valueOf(id));
     Topic topic = topicService.findByName(nameOfTopic);
     inquiry.setCustomerName(customerName);
     inquiry.setTopic(topic);
     inquiry.setDescription(description);
     inquiryService.update(inquiry, keyOfAttribute, valueOfAttribute);
     model.addAttribute("inquiry", inquiry);
     return "showInquiryUpdate";
    }
    @PostMapping ("/deleteInquiry")
    public String deleteInquiry (@RequestParam String inquiryId,
                                 @RequestParam String customerName){
        inquiryService.delete(Long.valueOf(inquiryId), customerName);
        return "main";
    }




}
