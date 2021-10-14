package chany.task.MedicalRecord5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VisitDto {

    @NotNull
    private Long hospitalId;
    @NotNull
    private String visitCode;
    private LocalDateTime visitDate = LocalDateTime.now();
}