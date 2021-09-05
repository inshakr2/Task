package chany.task.MedicalRecord3.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "PATIENT_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

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

    public Patient(Hospital hospital, String patientName, String patientCode, String genderCode, String birth, String phoneNumber) {
        this.hospital = hospital;
        this.patientName = patientName;
        this.patientCode = patientCode;
        this.genderCode = genderCode;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }
}
