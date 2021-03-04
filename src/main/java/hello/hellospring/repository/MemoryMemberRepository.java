package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 레포지토리는 마치 DB 처럼 저장소 역할을 함.
//@Repository // 스프링 컨테이너에서 Repository로 관리.
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // https://ppt21.com/qna/109783
    private static long sequence = 0L; // 키값을 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional로 null일때를 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // store 의 값을 전부 돌면서 member의 name과 파라미터 name이 같은 member 반환(없으면 null).
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // List로 반환.
    }

    public void clearStore() {
        store.clear(); // store를 비움.
    }
}
