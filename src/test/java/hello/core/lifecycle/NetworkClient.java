package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url =" + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스를 시작할 때 호출
    public void connect(){
        System.out.println("connect =" + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message =" + message);
    }

    //서비스를 종료할 때 호출
    public void disconnect(){
        System.out.println("close " + url);
    }


    /*
    * 설정 정보 사용 방법 (init, close 메소드를 작성 -> Bean 생성시 정보를 설정)
    * @Bean(initMethod = "init", destroyMethod = "close")
    * 메소드 이름을 자유롭게 작성할 수 있음
    * 스프링 빈이 스프링 코드에 의존하지 않음
    * 코드를 수정할 수 없는 외부 라이브러리에도 초기화, 종료 메소드를 적용할 수 있음
    */

    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }


    /*
    implements InitializingBean, DisposableBean (기존 코드 구현)
    * 초기화, 소멸 인터페이스의 단점 => 스프링 초창기에 사용했음. 현재는 거의 사용 안함
    * 스프링 전용 인터페이스 (코드가 스프링 전용 인터페이스에 의존)
    * 초기화, 소멸 메서드의 이름 변경 불가
    * 코드를 직접 수정할 수 없는 외부 라이브러리


    // InitializingBean : 초기화 빈
    // afterPropertiesSet : 의존관계주입 끝난 뒤 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // DisposableBean : 파괴 = 연결해제
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
     */
}
