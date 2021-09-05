package chany.task.MedicalRecord3.domain.code;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {

    @Nullable
    ID getId();
    boolean isNew();
}
