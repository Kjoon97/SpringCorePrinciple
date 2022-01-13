package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { // 필요한 구현 객체를 여기서 생성하고 주입한다.(각각의 생성자를 통해서 주입)
                         //역할(인터페이스)과 구현은 분리

    public MemberService memberService(){                   //역할
        return new MemberServiceImpl(memberRepository());   //구현
    }

    public OrderService orderService(){                                       //역할
        return new OrderServiceImpl(memberRepository(), discountPolicy());    //구현
    }

    public MemberRepository memberRepository() {    //역할
        return new MemoryMemberRepository();        //구현 나중에 db바꾸고 싶으면 이 줄만 수정하면됨.
    }

    public DiscountPolicy discountPolicy(){            //역할
        return new RateDiscountPolicy();                //구현  나중에 할인 정책을 변경하고 싶으면 이 줄만 수정하면됨
    }
}
