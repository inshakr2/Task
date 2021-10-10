package chany.task.MedicalRecord5.domain.code;

import chany.task.MedicalRecord5.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeGroup extends BaseTimeEntity implements Persistable<String> {

    @Id @Column(name = "CODE_GROUP")
    private String codeGroup;

    @Column(name = "GROUP_NAME", length = 10, nullable = false)
    private String groupName;
    @Column(name = "DESCRIPTION", length = 30, nullable = false)
    private String description;

    @Override
    public String getId() {
        return codeGroup;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
