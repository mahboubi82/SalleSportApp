package ma.mahboubi.salleSport.entities;



<<<<<<< HEAD
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
>>>>>>> origin/main
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.util.Date;
=======

>>>>>>> origin/main

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
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
=======
    private String firstName;
    private  String lastName;
    private String cine;
    private String email;
    private String BirthDate;
    private String phone;
    private boolean isActive;
    //private Base64 photo;
    private Sex sex;
>>>>>>> origin/main
    //private List<Subscription> subscriptions;



}
