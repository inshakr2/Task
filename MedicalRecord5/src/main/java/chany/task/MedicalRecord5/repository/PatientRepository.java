package chany.task.MedicalRecord5.repository;

import chany.task.MedicalRecord5.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {
    @Query("SELECT p FROM Patient p " +
            "JOIN FETCH p.visits v " +
            "JOIN FETCH v.hospital " +
            "WHERE p.id = :id")
    public Optional<Patient> findWithVisits(@Param("id") Long id);

    @Query("SELECT p FROM Patient p " +
            "JOIN FETCH p.visits")
    List<Patient> findAllWithVisits();
}
