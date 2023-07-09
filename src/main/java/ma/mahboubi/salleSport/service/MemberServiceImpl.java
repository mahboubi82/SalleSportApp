package ma.mahboubi.salleSport.service;

import lombok.AllArgsConstructor;
import ma.mahboubi.salleSport.entities.Gender;
import ma.mahboubi.salleSport.entities.Member;
import ma.mahboubi.salleSport.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    public MemberRepository memberRepository;
    @Override
    public Page<Member> findMemberWithSort(String field, String direction,int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber,5, sort);
        return memberRepository.findAll(pageable);
    }

    @Override
    public Page<Member> findMemberShouldPay(Date payementDate) {


        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date past = format.parse("2023-05-29");
            Date now = new Date();
            System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");
        }
        catch (Exception j){
            j.printStackTrace();
        }

        return null;
    }

}
