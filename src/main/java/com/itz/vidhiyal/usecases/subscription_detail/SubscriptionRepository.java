package com.itz.vidhiyal.usecases.subscription_detail;

import com.itz.vidhiyal.models.Subscription;
import com.itz.vidhiyal.usecases.subscription_detail.pojo.SubscriptionPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query(value = "select mem.JoiningDate , mem.MemberNumber,mem.MemberName ,sub.Amount," +
            "sub.SubscriptionYear " +
            "from (Select MemberId as MemberId,SUM(Amount) as Amount," +
            "GROUP_CONCAT(SubscriptionYear) as SubscriptionYear From SubscriptionDummy GROUP By MemberId )" +
            "sub LEFT JOIN MembershipDummy mem on sub.MemberId = mem.Id ORDER BY mem.id ASC", nativeQuery = true)
    List<SubscriptionPojo> getAllSubscription();

    @Query(value = "select mem.JoiningDate , mem.MemberNumber,mem.MemberName ,sub.Amount, " +
            "sub.SubscriptionYear " +
            "from (Select MemberId as MemberId,SUM(Amount) as Amount, " +
            "GROUP_CONCAT(SubscriptionYear) as SubscriptionYear From SubscriptionDummy GROUP By MemberId ) " +
            "sub LEFT JOIN MembershipDummy mem on sub.MemberId = mem.Id ORDER BY mem.id ASC ",
            countQuery = "select count(sub.memUnique) from (SELECT count(*) as memUnique " +
                    "from SubscriptionDummy group by MemberId) as sub", nativeQuery = true)
    Page<SubscriptionPojo> getSubscription(Pageable pg);

    @Query(value = "SELECT sum(Amount) as totalAmount FROM SubscriptionDummy", nativeQuery = true)
    String totalAmount();

    @Query(value = "SELECT sum(Amount) as totalAmount FROM SubscriptionDummy Where SubscriptionYear " +
            "Between ?1 and ?2", nativeQuery = true)
    String totalAmountByYear(String fromYear, String toYear);

    Subscription findByMemberIdAndSubscriptionYear(Integer memberId, String subYear);

    Optional<Subscription> findById(Integer memberId);
}
