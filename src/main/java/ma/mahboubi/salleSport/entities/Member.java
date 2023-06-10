package ma.mahboubi.salleSport.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    @Temporal(TemporalType.DATE)
    private Date payementDate;
    @Temporal(TemporalType.DATE)
    private Date SubscriptionDate;
    private String phone;
    private boolean isActive;
    //private Base64 photo;
    private Sex sex;
    //private List<Subscription> subscriptions;



}
