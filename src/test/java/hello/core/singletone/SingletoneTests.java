package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletoneTests {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureConfig() {
        AppConfig appConfig = new AppConfig();
        // 1. 호출할 때 마다 객채를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 2. 참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService1 = " + memberService2);

        // memberServic1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        /* 웹 어플리케이션은 다량의 고객이 동시다발적으로 사용 -> 수많은 객체가 서버에 저장되면 효율성 떨어짐 */
        /* ⭐️이를 해결하기 위해 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다 -> 싱글톤 패턴 ⭐ */
        /* 객체 인스턴스를 2개 이상 생성하지 못하도록 설계
         * ->
         * private 생성자를 사용하여 외부에서 new 키워드를 사용하지 못하게 설계 */
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // same : ==, != (대상의 주소값을 비교)
        // equal : equal (대상의 내용을 비교)
        // a:"123";
        // b:"123";
        // a!=b, a is equal to b
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }


}
