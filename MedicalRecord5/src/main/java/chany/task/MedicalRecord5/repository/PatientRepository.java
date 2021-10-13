package chany.task.MedicalRecord5.repository;

import chany.task.MedicalRecord5.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
