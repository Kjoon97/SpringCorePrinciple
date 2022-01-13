package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationConfigApplicationContext {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);   //이름, 타입으로 조회

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
    }
    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);   //타입으로 조회
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);  //구체 클래스 타입으로 조회 -> 역할과 구현을 구분해야되기때문에 권장은 하지않음.

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("빈 없는 이름으로 조회할경우 - 실패 케이스")
    void findBeanByNameX(){
        //  MemberService xxxx = ac.getBean("xxxxx", MemberService.class);   //이름, 타입으로 조회
        //No bean named 'xxxxx' available 오류 출력됨
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
        //화살표에서 오른쪽 로직이 실행되었을 경우 왼쪽의 오류가 떠야 테스트 성공.
    }
}
