package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.PatientCodeSeq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCodeSeqRepository extends JpaRepository<PatientCodeSeq, String> {
}
