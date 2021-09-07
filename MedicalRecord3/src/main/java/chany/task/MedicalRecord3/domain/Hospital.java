package chany.task.MedicalRecord3.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "HOSPITAL_SEQ_GEN",
        sequenceName = "HOSPITAL_SEQ",
        initialValue = 1, allocationSize = 1
)
public class Hospital extends BaseTimeEntity{

    @Id @Column(name = "HOSPITAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "HOSPITAL_SEQ_GEN")
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
