package chany.task.MedicalRecord3.repository;

import chany.task.MedicalRecord3.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
