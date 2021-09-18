package chany.task.MedicalRecord4.repository.code;

import chany.task.MedicalRecord4.domain.code.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {
}
