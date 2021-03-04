package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA 가 관리
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // id는 db가 알아서 생성해주며 그건 IDENTITY로 표기함.
    private Long id; // 시스템에서 저장하는 아이디

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
