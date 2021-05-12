package chany.task.MedicalRecord2.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public void register(Visit visit) {
        visit.setPatient(this);
        visits.add(visit);
    }

}
