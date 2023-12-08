package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTests {

    // 스프링 빈의 이벤트 라이프 사이클
    // 스프링 컨테이너 생성 -> 스프링 빈 생성(생성자 injection) -> 의존관계 주입(setter, field injection) ->
    // 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

    // 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
    // 소멸전 콜백 : 빈이 소멸되기 직전에 콜백

    @Test
    public void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        // ApplicationContext 로는 close 를 호출 할 수 없음.
        // AnnotationConfigApplicationContext 사용하거나
        // ConfigurableApplicationContext 사용.

    }

    /*
    * @Bean의 destroyMethod 메소드에는 기본값이 interred(추론)으로 등록되어 있음
    * interred(추론) 기능은 close, shutdown 이라는 이름의 메소드를 자동으로 서칭하여 호출해줌
    * 즉 이름 그대로 종료 메서드를 추론하여 호출해준다.
    * 코드 수정이 불가한 외부 라이브러리를 초기화, 종료해야할 때 사용.
    */

    /*
    * 최신 스프링에서는 @PostConstruct, @PredDestroy 사용을 권장함.
    * 어노테이션만 붙이면 끝.
    * 스프링에 종속적인 기술이 아니라 JSR-250이라는 자바의 표준이다. 즉 스프링 아닌 다른 컨테이너에서도 동작.
    * @ComponentScan과 잘 어울린다.
    * 단, 외부 라이브러리에 적용하지 못한다. (외부 라이브러리에 추가하려면 @Bean(initMethod="", destroyMethod="" 사용)
    * */

    @Configuration
    static class lifeCycleConfig{
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean // @PostConstruct, @PredDestroy 사용
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http:hello-spring.dev");
            return networkClient;
        }
    }
}
