package ma.mahboubi.salleSport.service;

import ma.mahboubi.salleSport.entities.Gender;
import ma.mahboubi.salleSport.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MemberService {
    public Page<Member> findMemberWithSort(String field, String direction,int pageNumber);
    public Page<Member> findMemberShouldPay(Date payementDate);

   // Member updateMember (String firstName, String lastName, String cine, String email, Date payementDate, Date subscriptionDate, String phone, Boolean is_Active, Gender gender, String photo);
}
