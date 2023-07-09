package ma.mahboubi.salleSport;

import ma.mahboubi.salleSport.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication


public class SalleSportApplication implements CommandLineRunner {
    @Autowired
    private MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(SalleSportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*memberRepository.save(Member.builder()
                .firstName("aaaaaaaa")
                .lastName("AAAAAAAAA")
                .payementDate(new Date())
                .SubscriptionDate(new Date())
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
                .payementDate(new Date())
                .SubscriptionDate(new Date())
                .phone("0600990082")
                .cine("BH000000")
                .email("SAMY@gmail.com")
                .isActive(false)
                .sex(Sex.M)
                .build()
        );*/
        //Member member = new Member(null, "ccccc", "CCCCC", "TT0099998", "TATA@gmail.com", "1200/03/01", "06889967276", false, Sex.F);

        //memberRepository.save(member);

        /*LocalDate aDate = LocalDate.of(2022, 6, 2);
        LocalDate last = LocalDate.of(2023, 6, 20);
        LocalDate sixtyDaysBehind = aDate.minusDays(5);

        Period period = Period.between(aDate, last);
        int diff = Math.abs(period.getMonths());

        System.out.println(diff);*/

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date past = format.parse("2023-05-29");
            Date now = new Date();
            System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");


        }
        catch (Exception j){
            j.printStackTrace();
        }

    }
}
