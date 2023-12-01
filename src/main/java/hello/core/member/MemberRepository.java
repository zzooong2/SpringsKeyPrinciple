package hello.core.member;

public interface MemberRepository {

    /* 회원을 저장하는 메소드 */
    void save(Member member);

    /* 회원 ID로 회원을 찾는 메소드 */
    Member findById(Long memberId);

}
