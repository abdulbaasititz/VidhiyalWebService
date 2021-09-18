package com.itz.vidhiyal.usecases.dashboard;

import com.itz.vidhiyal.usecases.dashboard.dao.DashboardDao;
import com.itz.vidhiyal.usecases.member_detail.MemberService;
import com.itz.vidhiyal.usecases.subscription_detail.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("${spring.base.path}" + "/v1")
public class DashboardController {
    @Autowired
    MemberService memberService;
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/get/dashboard")
    public ResponseEntity<?> getAllDashboard(HttpRequest request) {
        DashboardDao dashboardDao = new DashboardDao();
        dashboardDao.setTotalAmount(subscriptionService.getTotalAmount());
        dashboardDao.setNewlyJoined("10");
        dashboardDao.setSubscriptionAmount("12312");
        dashboardDao.setTotalMember(memberService.getMemberCount());
        return new ResponseEntity<>(dashboardDao, HttpStatus.OK);
    }

    @GetMapping("/get/dashboard/total-amount")
    public ResponseEntity<?> getTotalAmountByYear(@RequestParam String fromYear, @RequestParam String toYear) {
        String message = subscriptionService.totalAmountByYear(fromYear, toYear);
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/dashboard/total-member")
    public ResponseEntity<?> getTotalMemberByYear(@RequestParam Date fromDate, @RequestParam Date toDate) {
        String message = memberService.countMemberNumberByYear(fromDate, toDate);
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
