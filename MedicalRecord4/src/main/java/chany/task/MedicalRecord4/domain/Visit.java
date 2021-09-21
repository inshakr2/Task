package chany.task.MedicalRecord4.domain;

import chany.task.MedicalRecord4.dto.VisitDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit extends BaseTimeEntity{

    @Id @GeneratedValue
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

    public Visit(Hospital hospital, VisitDto visitDto) {
        this.hospital = hospital;
        this.registerDate = visitDto.getVisitDate();
        this.visitCode = visitDto.getVisitCode();
    }

    public void updateVisit(VisitDto visitDto) {
        this.visitCode = visitDto.getVisitCode();
        this.registerDate = visitDto.getVisitDate();
    }

    public void updateVisit(Hospital hospital, VisitDto visitDto) {
        this.visitCode = visitDto.getVisitCode();
        this.registerDate = visitDto.getVisitDate();
        this.hospital = hospital;
    }

}
