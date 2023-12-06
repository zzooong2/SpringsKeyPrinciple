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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* AppConfig 클래스는 어플리케이션의 실제 동작에 필요한 구현 객체를 생성한다. */
/* AppConfig 클래스는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.*/
/* command + e 단축키를 통해 히스토리를 확인할 수 있다.*/

@Configuration // 설정정보
public class AppConfig {

    // 메소드가 실행될 때 마다 객체가 생성되는데,
    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 서비스는 각각 실행되지만 MemoryMemberRepository() 메소드는 여러번 호출된다
    // 이는 싱글톤 패턴이 깨지는것이 아닌가? -> 테스트로 확인해보자

    // 아래와 같은 call 예상
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    // 실제로 테스트 결과 call 내용
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    // @Bean 만 사용해도 스프링 빈으로 등록되지만, 싱글톤 패턴이 깨진다.
    // 스프링 설정 정보는 항상 @Configuration 사용하자.

    /* 각각의 역할이 드러나게 코딩하는것이 중요 -> 리펙토링 command + option + m */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    /* 고객요구사항에 맞게 할인정책을 반영할 수 있음 */
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }
}
