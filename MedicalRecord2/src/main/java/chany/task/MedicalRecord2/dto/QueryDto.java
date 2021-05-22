package chany.task.MedicalRecord2.dto;

import chany.task.MedicalRecord2.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class QueryDto {

    private Long id;
    @NotEmpty
    private String name;
    private String code;
    private String gender;
    private String birth;
    private String phoneNumber;
    private List<Visit> visits;
}
