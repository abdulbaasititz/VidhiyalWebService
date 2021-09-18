package com.itz.vidhiyal.usecases.member_detail;

import com.itz.vidhiyal.helpers.common.calc.DateTimeCalc;
import com.itz.vidhiyal.models.Membership;
import com.itz.vidhiyal.usecases.member_detail.dao.MemberDao;
import com.itz.vidhiyal.usecases.member_detail.pojo.MembershipPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public MembershipPojo getMemberDetail(String memberNo) {
        return memberRepository.findByMemberNo(memberNo);
    }

    public List<Membership> getAllMember() {
        return memberRepository.findAll();
    }

    public Page<Membership> getMember(int pn, int ps) {
        Pageable pg = PageRequest.of(pn, ps);
        return memberRepository.findAll(pg);
    }

    public Boolean checkMemberNumber(String memberNumber) {
        Membership membership = memberRepository.findByMemberNumberAndIsActive(memberNumber, 1);
        return membership == null;
    }

    public Integer getMemberId(String memberNumber) {
        Membership membership = memberRepository.findByMemberNumberAndIsActive(memberNumber, 1);
        if (membership == null) {
            return 0;
        } else {
            return membership.getId();
        }
    }

    public String setMember(MemberDao memberDao, String user) {
        Boolean checkNumber = checkMemberNumber(memberDao.getMemberNumber());
        if (checkNumber) {
            String formattedDate = new DateTimeCalc().getTodayDate();
            Membership membership = new Membership();
            membership.setId(0);
            membership.setJoiningDate(memberDao.getJoiningDate());
            membership.setMemberNumber(memberDao.getMemberNumber());
            membership.setMemberName(memberDao.getMemberName());
            membership.setFatherName(memberDao.getFatherName());
            membership.setDesignation(memberDao.getDesignation());
            membership.setAddress(memberDao.getAddress());
            membership.setIsActive(1);
            membership.setCrBy(user);
            membership.setCrAt(formattedDate);
            memberRepository.save(membership);
            return "success";
        } else {
            return "data already present";
        }
    }

    public String editMember(MemberDao memberDao, String user) {
        Integer getNumber = getMemberId(memberDao.getMemberNumber());
        if (getNumber != 0) {
            String formattedDate = new DateTimeCalc().getTodayDate();
            Membership membership = new Membership();
            membership.setId(getNumber);
            membership.setJoiningDate(memberDao.getJoiningDate());
            membership.setMemberNumber(memberDao.getMemberNumber());
            membership.setMemberName(memberDao.getMemberName());
            membership.setFatherName(memberDao.getFatherName());
            membership.setDesignation(memberDao.getDesignation());
            membership.setAddress(memberDao.getAddress());
            membership.setIsActive(1);
            membership.setUpBy(user);
            membership.setUpAt(formattedDate);
            memberRepository.save(membership);
            return "success";
        } else {
            return "No Data Present";
        }

    }

    public List<MembershipPojo> getMemberByDate(Date fromDate, Date toDate) {
        return memberRepository.findByJoiningDateBetween(fromDate, toDate);
    }

    public String getMemberCount() {
        return memberRepository.countMemberNumber();
    }

    public String countMemberNumberByYear(Date fromDate, Date toDate) {
        return memberRepository.countMemberNumberByYear(fromDate, toDate);
    }

    public String removeMember(String memberNumber, String user) {
        Membership membership = memberRepository.findByMemberNumberAndIsActive(memberNumber, 1);
        if (membership != null) {
            String formattedDate = new DateTimeCalc().getTodayDate();
            membership.setIsActive(0);
            membership.setUpBy(user);
            membership.setUpAt(formattedDate);
            memberRepository.save(membership);
            return "Set as inactive";
        } else {
            return "No Data Present";
        }
    }
}
