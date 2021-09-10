package chany.task.MedicalRecord3.repository;

import chany.task.MedicalRecord3.domain.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @EntityGraph(attributePaths = {"hospital"})
    @Query("SELECT p FROM Patient p WHERE p.id = :id")
    Optional<Patient> findPatientEntityGraph(@Param("id") Long id);
}
