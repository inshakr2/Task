package chany.task.MedicalRecord3.domain;

import chany.task.MedicalRecord3.dto.PatientDto;
import chany.task.MedicalRecord3.utils.PatientKeyGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "PATIENT_ID")
    private long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

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

    public Patient(Hospital hospital, String patientName, String genderCode,
                   String birth, String phoneNumber, PatientCodeSeq seq) {
        this.hospital = hospital;
        this.patientName = patientName;
        this.genderCode = genderCode;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.patientCode = PatientKeyGenerator.getPatientKey(this, seq);
    }

    public void updatePatient(PatientDto patientDto, Hospital hospital) {
        this.setHospital(hospital);
        this.setPhoneNumber(patientDto.getPhoneNumber());
        this.setPatientName(patientDto.getName());
        this.setGenderCode(patientDto.getGenderCode());
        this.setBirth(patientDto.getBirth());
    }

    public void updatePatient(PatientDto patientDto) {
        this.setPhoneNumber(patientDto.getPhoneNumber());
        this.setPatientName(patientDto.getName());
        this.setGenderCode(patientDto.getGenderCode());
        this.setBirth(patientDto.getBirth());
    }
}
