package sparta.AIBusinessProject.domain.category.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name="p_category")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true,nullable=false)
    private String categoryName;

    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;

    public static Category create(String name){
        return Category.builder()
                .categoryName(name)
                .build();
    }
}
