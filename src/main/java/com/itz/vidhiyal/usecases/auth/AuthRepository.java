package com.itz.vidhiyal.usecases.auth;

import com.itz.vidhiyal.models.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserMaster, Long> {
    //    @Query(value = "select TOP 1 * " +
//            "from userInfo where userId = :username",nativeQuery = true)
    UserMaster findByUserId(String username);
}
