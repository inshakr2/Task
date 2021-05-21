package chany.task.MedicalRecord2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateRegisterDto {

    @NotEmpty
    private String hospitalName;
    private String hospitalChiefName;

    @NotEmpty
    private String patientName;
    private String patientGender;
    private String patientPhoneNumber;
    private String patientBirth;
}
