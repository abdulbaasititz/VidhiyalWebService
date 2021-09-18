package com.itz.vidhiyal.usecases.subscription_detail.pojo;

import java.util.Date;

public interface SubscriptionPojo {
    Date getJoiningDate();

    String getMemberNumber();

    String getMemberName();

    String getSubscriptionYear();

    String getAmount();
}
