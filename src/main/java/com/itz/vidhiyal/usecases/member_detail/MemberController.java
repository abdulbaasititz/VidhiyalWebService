package com.itz.vidhiyal.usecases.member_detail;

import com.itz.vidhiyal.helpers.common.token.ClaimsDao;
import com.itz.vidhiyal.helpers.common.token.ClaimsSet;
import com.itz.vidhiyal.models.Membership;
import com.itz.vidhiyal.usecases.member_detail.dao.MemberDao;
import com.itz.vidhiyal.usecases.member_detail.pojo.MembershipPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${spring.base.path}" + "/v1")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    ClaimsSet claimsSet;

    @GetMapping("/get/member-detail")
    public ResponseEntity<?> getMemberDetails(HttpServletRequest request, @RequestParam String memberNumber) {
        String token = request.getHeader("Authorization");
        MembershipPojo getMember = memberService.getMemberDetail(memberNumber);
        return new ResponseEntity<>(getMember, HttpStatus.OK);
    }

    @GetMapping("/get/all-member")
    public ResponseEntity<?> getAllMember(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        List<Membership> getAllMember = memberService.getAllMember();
        return new ResponseEntity<>(getAllMember, HttpStatus.OK);
    }

    @GetMapping("/get/member")
    public ResponseEntity<?> getMember(HttpServletRequest request, @RequestParam int pageNumber
            , @RequestParam int pageSize) {
        Page<Membership> getMember = memberService.getMember(pageNumber, pageSize);
        return new ResponseEntity<>(getMember, HttpStatus.OK);
    }

    @PostMapping("/set/member")
    public ResponseEntity<?> setMember(HttpServletRequest request, @RequestBody MemberDao memberDao) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        String message = memberService.setMember(memberDao, claimsDao.getEid());
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/edit/member")
    public ResponseEntity<?> editMember(HttpServletRequest request, @RequestBody MemberDao memberDao) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        String message = memberService.editMember(memberDao, claimsDao.getEid());
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/range-member")
    public ResponseEntity<?> getMemberByDate(HttpServletRequest request, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate
            , @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        List<MembershipPojo> getMember = memberService.getMemberByDate(fromDate, toDate);
        return new ResponseEntity<>(getMember, HttpStatus.OK);
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> removeMember(HttpServletRequest request, @RequestParam String memberNumber) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        String message = memberService.removeMember(memberNumber, claimsDao.getEid());
        Map<String, String> response = new HashMap<>();
        response.put("status", "1");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
