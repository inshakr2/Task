package chany.task.MedicalRecord3.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "HOSPITAL_ID")
    private Long id;

    @Column(name = "HOSPITAL_NAME", length = 45, nullable = false)
    private String hospitalName;

    @Column(name = "INSTITUTE_CODE", length = 20, nullable = false)
    private String instituteCode;

    @Column(name = "CHIEF_NAME", length = 10, nullable = false)
    private String chiefName;

    public Hospital(String hospitalName, String instituteCode, String chiefName) {
        this.hospitalName = hospitalName;
        this.instituteCode = instituteCode;
        this.chiefName = chiefName;
    }
}
