package com.itz.vidhiyal.usecases.subscription_detail;

import com.itz.vidhiyal.helpers.common.token.ClaimsDao;
import com.itz.vidhiyal.helpers.common.token.ClaimsSet;
import com.itz.vidhiyal.usecases.subscription_detail.dao.SubscriptionDao;
import com.itz.vidhiyal.usecases.subscription_detail.pojo.SubscriptionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${spring.base.path}" + "/v1")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    ClaimsSet claimsSet;

    @GetMapping("/get/all-subscription")
    public ResponseEntity<?> getAllSubscription(HttpServletRequest request) {
        List<SubscriptionPojo> getAllSubscription = subscriptionService.getAllSubscription();
        return new ResponseEntity<>(getAllSubscription, HttpStatus.OK);
    }

    @PostMapping("/get/subscription")
    public ResponseEntity<?> getSubscription(HttpServletRequest request, @RequestParam int pageNumber
            , @RequestParam int pageSize) {
        Page<SubscriptionPojo> getSubscription = subscriptionService.getSubscription(pageNumber, pageSize);
        return new ResponseEntity<>(getSubscription, HttpStatus.OK);

    }

    @PostMapping("/set/subscription")
    public ResponseEntity<?> setSubscription(HttpServletRequest request, @RequestBody SubscriptionDao subscriptionDao) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        String message = subscriptionService.setSubscription(subscriptionDao, claimsDao.getEid());
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/del/subscription")
    public ResponseEntity<?> deleteSubscription(HttpServletRequest request, @RequestBody SubscriptionDao subscriptionDao) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        String message = subscriptionService.removeSubscription(subscriptionDao, claimsDao.getEid());
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
