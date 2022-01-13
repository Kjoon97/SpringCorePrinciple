package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();        //AppConfig를 통해서 객체 가져오기.(위 주석 코드와 달리 인터페이스만 의존함)
//        MemberService memberService =

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링은 뭐든게 ApplicationContext로 시작한다. 이것을 스프링 컨테이너라고 보면된다. @Bean으로 등록된 모든 객체들을 다 관리해줌.
        //스프링이 AppConfig에 있는 환경 설정 정보(@Bean으로 등록된 객체들)를 스프링컨테이너에 다 집어넣고 관리해줌.
        //컨테이너에 객체들이 @Bean으로 등록될 때 기본적으로 이름은 메소드이름으로 등록된다.

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //컨테이너를 통해서 객체 가져오기.
        //첫번째 파라미터는 @Bean으로 등록될 때 객체 이름(메소드 이름), 두번째는 타입 설정(반환타입을 알기위해).
        //의미: 이름이 "memberService"이고 타입이 MemberService인 컨테이너로부터 (@Bean으로 등록되어있는) 객체를 가져와라!!


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
