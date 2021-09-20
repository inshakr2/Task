package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
