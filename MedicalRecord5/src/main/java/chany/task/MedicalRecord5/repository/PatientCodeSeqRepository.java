package chany.task.MedicalRecord5.repository;

import chany.task.MedicalRecord5.domain.PatientCodeSeq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCodeSeqRepository extends JpaRepository<PatientCodeSeq, String> {
}
