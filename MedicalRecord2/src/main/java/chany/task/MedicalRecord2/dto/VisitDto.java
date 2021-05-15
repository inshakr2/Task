package chany.task.MedicalRecord2.dto;

import chany.task.MedicalRecord2.domain.Hospital;
import chany.task.MedicalRecord2.domain.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VisitDto {

    @NotNull
    private LocalDateTime dateTime;
    private Hospital hospital;
    private Patient patient;

}
