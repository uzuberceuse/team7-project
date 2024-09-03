package sparta.AIBusinessProject.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.complain.entity.Complain;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.user.dto.SignInRequestDto;
import sparta.AIBusinessProject.domain.user.dto.SignUpRequestDto;
import sparta.AIBusinessProject.domain.user.dto.UserRequestDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="p_user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id", updatable = false, nullable = false)
    private UUID user_id;

    @Column(unique=true, nullable = false)
    private String username;

    // unique 제약조건
    @Column(unique=true, nullable = false)
    private String email;

    @Column(unique=true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value=EnumType.STRING)
    private UserRoleEnum role;

    //@Builder.Default
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses=new ArrayList<>();

    //@Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complain> complains = new ArrayList<>();

    //@Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    //@Builder.Default
    private Boolean isPublic=true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;

    // user 객체 변환 메서드
    public static User create(String username,String email,String password,String phone,UserRoleEnum role){
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .phone(phone)
                .role(role)
                .build();
    }

    // 수정을 한 후 -> updated값을 수정하는 메서드
    public void changeUpdated(UserRequestDto request, String updatedBy){
        this.username=request.getUsername();
        this.email=request.getEmail();
        this.password=request.getPassword();
        this.phone=request.getPhone();
        //this.addresses=request.getAddress();

        this.updated_at=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.updated_by=updatedBy;
        this.isPublic=false;
    }

    // 삭제 한 후 -> deleted 값을 수정하는 메서드
    public void changeDeleted(String deletedBy){
        this.deleted_at=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.deleted_by=deletedBy;
        this.isPublic=false;
    }



}
