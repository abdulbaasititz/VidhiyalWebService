package com.itz.vidhiyal.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "MemberId")
    private Integer memberId;
    @Column(name = "SubscriptionYear")
    private String subscriptionYear;
    @Column(name = "Amount")
    private String amount;
    @Column(name = "upAt")
    private String upAt;
    @Column(name = "upBy")
    private String upBy;
    @Column(name = "crAt")
    private String crAt;
    @Column(name = "crBy")
    private String crBy;
}
