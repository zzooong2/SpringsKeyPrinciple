package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    /* DIP 준수를 위해 MemberRepository 인터페이스에만 의존 */
    private final MemberRepository memberRepository;

    /* 생성자 주입 -> 일단 어떤 객체가 주입될 지 알 수 없음 */
    /* 다형성에 의해 외부에서 결정된 객체가 주입됨 */
    /* 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중한다 */

    @Autowired // 자동으로 의존관계 주입을 해줌 == ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
