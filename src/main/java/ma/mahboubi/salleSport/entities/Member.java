package ma.mahboubi.salleSport.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private  String lastName;
    private String cine;
    private String email;
    private String BirthDate;
    private String phone;
    private boolean isActive;
    //private Base64 photo;
    private Sex sex;
    //private List<Subscription> subscriptions;



}
