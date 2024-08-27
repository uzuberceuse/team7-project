package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private String category_id;
    private String StoreName;
    private String time;
    private String location;
    private String phone;
    private String details;
    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime updated_at;
    private String updated_by;
    private LocalDateTime deleted_at;
    private String deleted_by;
}
