package com.spring.order.service;

import com.spring.order.domain.Member;
import com.spring.order.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
}
