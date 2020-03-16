package com.osipchik.service.service;

import com.osipchik.service.domain.Inquiry;
import com.osipchik.service.exeption.ResourceNotFoundException;

import java.util.Map;
import java.util.Optional;

public interface InquiryService {

    Inquiry saveInquiry (String nameOfTopic,String customerName,
                      String description, String keyOfAttribute,
                      String valueOfAttribute);

    Iterable<Inquiry> findInquiriesByCustomerName (String customerName);

    Inquiry findInquiryByCustomerNameAndId(Long id, String customerName);

    Inquiry update (Inquiry inquiry,
                    String key, String value);
    void delete (Long id, String customerName);
    Inquiry findById (Long id) throws ResourceNotFoundException;
    Iterable<Inquiry> findAllInquiries ();
    Inquiry save(Map<String, Object> inquiry);
}
