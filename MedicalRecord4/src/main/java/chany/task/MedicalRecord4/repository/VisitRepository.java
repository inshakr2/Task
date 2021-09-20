package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
