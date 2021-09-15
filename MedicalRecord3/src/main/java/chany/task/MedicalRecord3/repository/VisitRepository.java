package chany.task.MedicalRecord3.repository;

import chany.task.MedicalRecord3.domain.Visit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v FROM Visit v WHERE v.id = :id")
    @EntityGraph(attributePaths = {"patient", "hospital"})
    Optional<Visit> findVisitEntityGraph(@Param("id") Long id);
}
