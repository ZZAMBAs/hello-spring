package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private  final EntityManager em;
    // JPA는 EntityManager로 모든 것이 동작한다. 따라서 반드시 생성해야 함.
    // JPA가 알아서 쿼리를 날려준다.

    public JpaMemberRepository(EntityManager em) { // 생성자 주입.
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // 객체 자체를 select
                .getResultList(); // 객체를 이용해 쿼리를 날리면 자동으로 테이블 쿼리로 변환함.
    }
}
