package chany.task.MedicalRecord2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PatientDto {

    @NotEmpty
    private String name;
    private String code;
    private String gender;
    private String birth;
    private String phoneNumber;
}
