package com.osipchik.service.repository;

import com.osipchik.service.domain.Inquiry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends CrudRepository<Inquiry, Long> {

    Iterable<Inquiry> findByCustomerName (String customerName);
    Inquiry findByIdAndCustomerName(Long id, String customerName);



}
