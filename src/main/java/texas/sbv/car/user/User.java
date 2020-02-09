package texas.sbv.car.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.social.Social;
import texas.sbv.car.social.Thumb;

@Component @Lazy @Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userSeq")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_seq") @NotNull private Long userSeq;
    @Column(name = "userid", unique = true, length = 25) @NotNull private String userid;
    @Column(name = "passwd", length=40) @NotNull private String passwd;
    @Column(name = "name", length = 30) @NotNull private String name;
    @Column(name = "email", length=100) @NotNull private String email;
    @Column(name = "auth") @NotNull private int auth;
    @Column(name = "gender", length=10)  private String gender;
    @Column(name = "birth_month", length=10)  private String birthMonth;
    @Column(name = "region", length=10)  private String region;


    @OneToMany(mappedBy = "userSeq", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Social> socials = new ArrayList<>();

    @OneToMany(mappedBy = "userSeq", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Thumb> thumbs = new ArrayList<>();


    @Builder
    private User(String userid, String passwd, String name, String email,
                 String gender, String birthMonth, String region) {
        Assert.hasText(userid, "userid must not be empty");
        Assert.hasText(passwd, "passwd must not be empty");
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(email, "email must not be empty");
        this.userid = userid;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthMonth = birthMonth;
        this.region = region;
    }

}
