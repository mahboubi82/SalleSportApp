package ma.mahboubi.salleSport.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min =4,max =25 )
    private String firstName;
    private  String lastName;
    private String cine;
    @Email
    private String email;
    @Temporal(TemporalType.DATE)
    private Date payementDate;
    @Temporal(TemporalType.DATE)
    private Date SubscriptionDate;
    private String phone;
    private Boolean is_Active;
    //private Base64 photo;
    private Gender gender;
    private String photo;

    //private List<Subscription> subscriptions;

}
