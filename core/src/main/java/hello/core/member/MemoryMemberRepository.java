package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();  //동시성 이슈가 발생할 수 있기 때문에 원래는 concurrent해시맵을 사용해야. 예제니깐 간단히 넘어감

    @Override
    public void save(Member member) {  //회원 저장
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {   //회원 조회
        return store.get(memberId);
    }
}
