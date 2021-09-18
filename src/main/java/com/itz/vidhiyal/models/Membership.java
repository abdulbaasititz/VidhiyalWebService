package com.itz.vidhiyal.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "JoiningDate")
    private Date joiningDate;
    @Column(name = "MemberNumber")
    private String memberNumber;
    @Column(name = "Designation")
    private String designation;
    @Column(name = "MemberName")
    private String memberName;
    @Column(name = "FatherName")
    private String fatherName;
    @Column(name = "Address")
    private String address;
    @Column(name = "City")
    private String city;
    @Column(name = "MobileNumber")
    private String mobileNumber;
    @Column(name = "AadharNumber")
    private String aadharNumber;

    @Column(name = "SubscriberType")
    private String subscriberType;
    @Column(name = "Amount")
    private String amount;

    @Column(name = "IsActive")
    private int isActive;
    @Column(name = "upAt")
    private String upAt;
    @Column(name = "upBy")
    private String upBy;
    @Column(name = "crAt")
    private String crAt;
    @Column(name = "crBy")
    private String crBy;
}
