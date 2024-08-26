package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainListResponseDto {

    private List<ComplainResponseDto> complainList;

}
