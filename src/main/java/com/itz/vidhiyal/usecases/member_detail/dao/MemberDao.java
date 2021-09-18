package com.itz.vidhiyal.usecases.member_detail.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberDao {
    private Date joiningDate;
    private String memberNumber;
    private String designation;
    private String memberName;
    private String fatherName;
    private String address;
    private String city;
    private String mobileNumber;
    private String aadharNumber;
    private String subscriberType;
    private String amount;
}
