package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
   // MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach                // 테스트 이전에 항상 실행해야하는 것은  @BeforeEach의 매소드안에 코드 작성한다. 테스트가 여러개 있으면 여러번 작동한다.
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
