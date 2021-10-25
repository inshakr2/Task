package chany.task.MedicalRecord5.repository;

import chany.task.MedicalRecord5.domain.Visit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Override
    @Query("SELECT v FROM Visit v " +
            "JOIN FETCH v.hospital")
    Optional<Visit> findById(Long aLong);
}
