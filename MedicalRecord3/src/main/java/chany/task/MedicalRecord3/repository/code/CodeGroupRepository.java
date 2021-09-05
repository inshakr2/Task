package chany.task.MedicalRecord3.repository.code;

import chany.task.MedicalRecord3.domain.code.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {
}
