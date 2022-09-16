package com.ssafy.artrend.businessservice.domain.member.repository;

import com.ssafy.artrend.businessservice.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
