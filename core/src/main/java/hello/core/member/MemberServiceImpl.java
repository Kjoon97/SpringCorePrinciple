package hello.core.member;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    //메모리 멤버 리포지토리로 구현. 메모리 멤버 리포지토리로 구현된 save()와 findById()를 호출할 수 있음

    public MemberServiceImpl(MemberRepository memberRepository) {   //MemberRepository의 구현체는 뭘로할지 생성자를 통해서 설정.
        this.memberRepository = memberRepository;                   //오로지 인터페이스만 의존하고 있음. MemoryMemberRepository관련된 코드 없음. -> DI 따름.
    }                                                               //구현체는 오직 AppConfig에서 MemberServiceImpl의 생성자를 통해서 넣어준다.

    @Override
    public void join(Member member) {  //회원 가입
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {    //회원 조회
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
