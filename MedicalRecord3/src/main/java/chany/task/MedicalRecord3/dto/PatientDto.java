package chany.task.MedicalRecord3.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class PatientDto {

    @NotNull
    private String name;
    @NotNull
    private String genderCode;
    @NotNull
    private Long hospitalId;
    private String birth;
    private String phoneNumber;

}
