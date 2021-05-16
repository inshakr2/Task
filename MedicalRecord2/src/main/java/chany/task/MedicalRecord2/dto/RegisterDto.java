package chany.task.MedicalRecord2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RegisterDto {

    @NotEmpty
    private String hospitalName;
    private String hospitalChiefName;

    @NotEmpty
    private String patientName;
    private String patientGender;
    private String patientPhoneNumber;
    private String patientBirth;

    @NotNull
    private LocalDateTime visitTime;
}
