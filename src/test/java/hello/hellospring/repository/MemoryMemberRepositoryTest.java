package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트 하나 끝날 때마다 실행하는 메소드를 표시.
    public void afterEach() {
        repository.clearStore();
        // 테스트 각각을 독립적으로 실행하게 해줌. (repository를 테스트 하나 끝날 때마다 클리어)
    }


    @Test // 테스트 실행 가능.
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member)); // 실무에서는 log를 활용함.
        Assertions.assertEquals(member, result); // Ctrl + P 로 메소드의 파라미터 타입을 알 수 있음.
        // assertionEquals는 테스트 검증 메소드다.
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        // Assertion을 편하게 해주는 다른 패키지의 assertThat
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); // 코드 복사 후, rename 할 변수에 커서 두고 Shift + F6 으로 쉽게 rename 가능.

        Member result = repository.findByName("spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
// 코드를 만들고 테스트 코드로 확인하는 방법도 있지만, 테스트 코드를 먼저 짜고 이후 코딩하는 방식도 있는데 그것을
// TDD(테스트 주도 개발, Test-driven Development) 방식이라고 한다.
// https://gmlwjd9405.github.io/2018/06/03/agile-tdd.html