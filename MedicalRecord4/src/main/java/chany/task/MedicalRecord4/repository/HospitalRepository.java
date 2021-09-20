package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
