package chany.task.MedicalRecord3.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "VISIT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @Column(name = "REGISTER_DATE", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "VISIT_CODE", length = 10, nullable = false)
    private String visitCode;

    public Visit(Hospital hospital, Patient patient, LocalDateTime registerDate, String visitCode) {
        this.hospital = hospital;
        this.patient = patient;
        this.registerDate = registerDate;
        this.visitCode = visitCode;
    }
}
