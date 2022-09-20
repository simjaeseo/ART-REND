package com.artrend.authservice.repository;

import com.artrend.authservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByProviderId(String uuid);

    void deleteByProviderId(String uuid);
}
