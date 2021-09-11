package chany.task.MedicalRecord3.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class VisitDto {

    @NotNull
    private Long hospitalId;

    // Patient
    private Long patientId;
    @NotNull(groups = FirstVisit.class)
    private String patientName;
    @NotNull(groups = FirstVisit.class)
    private String genderCode;
    private String birth;
    private String phoneNumber;

    // Visit
    @NotNull
    private String visitCode;
    private LocalDateTime visitDate = LocalDateTime.now();

    public VisitDto(@NotNull Long hospitalId, @NotNull(groups = FirstVisit.class) String patientName,
                    @NotNull(groups = FirstVisit.class) String genderCode, String birth, String phoneNumber,
                    @NotNull String visitCode, LocalDateTime visitDate) {
        this.hospitalId = hospitalId;
        this.patientName = patientName;
        this.genderCode = genderCode;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.visitCode = visitCode;
        this.visitDate = visitDate;
    }
}
