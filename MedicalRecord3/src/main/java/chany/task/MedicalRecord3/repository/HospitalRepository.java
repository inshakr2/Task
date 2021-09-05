package chany.task.MedicalRecord3.repository;

import chany.task.MedicalRecord3.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
