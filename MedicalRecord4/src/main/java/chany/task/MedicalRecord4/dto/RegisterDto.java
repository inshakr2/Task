package chany.task.MedicalRecord4.dto;

import chany.task.MedicalRecord4.dto.marker.AlreadyVisit;
import chany.task.MedicalRecord4.dto.marker.FirstVisit;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RegisterDto {

    // patient
    @NotNull(groups = AlreadyVisit.class)
    private Long patientId;

    @NotNull(groups = FirstVisit.class)
    private String name;
    @NotNull(groups = FirstVisit.class)
    private String genderCode;
    @NotNull(groups = FirstVisit.class)
    private String birth;
    @NotNull(groups = FirstVisit.class)
    private String phoneNumber;

    // visit
    @NotNull
    private Long hospitalId;
    @NotNull
    private String visitCode;
    private LocalDateTime visitDate = LocalDateTime.now();
}
