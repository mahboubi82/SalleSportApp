package ma.mahboubi.salleSport.repository;

import ma.mahboubi.salleSport.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //Page<Member> findByLastNameContaining(String kw,Pageable pageable);
    Page<Member> findByLastNameContaining( String kw,Pageable pageable);

}
