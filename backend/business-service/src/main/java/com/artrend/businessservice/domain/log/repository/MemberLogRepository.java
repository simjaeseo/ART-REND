package com.artrend.businessservice.domain.log.repository;

import com.artrend.businessservice.domain.log.entity.MemberLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLogRepository extends JpaRepository<MemberLog, Long> {
}
