package sparta.AIBusinessProject.domain.complain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainRequestDto {

    private UUID complainId;
    private UUID userId;
    private UUID reviewId;
    private String ComplainContent;
    private String created_by;
    private String deleted_by;

}
