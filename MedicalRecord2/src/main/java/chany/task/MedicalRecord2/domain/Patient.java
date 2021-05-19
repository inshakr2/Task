package chany.task.MedicalRecord2.domain;

import chany.task.MedicalRecord2.common.PatientKeyGenerator;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static chany.task.MedicalRecord2.common.PatientKeyGenerator.generate;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Patient extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;
    @Column(name = "PATIENT_NAME")
    private String name;
    @Column(name = "PATIENT_CODE")
    private String code;
    private String gender;
    private String birth;
    private String phoneNumber;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

}
