package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FIxDiscountPolicy();   //이 코드는 추상(DiscountPolicy)과 구현(FIxDiscountPolicy()) 모두 의존하고 있다. ->DIP위반.
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();    //이 코드는 추상(DiscountPolicy)과 구현(RateDiscountPolicy()) 모두 의존하고 있다 ->DIP위반.
    //FixDiscountPolicy를 RateDiscountPolicy로 바꾸려면 위와 같이 OrderServiceImpl의 코드 수정이 있어야함. -> OCP위반

    // private DiscountPolicy discountPolicy;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();에서 private DiscountPolicy discountPolicy;로 수정
    //이로써 인터페이스에만 의존하게 됨 , final은 무조건 값을 할당해줘야되기 때문에 지움

    private final MemberRepository memberRepository;  //final은 값이 할당되어야 쓸 수 있는데 생성자를 통해서 할당도 됨.
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
