package ma.mahboubi.salleSport.service;

import ma.mahboubi.salleSport.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    public Page<Member> findMemberWithSort(String field, String direction,int pageNumber);
}
