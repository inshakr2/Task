package chany.task.MedicalRecord4.domain.code;

import chany.task.MedicalRecord4.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Code extends BaseTimeEntity implements Persistable<String>{

    @Id @Column(name = "CODE")
    private String code;
    @Column(name = "CODE_NAME", length = 10, nullable = false)
    private String codeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE_GROUP")
    private CodeGroup codeGroup;

    @Override
    public String gedId() {
        return code;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
