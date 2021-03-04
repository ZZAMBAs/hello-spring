package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 주의: 스프링 데이터 JPA는 JPA 편의도구이므로, JPA 지식이 반드시 필요.
// 여기선 강의 진도에 따라 어쩔 수 없이 진행.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{ // 스프링이 자동으로 구현체로 등록.
    @Override
    Optional<Member> findByName(String name);
}
