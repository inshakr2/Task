package chany.task.MedicalRecord3.domain.code;

import chany.task.MedicalRecord3.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeGroup extends BaseTimeEntity implements Persistable<String>{

    @Id
    @Column(name = "CODE_GROUP")
    private String codeGroup;
    @Column(name = "GROUP_NAME", length = 10, nullable = false)
    private String groupName;
    @Column(name = "DESCRIPTION", length = 30, nullable = false)
    private String description;

    public CodeGroup(String codeGroup, String groupName, String description) {
        this.codeGroup = codeGroup;
        this.groupName = groupName;
        this.description = description;
    }

    @Override
    public String getId() {
        return codeGroup;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
