package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.PatientCodeSeq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCodeSeqRepository extends JpaRepository<PatientCodeSeq, String> {
}
