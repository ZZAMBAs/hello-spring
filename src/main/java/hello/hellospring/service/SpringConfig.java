package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 스프링 빈을 직접 등록하는 방법. (@Service, @Repository를 사용하지 않고)
// 이 방법은 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 때 사용한다.

@Configuration
public class SpringConfig {
    /*
    private final DataSource dataSource; // jdbc 용
    
    private EntityManager em; // jpa 용
    
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
*/

    private final MemberRepository memberRepository;

    // 생성자가 한개일땐 @Autowired 생략 가능.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean // 스프링 빈에 등록한다.
    public MemberService memberService() {
        //return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

   // @Bean // 스프링 빈에 등록한다.
   // public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        // OCP (개방 폐쇄 원칙) : https://m.blog.naver.com/jwyoon25/221615569649 interface같은 추상의 사용 이유

        //new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    //}

    @Bean // 혹은 TimeTraceAop 클래스에 @Component 붙이기.
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
