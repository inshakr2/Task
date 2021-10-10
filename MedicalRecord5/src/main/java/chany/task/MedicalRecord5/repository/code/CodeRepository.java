package chany.task.MedicalRecord5.repository.code;

import chany.task.MedicalRecord5.domain.code.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {
}
