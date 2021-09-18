package com.itz.vidhiyal.usecases.subscription_detail;

import com.itz.vidhiyal.helpers.common.calc.DateTimeCalc;
import com.itz.vidhiyal.models.Subscription;
import com.itz.vidhiyal.usecases.member_detail.MemberService;
import com.itz.vidhiyal.usecases.subscription_detail.dao.SubscriptionDao;
import com.itz.vidhiyal.usecases.subscription_detail.pojo.SubscriptionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    MemberService memberService;

    public List<SubscriptionPojo> getAllSubscription() {
        return subscriptionRepository.getAllSubscription();
    }

    public Page<SubscriptionPojo> getSubscription(int pn, int ps) {
        Pageable pg = PageRequest.of(pn, ps);
        return subscriptionRepository.getSubscription(pg);
    }

    public String getTotalAmount() {
        return subscriptionRepository.totalAmount();
    }

    public String totalAmountByYear(String fromYear, String toYear) {
        return subscriptionRepository.totalAmountByYear(fromYear, toYear);
    }

    public Boolean checkSubscriptionByYear(Integer memberId, String subYear) {
        Subscription subscription = subscriptionRepository.findByMemberIdAndSubscriptionYear(memberId, subYear);
        return subscription == null;
    }

    public String setSubscription(SubscriptionDao subscriptionDao, String user) {
        Integer memberId = memberService.getMemberId(subscriptionDao.getMemberNumber());
        if (memberId != 0) {
            if (checkSubscriptionByYear(memberId, subscriptionDao.getSubscriptionYear())) {
                String formattedDate = new DateTimeCalc().getTodayDate();
                Subscription subscription = new Subscription();
                subscription.setId(0);
                subscription.setAmount(subscriptionDao.getAmount());
                subscription.setSubscriptionYear(subscriptionDao.getSubscriptionYear());
                subscription.setMemberId(memberId);
                subscription.setCrBy(user);
                subscription.setCrAt(formattedDate);
                subscriptionRepository.save(subscription);
                return "success";
            }
            return "Member Subscribed already in current year";
        } else {
            return "Set Member Details in Member page";
        }
    }

    public String removeSubscription(SubscriptionDao subscriptionDao, String eid) {
        Integer memberId = memberService.getMemberId(subscriptionDao.getMemberNumber());
        if (memberId != 0) {
            if (checkSubscriptionByYear(memberId, subscriptionDao.getSubscriptionYear())) {
                return "Member Not Subscribed in given year";
            }
            Subscription subscription = subscriptionRepository.findByMemberIdAndSubscriptionYear(memberId, subscriptionDao.getSubscriptionYear());
            subscriptionRepository.delete(subscription);
            return "Removed the subscription in given year";
        } else {
            return "Set Member Details in Member page";
        }
    }
}
