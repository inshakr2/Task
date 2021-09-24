package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p " +
            "JOIN FETCH p.visits v " +
            "JOIN FETCH v.hospital " +
            "WHERE p.id = :id")
    public Optional<Patient> findWithVisits(@Param("id") Long id);
}
