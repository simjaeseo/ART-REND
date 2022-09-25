package com.artrend.authservice.service;

import com.artrend.authservice.domain.Member;
import com.artrend.authservice.dto.request.NicknameRequest;
import com.artrend.authservice.exception.MemberException;
import com.artrend.authservice.exception.MemberExceptionType;
import com.artrend.authservice.global.common.MessageResponse;
import com.artrend.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void insertNickname(Long memberId, NicknameRequest nicknameRequest) throws MemberException{
        Member findMember = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        findMember.updateNickname(nicknameRequest.getNickname());
    }

    public boolean checkNickname(Long memberId, NicknameRequest nicknameRequest){
        Optional<Member> findMember = memberRepository.findByNickname(nicknameRequest.getNickname());

        boolean isDuplicateNickname;

        if(findMember.isPresent()){
            isDuplicateNickname = true;
        } else{
            isDuplicateNickname = false;
        }

        return isDuplicateNickname;}

}
