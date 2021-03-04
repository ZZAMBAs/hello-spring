package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 전반적인 스프링 도움말 사이트 : https://spring.io/
@SpringBootApplication // 해당 메인 패키지 하위 폴더들만 컴포넌트 스캔을 실행
public class HelloSpringApplication { // 인프런 강의 : https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}