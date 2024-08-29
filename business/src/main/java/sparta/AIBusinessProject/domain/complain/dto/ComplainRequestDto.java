package sparta.AIBusinessProject.domain.complain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainRequestDto {

    private UUID complain_id;
    private UUID user_id;
    private UUID review_id;
    private String ComplainContent;
    private String created_by;
    private String deleted_by;

}
