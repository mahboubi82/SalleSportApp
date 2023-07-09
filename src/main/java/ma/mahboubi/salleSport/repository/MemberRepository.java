package ma.mahboubi.salleSport.repository;

import ma.mahboubi.salleSport.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //Page<Member> findByLastNameContaining(String kw,Pageable pageable);
    Page<Member> findByLastNameContaining( String kw,Pageable pageable);

    @Query("select m FROM Member m where m.is_Active = ?1")
    Page<Member> findAllByIs_Active(Boolean is_Active, String kw, Pageable pageable);


    @Query("select m FROM Member m where m.payementDate >= : payementDate")
    Page<Member> findAllByPayementDateBefore(@Param("payementDate") Date payementDate, String kw, Pageable pageable);

    Page<Member> findAllByExpiryDateBetweenAndLastNameContaining(Date start, Date end,String kw, Pageable pageable);





}
