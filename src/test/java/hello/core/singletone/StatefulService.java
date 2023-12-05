package hello.core.singletone;

public class StatefulService {

    /* private int price; // 상태를 유지하는 필드
    public void order(String name, int price){
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 문제가 발생하는 부분
    }

    public int getPrice() {
        return price;
    } */

    // 위 코드는 새로운 주문이 생성될 경우, 같은 인스턴스 객체를 이용하기 때문에 기존에 주문했던 고객의 주문금액이 변경됨.
    // 실무에서 한번씩 발생하는 어마무시한 사건사고로, 꼭 지역변수나 파라메타 등을 이용하여 설계해야한다.

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        return price;
    }
}
