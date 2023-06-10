package ma.mahboubi.salleSport;

import ma.mahboubi.salleSport.entities.Member;
import ma.mahboubi.salleSport.entities.Sex;
import ma.mahboubi.salleSport.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication


public class SalleSportApplication implements CommandLineRunner {
    @Autowired
    private MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(SalleSportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        memberRepository.save(Member.builder()
                .firstName("aaaaaaaa")
                .lastName("AAAAAAAAA")
                .BirthDate("1999/03/09")
                .phone("06600042800")
                .cine("BH435271")
                .email("NOUREDDINE@gmail.com")
                .isActive(true)
                .sex(Sex.M)
                .build()
        );
        memberRepository.save(Member.builder()
                .firstName("bbbbb")
                .lastName("BBBBBBBBB")
                .BirthDate("2012/11/26")
                .phone("0600990082")
                .cine("BH000000")
                .email("SAMY@gmail.com")
                .isActive(false)
                .sex(Sex.M)
                .build()
        );
        Member member = new Member(null, "ccccc", "CCCCC", "TT0099998", "TATA@gmail.com", "1200/03/01", "06889967276", false, Sex.F);

        memberRepository.save(member);

    }
}
