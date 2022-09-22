package com.artrend.businessservice.domain.member.repository;

import com.artrend.businessservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberById(Long memberId);
}
