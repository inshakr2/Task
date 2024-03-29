package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.domain.Visit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Override
    @EntityGraph(attributePaths = {"hospital"})
    Optional<Visit> findById(Long aLong);
}
