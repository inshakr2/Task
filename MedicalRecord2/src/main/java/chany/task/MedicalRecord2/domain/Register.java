package chany.task.MedicalRecord2.domain;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Register {

    private Hospital hospital;
    private Visit visit;
    private Patient patient;
}
