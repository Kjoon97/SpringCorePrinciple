package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){ // 상황: 2명의 고객의 요청이 옴. A사용자가 10000원을 주문하고 주문 금액을 조회하려는데 그사이에 B사용자가 20000원을 주문.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원을 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자가 20000원을 주문문
       statefulService2.order("userB", 20000);

        //ThreadA: A사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);   //-> 기대한건 만원이지만 2만원이 출력됨. statefulService1이든 2이든 같은 인스턴스를 공유하기 때문에 바뀜.

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}