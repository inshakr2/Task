package chany.task.MedicalRecord2.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Visit extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "VISIT_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;
    private LocalDateTime dateTime;

}
