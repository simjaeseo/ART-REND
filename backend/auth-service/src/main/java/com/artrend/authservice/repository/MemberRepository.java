package com.artrend.authservice.repository;

import com.artrend.authservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByKakaoProviderId(String providerId);
    Optional<Member> findByGoogleProviderId(String providerId);
    Optional<Member> findById(Long memberId);

    Optional<Member> findByDi(String di);

    Optional<Member> findByNickname(String nickname);

//    void deleteByProviderId(String uuid);
}
