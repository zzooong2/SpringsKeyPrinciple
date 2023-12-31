package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // 해당 어노테이션 붙어 있는 것을 찾음
// @Primary // @Primary 어노테이션이 붙어있는 클래스를 먼저 찾음
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; //10% 할인


    /* Test 패키지, 메서드 작성을 손쉽게 하는 커맨드 -> command + shift + t */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
