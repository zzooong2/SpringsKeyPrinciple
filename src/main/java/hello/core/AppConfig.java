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

/* AppConfig 클래스는 어플리케이션의 실제 동작에 필요한 구현 객체를 생성한다. */
/* AppConfig 클래스는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.*/
/* command + e 단축키를 통해 히스토리를 확인할 수 있다.*/
public class AppConfig {
    
    /* 각각의 역할이 드러나게 코딩하는것이 중요 -> 리펙토링 command + option + m */
    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
    }

    private static MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }

    /* 고객요구사항에 맞게 할인정책을 반영할 수 있음 */
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }

}
