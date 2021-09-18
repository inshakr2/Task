package chany.task.MedicalRecord4.domain.code;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {

    @Nullable
    ID gedId();
    boolean isNew();
}
