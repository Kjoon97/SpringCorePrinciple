package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)   //false로 해주면 이 매소드 자체가 호출이 안된다.
        public void setNoBean1(Member noBean1){   //Member는 스프링 빈으로 등록되지 않았음.
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){    //호출은 되지만 빈으로 등록되지않았으므로 null로 호출됨.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){   //호출은 되지만 빈으로 등록되지않았으므로 Optional.empty로 호출됨.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
