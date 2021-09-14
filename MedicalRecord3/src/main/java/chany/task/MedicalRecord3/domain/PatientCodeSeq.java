package chany.task.MedicalRecord3.domain;

import chany.task.MedicalRecord3.domain.BaseTimeEntity;
import chany.task.MedicalRecord3.domain.code.Persistable;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity @Getter
public class PatientCodeSeq implements Persistable {

    @Id
    private String created;
    private Long seq = 1L;

    public PatientCodeSeq() {
        this.created = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYMMdd"));
    }

    public void countUp() {
        this.seq ++;
    }

    @Override
    public Object getId() {
        return created;
    }

    @Override
    public boolean isNew() {
        return getCreated() == null;
    }

}