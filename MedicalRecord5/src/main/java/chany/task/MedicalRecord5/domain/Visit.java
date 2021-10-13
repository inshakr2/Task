package chany.task.MedicalRecord5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "VISIT_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

    @Column(name = "REGISTER_DATE", nullable = false)
    private LocalDateTime registerDate = LocalDateTime.now();

    @Column(name = "VISIT_CODE", length = 10, nullable = false)
    private String visitCode;
}
