package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스는 외부 요청에 대한 일을 처리.
//@Service // 스프링 컨테이너에서 서비스로 관리함
@Transactional // JPA에서 사용을 위해 필요.
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository; // Dependency Injection.
    }

    // 회원 가입
    public Long join(Member member){

            //동명이인 금지
            validateDuplicateMember(member); // ctrl(cmd) + alt(opt) + m : 메소드 추출하기.

            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName()); // 반환 타입 자동 완성 : ctrl(cmd) + alt(opt) + v

        result.ifPresent(m -> { // ifPresent : 값이 있으면 아래를 수행.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
