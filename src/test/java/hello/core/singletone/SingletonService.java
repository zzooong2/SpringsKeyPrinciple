package hello.core.singletone;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 조회 방법 : 본인을 인스턴스 객체로 지정하여 정적으로 보관
    public static SingletonService getInstance(){
        return instance;
    }

    // private 선언으로 인해 다른 외부에서 SingletonService 매서드를 사용하지 못하도록 설계
    private SingletonService() {
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
