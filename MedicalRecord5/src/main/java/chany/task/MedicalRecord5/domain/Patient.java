package chany.task.MedicalRecord5.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "PATIENT_ID")
    private Long id;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

    @Column(name = "PATIENT_NAME", length = 45, nullable = false)
    private String patientName;
    @Column(name = "PATIENT_CODE", length = 13, nullable = false)
    private String patientCode;
    @Column(name = "GENDER_CODE", length = 10, nullable = false)
    private String genderCode;
    @Column(name = "BIRTH", length = 10)
    private String birth;
    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    public void addVisit(Visit visit) {
        this.getVisits().add(visit);
        visit.setPatient(this);
    }
}
