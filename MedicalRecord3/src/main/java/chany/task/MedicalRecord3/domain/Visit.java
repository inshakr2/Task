package chany.task.MedicalRecord3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "VISIT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @Column(name = "REGISTER_DATE", nullable = false)
    private LocalDateTime registerDate = LocalDateTime.now();

    @Column(name = "VISIT_CODE", length = 10, nullable = false)
    private String visitCode;

    public static Visit registerVisit(Hospital hospital, Patient patient, LocalDateTime registerDate, String visitCode) {
        Visit visit = new Visit();
        visit.setHospital(hospital);
        visit.setPatient(patient);
        visit.setRegisterDate(registerDate);
        visit.setVisitCode(visitCode);
        patient.getVisits().add(visit);

        return visit;
    }

    public Visit(Hospital hospital, LocalDateTime registerDate, String visitCode) {
        this.hospital = hospital;
        this.registerDate = registerDate;
        this.visitCode = visitCode;
    }

    public void updateVisit(Hospital hospital, Patient patient, String visitCode, LocalDateTime visitDate) {
        this.setHospital(hospital);
        this.setPatient(patient);
        this.setVisitCode(visitCode);
        this.setRegisterDate(visitDate);
    }

    public void updateVisit(Hospital hospital, String visitCode, LocalDateTime visitDate) {
        this.setHospital(hospital);
        this.setVisitCode(visitCode);
        this.setRegisterDate(visitDate);
    }
}
