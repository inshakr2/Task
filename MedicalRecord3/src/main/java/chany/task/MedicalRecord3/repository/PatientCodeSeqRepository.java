package chany.task.MedicalRecord3.repository;

import chany.task.MedicalRecord3.domain.PatientCodeSeq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCodeSeqRepository extends JpaRepository<PatientCodeSeq, String> {
}
