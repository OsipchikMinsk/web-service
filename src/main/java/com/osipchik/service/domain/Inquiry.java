package com.osipchik.service.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    private String description;
    private Date dateOfInquiry;
    private String customerName;

    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "inquiry_attribute", joinColumns = @JoinColumn(name = "inquiry_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> attributes = new HashMap<String, String>();

    public Inquiry(){
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfInquiry() {
        return dateOfInquiry;
    }

    public void setDateOfInquiry(Date dateOfInquiry) {
        this.dateOfInquiry = dateOfInquiry;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setAttributes (String name, String value){
        attributes.put(name,value);
    }
    public String getAttributeByKey(String key){
        return attributes.get(key);
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inquiry inquiry = (Inquiry) o;
        return Objects.equals(id, inquiry.id) &&
                Objects.equals(topic, inquiry.topic) &&
                Objects.equals(description, inquiry.description) &&
                Objects.equals(customerName, inquiry.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, description, customerName);
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "id=" + id +
                ", topic=" + topic +
                ", description='" + description + '\'' +
                ", dateOfInquiry=" + dateOfInquiry +
                ", customerName='" + customerName + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
