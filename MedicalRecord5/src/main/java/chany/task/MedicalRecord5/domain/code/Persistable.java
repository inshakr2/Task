package chany.task.MedicalRecord5.domain.code;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {

    @Nullable
    ID getId();
    boolean isNew();
}
