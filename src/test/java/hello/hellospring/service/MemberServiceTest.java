package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
// 단위 테스트
class MemberServiceTest { // 테스트 하고 싶은 클래스의 이름에 커서를 대고 ctrl + shift + t

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 테스트 실행 전 실행되는 메소드.
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 테스트 하나 끝날 때마다 실행하는 메소드를 표시.
    public void afterEach() {
        memberRepository.clearStore();
        // 테스트 각각을 독립적으로 실행하게 해줌. (repository를 테스트 하나 끝날 때마다 클리어)
    }

    @Test
    void 회원가입() {
        // given : 이용 요소
        Member member = new Member();
        member.setName("spring");

        // when : 테스트 코드의 중심부
        Long saveId = memberService.join(member);

        // then : 검증부
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try-catch 방법
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}