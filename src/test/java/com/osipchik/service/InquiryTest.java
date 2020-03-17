package com.osipchik.service;
import com.osipchik.service.domain.Inquiry;
import com.osipchik.service.domain.Topic;
import com.osipchik.service.repository.InquiryRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquiryTest {

    @Autowired
    private InquiryRepository inquiryRepository;


    public static List<Inquiry> createInquiries() throws ParseException {
        List<Inquiry> inquiriesTest = new ArrayList<>();
        Inquiry test1 = new Inquiry();
        test1.setId(2L);
        test1.setCustomerName("ArtemTest");
        test1.setDateOfInquiry(new Date(2020,03,18));
        test1.setDescription("Test2");
        test1.setTopic(new Topic(2L, "internet connection issue"));
        Inquiry test2 = new Inquiry();
        test2.setId(21L);
        test2.setCustomerName("ArtemTest");
        test2.setDateOfInquiry(new Date(2020,03,16));
        test2.setDescription("Test");
        test2.setTopic(new Topic(1L, "consultation"));
        inquiriesTest.add(test1);
        inquiriesTest.add(test2);
        return inquiriesTest;
    }

    @Test
    public void findInquiriesByCustomerName() throws ParseException {

        List<Inquiry> inquiryTest = InquiryTest.createInquiries();
        print(inquiryTest);
        List<Inquiry> inquiriesDb = (List<Inquiry>) inquiryRepository.findByCustomerName("ArtemTest");
        System.out.println("++++++++++++++");
        print(inquiriesDb);
        Assert.assertEquals(true, inquiryTest.equals(inquiriesDb));

    }

    public void print (List<Inquiry> inquiries){
        inquiries.forEach(inquiry ->
                System.out.println(inquiry));
    }
}
