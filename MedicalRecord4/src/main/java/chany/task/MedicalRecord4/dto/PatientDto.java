package chany.task.MedicalRecord4.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PatientDto {

    @NotNull
    private String name;
    @NotNull
    private String genderCode;
    private String birth;
    private String phoneNumber;
}
