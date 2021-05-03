package chany.task.MedicalRecord2.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Hospital extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "HOSPITAL_ID")
    private Long id;
    @Column(name = "HOSPITAL_NAME")
    private String name;
    private String instituteCode;
    private String chiefName;
}
