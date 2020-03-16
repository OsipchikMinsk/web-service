package com.osipchik.service.service.impl;

import com.osipchik.service.domain.Inquiry;
import com.osipchik.service.domain.Topic;
import com.osipchik.service.exeption.ResourceNotFoundException;
import com.osipchik.service.repository.InquiryRepository;
import com.osipchik.service.repository.TopicRepository;
import com.osipchik.service.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public Inquiry saveInquiry(String nameOfTopic, String customerName, String description,
                                    String keyOfAttribute, String valueOfAttribute) {
        Inquiry inquiry = new Inquiry();
        Topic topic = topicRepository.findTopicByName(nameOfTopic);
        if (keyOfAttribute != null && !keyOfAttribute.isEmpty() &&
                valueOfAttribute != null && !valueOfAttribute.isEmpty()) {
            inquiry.setAttributes(keyOfAttribute, valueOfAttribute);
        }
        inquiry.setTopic(topic);
        inquiry.setDescription(description);
        inquiry.setCustomerName(customerName);
        return this.inquiryRepository.save(inquiry);
    }

    @Override
    public Iterable<Inquiry> findInquiriesByCustomerName(String customerName) {
        if (customerName != null && !customerName.isEmpty()) {
            return this.inquiryRepository.findByCustomerName(customerName);
        }
        return this.inquiryRepository.findAll();
    }

    @Override
    public Inquiry findInquiryByCustomerNameAndId(Long id, String customerName) {
        return this.inquiryRepository.findByIdAndCustomerName(id, customerName);
    }

    @Override
    public Inquiry update(Inquiry inquiry,
                          String key, String value) {
        if (inquiry.getId() != null) {
            return this.inquiryRepository.save(inquiry);
        }
        return  saveInquiry(inquiry.getTopic().getName(),
                inquiry.getCustomerName(),
                inquiry.getDescription(),
                key, value);

    }

    @Override
    public void delete(Long id, String customerName) {
        Inquiry inquiry = this.inquiryRepository.findByIdAndCustomerName(id, customerName);
        this.inquiryRepository.delete(inquiry);

    }

    @Override
    public Inquiry findById(Long id) throws ResourceNotFoundException {
        return inquiryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Inquiry " + id));
    }

    @Override
    public Iterable<Inquiry> findAllInquiries() {
        return this.inquiryRepository.findAll();
    }

    @Override
    public Inquiry save(Map<String, Object> inquiryMap) {
        Inquiry inquiry = new Inquiry();
        inquiry.setDateOfInquiry(new Date());
        inquiry.setTopic(topicRepository.getOne((Long.valueOf((String) inquiryMap.get("topic")))));
        inquiry.setDescription((String) inquiryMap.get("description"));
        inquiry.setCustomerName((String) inquiryMap.get("customerName"));

        List<String> attrNames = (List) inquiryMap.get("AttributeName");
        List<String> attrValues = (List) inquiryMap.get("AttributeValue");
        Map<String, String> attributes = new HashMap<>();

        for (int i = 0; i < attrNames.size(); i++) {
            attributes.put(attrNames.get(i), attrValues.get(i));
        }
        inquiry.setAttributes(attributes);
        return inquiryRepository.save(inquiry);
    }
}
