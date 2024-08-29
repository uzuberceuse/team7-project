package sparta.AIBusinessProject.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {

    private UUID reviewId;
    private UUID userId;
    private String title;
    private String content;
    
}
