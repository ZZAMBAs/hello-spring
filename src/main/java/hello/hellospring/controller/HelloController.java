package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 컨트롤러는 외부 명령에 따라 어떤 것을 실행할지를 정함.
@Controller // 웹의 진입점인 컨트롤러 임을 표시. 스프링 컨테이너에서 객체로 생성 후 관리됨.(스프링 빈이 관리된다.)
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); // Model은 MVC의 M 이다.
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        // @RequestParam 을 붙여서 경로 상 파라미터를 넘겨줄 수 있다. ( ex: .../hello-mvc?name=example)
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody // http의 body 부분을 직접 넣어주겠다고 표시. 접속하고 페이지 소스를 한번 보자.
    public  String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    } // JSON 방식으로 출력.

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
