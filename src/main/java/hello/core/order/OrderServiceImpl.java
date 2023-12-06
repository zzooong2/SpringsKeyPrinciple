package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /* DIP 준수를 위해 MemberRepository, DiscountPolicy 인터페이스에만 의존 */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 생성자 주입 -> 일단 어떤 객체가 주입될 지 알 수 없음 */
    // 생성자 사용시 불변, 누락, final 키워드 사용가능 이라는 장점이 있음

    /* 다형성에 의해 외부에서 결정된 객체가 주입됨 */
    /* 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중한다 */


    @Autowired // 자동으로 의존관계 주입을 해줌 == ac.getBean(MemberRepository.class)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
